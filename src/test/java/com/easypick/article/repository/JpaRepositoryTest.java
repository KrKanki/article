package com.easypick.article.repository;

import com.easypick.article.config.JpaConfig;
import com.easypick.article.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
@Import(JpaConfig.class)
class JpaRepositoryTest {


    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorkFine(){

        // Given

        // When
        List<Article> articleList = articleRepository.findAll();

        // Then
        assertThat(articleList)
                .isNotNull()
                .hasSize(123);
    }
    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorkFine(){

        // Given
        long previousCount = articleRepository.count();

        // When
        articleRepository.save(Article.of("Article Title", "Article Content", "#Spring"));


        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorkFine(){

        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashTage = "#SpringBoot";
        article.setHashtag(updatedHashTage);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);


        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashTage);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorkFine(){

        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleComment = articleCommentRepository.count();
        int deletedCommentSize = article.getArticleComments().size();


        // When
        articleRepository.delete(article);


        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleComment-deletedCommentSize);
    }
}