package com.easypick.article.controller;

import com.easypick.article.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(MainController.class)
class MainControllerTest {

    private final MockMvc mvc;

    public MainControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void givenRootPath_whenRequestingRootPage_thenRedirectsToArticlesPage() throws Exception {

        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

}