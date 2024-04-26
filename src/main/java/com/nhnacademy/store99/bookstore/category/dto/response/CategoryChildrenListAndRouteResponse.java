package com.nhnacademy.store99.bookstore.category.dto.response;

import com.nhnacademy.store99.bookstore.category.dto.ActiveCategoryIdAndNameDto;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author seunggyu-kim
 */
@Getter
public class CategoryChildrenListAndRouteResponse {
    /**
     * 현재 검색된 카테고리까지의 경로를 역순으로 반환합니다.
     */
    public LinkedList<ActiveCategoryIdAndNameDto> nowCategoryRoute;

    /**
     * 현재 카테고리의 자식 카테고리 목록을 반환합니다.
     */
    @Setter
    public List<ActiveCategoryIdAndNameDto> childrenCategoryList;

    public void setNowCategoryRouteByCategory(Category category) {
        nowCategoryRoute = new LinkedList<>();
        while (category != null) {
            nowCategoryRoute.addFirst(new ActiveCategoryIdAndNameDto(category.getId(), category.getCategoryName()));
            if (category.getCategoryDepth() == 1) {
                break;
            }
            category = category.getParentCategory();
        }
    }
}