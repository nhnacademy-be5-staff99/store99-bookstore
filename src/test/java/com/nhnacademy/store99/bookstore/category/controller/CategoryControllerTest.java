package com.nhnacademy.store99.bookstore.category.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.store99.bookstore.category.dto.ActiveCategoryIdAndNameDto;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.bookstore.category.entity.Category;
import com.nhnacademy.store99.bookstore.category.service.CategoryService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.config.RestDocSupport;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author seunggyu-kim
 */
@WebMvcTest(CategoryController.class)
class CategoryControllerTest extends RestDocSupport {

    @MockBean
    private CategoryService categoryService;

    @DisplayName("현재 카테고리의 자식 목록과 경로 조회 성공 - 정상적인 카테고리 ID")
    @Test
    void getActiveCategoriesSuccess() throws Exception {
        // given
        Long categoryId = 1L;
        Category category1 = Category.builder().id(categoryId).categoryName("국내도서").categoryDepth(1).build();
        Category category2 =
                Category.builder().id(2L).categoryName("과학").categoryDepth(2).parentCategory(category1).build();

        List<ActiveCategoryIdAndNameDto> childrenCategories = List.of(
                new ActiveCategoryIdAndNameDto(3L, "컴퓨터/모바일"),
                new ActiveCategoryIdAndNameDto(4L, "인문/사회")
        );

        CategoryChildrenListAndRouteResponse expectedResponseBody = new CategoryChildrenListAndRouteResponse();
        expectedResponseBody.setNowCategoryRouteByCategory(category2);
        expectedResponseBody.setChildrenCategoryList(childrenCategories);

        given(categoryService.getChildrenListAndRoute(anyLong())).willReturn(expectedResponseBody);

        // when
        String actualResponse =
                mockMvc.perform(MockMvcRequestBuilders.get("/open/v1/categories?categoryId=" + categoryId)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.OK).resultMessage("Success").build();
        CommonResponse<Object> response =
                CommonResponse.builder().header(header).result(expectedResponseBody)
                        .build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("현재 카테고리의 자식 목록과 경로 조회 성공 - 존재하지 않는 카테고리 ID")
    @Test
    void getActiveCategoriesWithNonExistentCategoryId() throws Exception {
        // given
        Long categoryId = 999L;
        CategoryChildrenListAndRouteResponse expectedResponseBody = new CategoryChildrenListAndRouteResponse();
        given(categoryService.getChildrenListAndRoute(anyLong())).willReturn(expectedResponseBody);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/open/v1/categories?categoryId=" + categoryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.result.nowCategoryRoute").doesNotExist(),
                        jsonPath("$.result.childrenCategoryList").isEmpty()
                );
    }
}