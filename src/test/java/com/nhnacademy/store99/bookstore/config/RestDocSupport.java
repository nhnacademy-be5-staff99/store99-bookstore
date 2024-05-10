package com.nhnacademy.store99.bookstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.store99.bookstore.auth.service.AdminCheckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Rest docs를 편리하게 사용하기 위한 Support 클래스
 *
 * @author seunggyu-kim
 */
@Disabled
@Import(RestDocsConfig.class)
@ExtendWith({RestDocumentationExtension.class})
public abstract class RestDocSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected RestDocumentationResultHandler restDoc;

    // 관리자 여부 테스트 용으로 사용
    // ex) BDDMockito.given(adminCheckService.isAdmin(Mockito.anyLong())).willReturn(true);
    @MockBean
    protected AdminCheckService adminCheckService;

    /**
     * Spring Rest Docs를 사용하기 위한 설정
     *
     * @param webApplicationContext
     * @param restDocumentationContextProvider
     */
    @BeforeEach
    public void setup(
            final WebApplicationContext webApplicationContext,
            final RestDocumentationContextProvider restDocumentationContextProvider
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDoc)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))     // 한글 깨짐 방지 처리
                .build();
    }
}
