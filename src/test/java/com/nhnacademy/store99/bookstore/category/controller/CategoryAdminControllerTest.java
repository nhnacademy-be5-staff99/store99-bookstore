package com.nhnacademy.store99.bookstore.category.controller;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.exception.CategoryNotFoundException;
import com.nhnacademy.store99.bookstore.category.service.CategoryAdminService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.config.RestDocSupport;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author seunggyu-kim
 */
@WebMvcTest(CategoryAdminController.class)
class CategoryAdminControllerTest extends RestDocSupport {
    @MockBean
    private CategoryAdminService categoryAdminService;

    @Test
    void viewAdminCategoryManagementPage() {
        // TODO
    }

    @DisplayName("카테고리 추가 성공")
    @Test
    void addCategory() throws Exception {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New category", 1, null);
        BDDMockito.given(categoryAdminService.addCategoryAndGetId(Mockito.any(AddCategoryRequest.class)))
                .willReturn(1L);
        BDDMockito.given(adminCheckService.isAdmin(Mockito.anyLong())).willReturn(true);

        // when
        String actualResponse = mockMvc.perform(MockMvcRequestBuilders.post("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.header()
                                .string("Location", Matchers.equalTo("/admin/v1/categories/1"))
                ).andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.CREATED).build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("카테고리 추가 실패 - 없는 부모 카테고리 입력")
    @Test
    void addCategoryWithNotExistParentCategory() throws Exception {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New Category", 2, 99L);
        BDDMockito.given(categoryAdminService.addCategoryAndGetId(Mockito.any(AddCategoryRequest.class)))
                .willThrow(new CategoryNotFoundException(99L));
        BDDMockito.given(adminCheckService.isAdmin(Mockito.anyLong())).willReturn(true);

        // when
        String actualResponse = mockMvc.perform(MockMvcRequestBuilders.post("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.NOT_FOUND)
                .resultMessage("Category not found (categoryId: 99)").build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("카테고리 추가 실패 - 관리자 권한 없음")
    @Test
    void addCategoryWithNotAdmin() throws Exception {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New Category", 2, 99L);
        BDDMockito.given(adminCheckService.isAdmin(Mockito.anyLong())).willReturn(false);

        // when
        String actualResponse = mockMvc.perform(MockMvcRequestBuilders.post("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header =
                CommonHeader.builder().httpStatus(HttpStatus.FORBIDDEN).resultMessage("관리자 권한 없음").build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void modifyCategory() {
        // TODO
    }

    @Test
    void removeCategory() {
        // TODO
    }
}