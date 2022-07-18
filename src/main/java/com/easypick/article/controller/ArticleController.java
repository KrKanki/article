package com.easypick.article.controller;

import com.easypick.article.domain.type.SearchType;
import com.easypick.article.dto.response.ArticleCommentResponse;
import com.easypick.article.dto.response.ArticleResponse;
import com.easypick.article.dto.response.ArticleWithCommentsResponse;
import com.easypick.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @Autowired ArticleService articleService;
    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC ) Pageable pageable,
             ModelMap map){

        map.addAttribute("articles", articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from));

        return "articles/index";
    }

    @GetMapping("/{articlesId}")
    public String articles(@PathVariable Long articlesId, ModelMap map){
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articlesId));
        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());

        return "articles/detail";
    }



}
