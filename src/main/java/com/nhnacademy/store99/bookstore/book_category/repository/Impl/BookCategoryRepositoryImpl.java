package com.nhnacademy.store99.bookstore.book_category.repository.Impl;

import com.nhnacademy.store99.bookstore.author.entity.QAuthor;
import com.nhnacademy.store99.bookstore.book.entity.QBook;
import com.nhnacademy.store99.bookstore.book_author.entity.QBookAuthor;
import com.nhnacademy.store99.bookstore.book_author.response.BookTransDTO;
import com.nhnacademy.store99.bookstore.book_category.entity.BookCategory;
import com.nhnacademy.store99.bookstore.book_category.entity.QBookCategory;
import com.nhnacademy.store99.bookstore.book_category.repository.BookCategoryRepository;
import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import com.nhnacademy.store99.bookstore.book_category.response.CategoryParentsDTO;
import com.nhnacademy.store99.bookstore.category.entity.QCategory;
import com.nhnacademy.store99.bookstore.file.entity.QFile;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import java.util.ArrayList;
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
        select * from categories where parent_category_id = ?;
        1. 최초 입력 카테고리의 도서들의 목록을 가져와서 저장 > List A
        2. 이 id를 부모로 가지는 카테고리들 저장 > List B
        3. List B의 각 도서들을 저장 > List A
        4. List B의 각 카테고리를 부모로 가지는 카테고리 저장 > List C
        5. List B의 반복 끝난 후 List C -> List B 옮기기
        6. 3부터 List C의 size가 0일때까지 반복.
        카테고리의 모든 column이 필요함.
     */
    @Override
    public List<CategoryParentsDTO> getCategoriesByParentsId(Long categoryId) {
        QCategory category = QCategory.category;
        List<CategoryParentsDTO> categoryList;
        List<CategoryParentsDTO> parentsList = new ArrayList<>();

        // 발생하는 오류 원인. 엔티티에서는 부모가 category인데, 내가 부모를 id로만 받으려고 해서 그럼.
        // 받는 방법을 바꾸면 될듯

        // 1
        List<CategoryParentsDTO> returnList = new ArrayList<>(from(category).where(category.id.eq(categoryId)).select(
                Projections.constructor(
                        CategoryParentsDTO.class,
                        category.id.as("categoryId"),
                        category.categoryName.as("categoryName"),
                        category.categoryDepth.as("categoryDepth"),
                        category.parentCategory.id.as("parentCategoryId")
                )
        ).fetch());

        // 2
        categoryList =
                from(category).where(category.parentCategory.id.eq(categoryId))
                        .select(Projections.constructor(
                                CategoryParentsDTO.class,
                                category.id.as("categoryId"),
                                category.categoryName.as("categoryName"),
                                category.categoryDepth.as("categoryDepth"),
                                category.parentCategory.id.as("parentCategoryId")
                        )).fetch();
        do {


            // 3
            for (CategoryParentsDTO bct : categoryList) {
                returnList.addAll(
                        from(category).where(category.id.eq(bct.getCategoryId()))
                                .select(Projections.constructor(CategoryParentsDTO.class,
                                        category.id.as("categoryId"),
                                        category.categoryName.as("categoryName"),
                                        category.categoryDepth.as("categoryDepth"),
                                        category.parentCategory.id.as("parentCategoryId")
                                ))
                                .fetch()
                );
            }

            // 4
            for (CategoryParentsDTO bct : categoryList) {
                parentsList.addAll(
                        from(category).where(category.parentCategory.id.eq(bct.getCategoryId()))
                                .select(Projections.constructor(CategoryParentsDTO.class,
                                        category.id.as("categoryId"),
                                        category.categoryName.as("categoryName"),
                                        category.categoryDepth.as("categoryDepth"),
                                        category.parentCategory.id.as("parentCategoryId")
                                ))
                                .fetch()
                );
            }


            // 5
            if (!parentsList.isEmpty()) {
                categoryList.clear();
                categoryList.addAll(parentsList);
                parentsList.clear();
            } else {
                break;
            }

            // 6
        } while (true);
        // 파라미터로 받은 id를 가지고, 그의 자식 카테고리들이 반환된다.
        return returnList;
    }


    // 도서 하나에 카테고리 하나.
    // 카테고리 하나에 여러 도서니까 이 결과에서 중복되는 값은 없을것.
    @Override
    public Page<BookTransDTO> getBooksByCategories(List<CategoryParentsDTO> parentsDTOList, Pageable pageable) {
        QBookCategory bookCategory = QBookCategory.bookCategory;
        QBook book = QBook.book;
        QAuthor author = QAuthor.author;
        QBookAuthor bookAuthor = QBookAuthor.bookAuthor;
        QFile file = QFile.file;
        List<BookTransDTO> bookResponsesDtoVar = new ArrayList<>();

        // 가능하면 jpql 이친구를 union같이 합쳐서 paging을 적용하고싶은데
        // JPQL은 union이 없다고함...
        // 밑의 반복문은 요구된 카테고리와 그 자식들을 가지는 모든 BookResponse객체를 반환함.
        for (CategoryParentsDTO CPDTO : parentsDTOList) {
            bookResponsesDtoVar.addAll(from(bookCategory).
                    where(bookCategory.id.eq(CPDTO.getCategoryId())).
                    where(bookCategory.book.id.eq(book.id)).
                    select(Projections.bean(BookTransDTO.class,
                            book.id.as("bookId"),
                            book.bookIsbn13,
                            book.bookIsbn10,
                            book.bookTitle,
                            book.bookContents,
                            book.bookDescription,
                            book.bookPublisher,
                            book.bookDate,
                            book.bookPrice,
                            book.bookSalePrice,
                            book.bookIsPacked,
                            book.bookThumbnailUrl,
                            book.bookStock,
                            book.bookCntOfReview,
                            book.bookAvgOfRate,
                            book.createdAt,
                            book.updatedAt
                    )).distinct().fetch());
        }
        List<Long> bookIds = bookResponsesDtoVar.stream().map(BookTransDTO::getBookId).collect(Collectors.toList());


        Map<Long, List<BookTransDTO.AuthorDTO>> authorsMap = from(bookAuthor)
                .where(bookAuthor.book.id.in(bookIds))
                .join(bookAuthor.author, author)
                .transform(GroupBy.groupBy(bookAuthor.book.id)
                        .as(GroupBy.list(
                                        Projections.constructor(BookTransDTO.AuthorDTO.class,
                                                author.authorName, author.authorType)
                                )
                        )
                );


        List<BookTransDTO> bookResponses = bookResponsesDtoVar.stream().map(b ->
                {
                    return BookTransDTO.builder()
                            .BookId(b.getBookId())
                            .BookIsbn13(b.getBookIsbn13())
                            .BookIsbn10(b.getBookIsbn10())
                            .BookTitle(b.getBookTitle())
                            .BookContents(b.getBookContents())
                            .BookPublisher(b.getBookPublisher())
                            .BookDate(b.getBookDate())
                            .BookPrice(b.getBookPrice())
                            .BookSalePrice(b.getBookSalePrice())
                            .BookIsPacked(b.getBookIsPacked())
                            .BookThumbnailUrl(b.getBookThumbnailUrl()).BookStock(b.getBookStock())
                            .BookCntOfReview(b.getBookCntOfReview()).BookAvgOfRate(b.getBookAvgOfRate())
                            .CreatedAt(b.getCreatedAt()).UpdatedAt(b.getUpdatedAt())
                            .authorsDTOList(authorsMap.get(b.getBookId()))
                            .build();
                }
        ).collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), bookResponses.size());
        // 도서들 잘 나오는거같음. 나중에 한번 더 확실하게 확인해보고. 일단 괜찮아보임
        // 그리고 이미지랑 작가들도 잘 나오니까 도서들마다 잘 붙여줘서 페이징 하면 완성될거같음

        return new PageImpl<>(bookResponses.subList((int) pageable.getOffset(), end), pageable, bookResponses.size());
    }

    @Override
    public BookCategoryResponse getCategoryByBookId(Long bookId) {
        return null;
    }


}
