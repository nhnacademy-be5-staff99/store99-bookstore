package com.nhnacademy.store99.bookstore.category.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.store99.bookstore.category.dto.request.AddCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.request.ModifyCategoryRequest;
import com.nhnacademy.store99.bookstore.category.dto.response.CategoryForAdminResponse;
import com.nhnacademy.store99.bookstore.category.exception.CategoryNotFoundException;
import com.nhnacademy.store99.bookstore.category.service.CategoryAdminService;
import com.nhnacademy.store99.bookstore.common.response.CommonHeader;
import com.nhnacademy.store99.bookstore.common.response.CommonResponse;
import com.nhnacademy.store99.bookstore.config.RestDocSupport;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * @author seunggyu-kim
 */
@WebMvcTest(CategoryAdminController.class)
class CategoryAdminControllerTest extends RestDocSupport {
    @MockBean
    private CategoryAdminService categoryAdminService;

    @DisplayName("관리자 카테고리 관리 페이지 조회 성공")
    @Test
    void getCategories() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);
        List<CategoryForAdminResponse> categoCategoryForAdminResponseList = List.of(
                CategoryForAdminResponse.builder().id(1L).categoryName("국내도서").categoryDepth(1).build(),
                CategoryForAdminResponse.builder().id(2L).categoryName("과학").categoryDepth(2).parentCategoryId(1L)
                        .build(),
                CategoryForAdminResponse.builder().id(3L).categoryName("컴퓨터/모바일").categoryDepth(2).parentCategoryId(1L)
                        .build(),
                CategoryForAdminResponse.builder().id(4L).categoryName("경제경영").categoryDepth(2).parentCategoryId(1L)
                        .build(),
                CategoryForAdminResponse.builder().id(5L).categoryName("대학교재/전문서적").categoryDepth(2)
                        .parentCategoryId(1L).build(),
                CategoryForAdminResponse.builder().id(6L).categoryName("프로그래밍 개발/방법론").categoryDepth(3)
                        .parentCategoryId(3L).build(),
                CategoryForAdminResponse.builder().id(7L).categoryName("인공지능").categoryDepth(3).parentCategoryId(3L)
                        .build(),
                CategoryForAdminResponse.builder().id(8L).categoryName("컴퓨터 공학").categoryDepth(3).parentCategoryId(3L)
                        .build(),
                CategoryForAdminResponse.builder().id(9L).categoryName("프로그래밍 언어").categoryDepth(3).parentCategoryId(3L)
                        .build(),
                CategoryForAdminResponse.builder().id(10L).categoryName("기초과학/교양과학").categoryDepth(3)
                        .parentCategoryId(2L).build()
        );
        Page<CategoryForAdminResponse> expectedCategoryPage = new PageImpl<>(categoCategoryForAdminResponseList);
        given(categoryAdminService.getCategories(any(Pageable.class)))
                .willReturn(expectedCategoryPage);

        // when
        String actualResponse = mockMvc.perform(get("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.OK).resultMessage("Success").build();
        CommonResponse<Page<CategoryForAdminResponse>> response =
                CommonResponse.<Page<CategoryForAdminResponse>>builder().header(header).result(expectedCategoryPage)
                        .build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("관리자 카테고리 조회 실패 - 관리자 권한 없음")
    @Test
    void getCategoriesWithNotAdmin() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(false);

        // when
        String actualResponse = mockMvc.perform(get("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header =
                CommonHeader.builder().httpStatus(HttpStatus.FORBIDDEN).resultMessage("관리자 권한 없음").build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("카테고리 추가 성공")
    @Test
    void addCategory() throws Exception {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New category", 1L);
        given(categoryAdminService.addCategoryAndGetId(any(AddCategoryRequest.class)))
                .willReturn(2L);
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        String actualResponse = mockMvc.perform(post("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpectAll(
                        status().isCreated(),
                        header()
                                .string("Location", equalTo("/admin/v1/categories/2"))
                ).andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.CREATED).build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("카테고리 추가 성공 - 없는 부모 카테고리 입력")
    @Test
    void addCategoryWithNotExistParentCategory() throws Exception {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New Category", 99L);
        given(categoryAdminService.addCategoryAndGetId(any(AddCategoryRequest.class)))
                .willThrow(new CategoryNotFoundException(99L));
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        String actualResponse = mockMvc.perform(post("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header = CommonHeader.builder().httpStatus(HttpStatus.NOT_FOUND)
                .resultMessage("Category not found (categoryId: 99)").build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("카테고리 추가 실패 - 관리자 권한 없음")
    @Test
    void addCategoryWithNotAdmin() throws Exception {
        // given
        AddCategoryRequest request = new AddCategoryRequest("New Category", 99L);
        given(adminCheckService.isAdmin(anyLong())).willReturn(false);

        // when
        String actualResponse = mockMvc.perform(post("/admin/v1/categories")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();

        // then
        CommonHeader header =
                CommonHeader.builder().httpStatus(HttpStatus.FORBIDDEN).resultMessage("관리자 권한 없음").build();
        CommonResponse<Void> response = CommonResponse.<Void>builder().header(header).build();
        String expectedResponse = objectMapper.writeValueAsString(response);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @DisplayName("카테고리 수정 성공")
    @Test
    void modifyCategorySuccessfully() throws Exception {
        // given
        ModifyCategoryRequest request = new ModifyCategoryRequest("Modified Category", 1L);
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        mockMvc.perform(put("/admin/v1/categories/1")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // then
        verify(categoryAdminService).modifyCategory(anyLong(), any(ModifyCategoryRequest.class));
    }

    @DisplayName("카테고리 수정 실패 - 사용자 ID 없음")
    @Test
    void modifyCategoryFailsWhenUserIdIsMissing() throws Exception {
        // given
        ModifyCategoryRequest request = new ModifyCategoryRequest("Modified Category", 1L);
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        mockMvc.perform(put("/admin/v1/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        // then
        verify(categoryAdminService, never()).modifyCategory(anyLong(), any(ModifyCategoryRequest.class));
    }

    @DisplayName("카테고리 수정 실패 - 관리자 권한 없음")
    @Test
    void modifyCategoryFailsWhenNotAdmin() throws Exception {
        // given
        ModifyCategoryRequest request = new ModifyCategoryRequest("Modified Category", 1L);
        given(adminCheckService.isAdmin(anyLong())).willReturn(false);

        // when
        mockMvc.perform(put("/admin/v1/categories/1")
                        .header("X-USER-ID", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());

        // then
        verify(categoryAdminService, never()).modifyCategory(anyLong(), any(ModifyCategoryRequest.class));
    }

    @DisplayName("카테고리 삭제 성공")
    @Test
    void removeCategorySuccessfully() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        mockMvc.perform(delete("/admin/v1/categories/1")
                        .header("X-USER-ID", 1L))
                .andExpect(status().isOk());

        // then
        verify(categoryAdminService).removeCategory(1L);
    }

    @DisplayName("카테고리 삭제 실패 - 사용자 ID 없음")
    @Test
    void removeCategoryFailsWhenUserIdIsMissing() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        mockMvc.perform(delete("/admin/v1/categories/1"))
                .andExpect(status().isBadRequest());

        // then
        verify(categoryAdminService, never()).removeCategory(anyLong());
    }

    @DisplayName("카테고리 삭제 실패 - 관리자 권한 없음")
    @Test
    void removeCategoryFailsWhenNotAdmin() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(false);

        // when
        mockMvc.perform(delete("/admin/v1/categories/1")
                        .header("X-USER-ID", 1L))
                .andExpect(status().isForbidden());

        // then
        verify(categoryAdminService, never()).removeCategory(anyLong());
    }

    @DisplayName("카테고리 삭제 복구 성공")
    @Test
    void restoreCategorySuccessfully() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        mockMvc.perform(put("/admin/v1/categories/1/restore")
                        .header("X-USER-ID", 1L))
                .andExpect(status().isOk());

        // then
        verify(categoryAdminService).restoreCategory(1L);
    }

    @DisplayName("카테고리 삭제 복구 실패 - 사용자 ID 없음")
    @Test
    void restoreCategoryFailsWhenUserIdIsMissing() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(true);

        // when
        mockMvc.perform(put("/admin/v1/categories/1/restore"))
                .andExpect(status().isBadRequest());

        // then
        verify(categoryAdminService, never()).restoreCategory(anyLong());
    }

    @DisplayName("카테고리 삭제 복구 실패 - 관리자 권한 없음")
    @Test
    void restoreCategoryFailsWhenNotAdmin() throws Exception {
        // given
        given(adminCheckService.isAdmin(anyLong())).willReturn(false);

        // when
        mockMvc.perform(put("/admin/v1/categories/1/restore")
                        .header("X-USER-ID", 1L))
                .andExpect(status().isForbidden());

        // then
        verify(categoryAdminService, never()).restoreCategory(anyLong());
    }
}