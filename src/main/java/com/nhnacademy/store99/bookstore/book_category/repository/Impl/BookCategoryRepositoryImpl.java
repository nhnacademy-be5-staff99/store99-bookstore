package com.nhnacademy.store99.bookstore.book_category.repository.Impl;

import com.nhnacademy.store99.bookstore.author.entity.QAuthor;
import com.nhnacademy.store99.bookstore.book.dto.response.BookListElementDTO;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_author.entity.QBookAuthor;
import com.nhnacademy.store99.bookstore.book_category.dto.response.BookCategoryResponse;
import com.nhnacademy.store99.bookstore.book_category.dto.response.CategoryParentsDTO;
import com.nhnacademy.store99.bookstore.book_category.entity.BookCategory;
import com.nhnacademy.store99.bookstore.book_category.entity.QBookCategory;
import com.nhnacademy.store99.bookstore.book_category.repository.BookCategoryRepository;
import com.nhnacademy.store99.bookstore.book_tag.entity.QBookTag;
import com.nhnacademy.store99.bookstore.category.entity.QCategory;
import com.nhnacademy.store99.bookstore.tag.entity.QTag;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookCategoryRepositoryImpl extends QuerydslRepositorySupport implements BookCategoryRepository {


    public BookCategoryRepositoryImpl() {
        super(BookCategory.class);
    }


    /*
        카테고리 전부 가져와서 파라미터로 받은 id의 자식들 반환함
     */
    @Override
    public List<CategoryParentsDTO> getCategoriesByParentsId(Long categoryId) {
        QCategory category = QCategory.category;
        Map<Long, List<CategoryParentsDTO>> categoryList = from(category)
                .where(category.parentCategory.id.isNotNull())
                .transform(GroupBy.groupBy(category.parentCategory.id)
                        .as(GroupBy.list(Projections.constructor(
                                CategoryParentsDTO.class,
                                category.id.as("categoryId"),
                                category.parentCategory.id.as("parentCategoryId")
                        ))));
        Comparator<CategoryParentsDTO> sorted =
                Comparator.comparing(CategoryParentsDTO::getCategoryId, Comparator.naturalOrder());

        CategoryParentsDTO topDTO = from(category).where(category.id.eq(categoryId)).select(
                Projections.constructor(CategoryParentsDTO.class,
                        category.id.as("categoryId"),
                        category.parentCategory.id.as("parentCategoryId")
                )
        ).fetchOne();
        List<CategoryParentsDTO> list = new ArrayList<>();
        list.add(topDTO);
        list.addAll(recursionCategory(categoryId, categoryList));
        return list.stream().sorted(sorted).collect(Collectors.toList());
    }


    @Override
    public Page<BookListElementDTO> getBooksByCategories(List<CategoryParentsDTO> parentsDTOList, Pageable pageable) {
        QBookCategory bookCategory = QBookCategory.bookCategory;
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QTag tag = QTag.tag;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;
        QBookTag bookTag = QBookTag.bookTag;

        List<Long> parentIds =
                parentsDTOList.stream().map(CategoryParentsDTO::getCategoryId).collect(Collectors.toList());
        JPQLQuery<BookListElementDTO> bookResponsesQuery = from(bookCategory).
                where(bookCategory.book.id.eq(book.id)).
                where(bookCategory.book.deletedAt.isNull()).
                where(bookCategory.category.id.in(parentIds)).
                select(Projections.bean(BookListElementDTO.class,
                        book.id.as("bookId"),
                        book.bookTitle,
                        book.bookPublisher,
                        book.bookDate,
                        book.bookPrice,
                        book.bookSalePrice,
                        book.bookThumbnailUrl,
                        book.bookCntOfReview,
                        book.bookViewCount,
                        book.bookStock,
                        book.bookAvgOfRate
                )).distinct();
        int totalSize = bookResponsesQuery.fetch().size();
        List<BookListElementDTO> bookResponsesDtoVar =
                new ArrayList<>(bookResponsesQuery.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch());


        List<Long> bookIds =
                bookResponsesDtoVar.stream().map(BookListElementDTO::getBookId).collect(Collectors.toList());


        Map<Long, List<BookListElementDTO.AuthorDTO>> authorsMap = from(bookAuthor)
                .where(bookAuthor.book.id.in(bookIds))
                .join(bookAuthor.author, author)
                .transform(GroupBy.groupBy(bookAuthor.book.id)
                        .as(GroupBy.list(
                                        Projections.constructor(BookListElementDTO.AuthorDTO.class,
                                                author.authorName, author.authorType)
                                )
                        )
                );

        /**
         * book id를 그룹화하여 tag id에 해당하는 tagName 담기
         *
         * @author rosin23
         */

        Map<Long, List<BookListElementDTO.TagDTO>> tagMap = from(bookTag)
                .where(bookTag.book.id.in(bookIds))
                .join(bookTag.tag, tag)
                .transform(GroupBy.groupBy(bookTag.book.id)
                        .as(GroupBy.list(
                                        Projections.constructor(BookListElementDTO.TagDTO.class,
                                                tag.tagName)
                                )
                        )
                );


        List<BookListElementDTO> bookResponses = bookResponsesDtoVar.stream().map(b ->
                {
                    return BookListElementDTO.builder()
                            .BookId(b.getBookId())
                            .BookTitle(b.getBookTitle())
                            .BookPublisher(b.getBookPublisher())
                            .BookDate(b.getBookDate())
                            .BookPrice(b.getBookPrice())
                            .BookSalePrice(b.getBookSalePrice())
                            .BookThumbnailUrl(b.getBookThumbnailUrl())
                            .BookCntOfReview(b.getBookCntOfReview())
                            .BookViewCount(b.getBookViewCount())
                            .BookStock(b.getBookStock())
                            .BookAvgOfRate(b.getBookAvgOfRate())
                            .authorsDTOList(authorsMap.get(b.getBookId()))
                            .tagDTOList(tagMap.get(b.getBookId()))
                            .build();
                }
        ).collect(Collectors.toList());

        return new PageImpl<>(bookResponses, pageable, totalSize);
    }

    @Override
    public BookCategoryResponse getCategoryByBookId(Long categoryId) {
        return null;
    }

    private List<CategoryParentsDTO> recursionCategory(Long targetId,
                                                       Map<Long, List<CategoryParentsDTO>> allCategoriesMap) {
        // 바닥
        if (allCategoriesMap.get(targetId) == null | targetId == null) {
            return new ArrayList<>();
        }
        List<CategoryParentsDTO> returnList = new ArrayList<>();

        // 자식들 가져와서.
        List<CategoryParentsDTO> eles = allCategoriesMap.get(targetId);
        for (CategoryParentsDTO ele : eles) {
            // 자식을 가지고 있지 않다면
            if (!allCategoriesMap.containsKey(ele.getParentCategoryId())) {
                // 마지막 자식이니까 스스로가 들어감.
                returnList.add(ele);
            } else {
                // 마지막 자식이 아니면 그의 자식들을 가져와서 넣음
                returnList.add(ele);
                returnList.addAll(recursionCategory(ele.getCategoryId(), allCategoriesMap));
            }
        }
        return returnList;
    }

}
