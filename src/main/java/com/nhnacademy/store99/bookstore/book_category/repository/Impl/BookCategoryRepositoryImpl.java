package com.nhnacademy.store99.bookstore.book_category.repository.Impl;

import com.nhnacademy.store99.bookstore.book_category.entity.BookCategory;
import com.nhnacademy.store99.bookstore.book_category.repository.BookCategoryRepository;
import com.nhnacademy.store99.bookstore.book_category.response.BookCategoryResponse;
import com.nhnacademy.store99.bookstore.category.entity.QCategory;
import com.querydsl.core.types.Projections;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookCategoryRepositoryImpl extends QuerydslRepositorySupport implements BookCategoryRepository {
    public BookCategoryRepositoryImpl() {
        super(BookCategory.class);
    }


    /*
        parents_id = ? 인 녀석들 가져옴.
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
    public List<BookCategoryResponse> getCategoriesByParentsId(Long categoryId) {
        QCategory category = QCategory.category;
        List<BookCategoryResponse> categoryList;
        List<BookCategoryResponse> parentsList = new ArrayList<>();

        // 발생하는 오류 원인. 엔티티에서는 부모가 category인데, 내가 부모를 id로만 받으려고 해서 그럼.
        // 받는 방법을 바꾸면 될듯

        // 1
        List<BookCategoryResponse> returnList = new ArrayList<>(from(category).where(category.id.eq(categoryId)).select(
                Projections.constructor(
                        BookCategoryResponse.class,
                        category.id.as("categoryId"),
                        category.categoryName.as("categoryName"),
                        category.categoryDepth.as("categoryDepth"),
                        category.parentCategory.id.as("parentCategoryId")
                )
        ).fetch());

        // 2
        Long tempCategoryId = categoryId;
        categoryList =
                from(category).where(category.parentCategory.id.eq(tempCategoryId))
                        .select(Projections.constructor(
                                BookCategoryResponse.class,
                                category.id.as("categoryId"),
                                category.categoryName.as("categoryName"),
                                category.categoryDepth.as("categoryDepth"),
                                category.parentCategory.id.as("parentCategoryId")
                        )).fetch();
        do {


            // 3
            for (BookCategoryResponse bct : categoryList) {
                returnList.addAll(
                        from(category).where(category.id.eq(bct.getCategoryId()))
                                .select(Projections.constructor(BookCategoryResponse.class,
                                        category.id.as("categoryId"),
                                        category.categoryName.as("categoryName"),
                                        category.categoryDepth.as("categoryDepth"),
                                        category.parentCategory.id.as("parentCategoryId")
                                ))
                                .fetch()
                );
            }

            // 4
            for (BookCategoryResponse bct : categoryList) {
                parentsList.addAll(
                        from(category).where(category.parentCategory.id.eq(bct.getCategoryId()))
                                .select(Projections.constructor(BookCategoryResponse.class,
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
        // 파라미터로 받은 id를 가진, 부모로 가진 태그들이 반환된다.
        return returnList;
    }
}
