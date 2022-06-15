package com.quick.start.ddd.domain.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.quick.start.ddd.core.JpaRepositoryTest;
import com.quick.start.ddd.domain.model.entity.Question;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@JpaRepositoryTest
public class QuestionRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void repository_should_successfully_save_question() {
        var question = new Question("UID_00001", "A test title", "A test detail");
        question.editTitle("UID_00002", "for test", "A new test title");
        Question saveQuestion = questionRepository.save(question);
        assertSameQuestion(question, saveQuestion);
    }

    @Test
    void repository_should_successfully_find_question_by_id() {
        Question question = new Question("UID_00001", "A test title", "A test detail");
        question.editTitle("UID_00002", "for test", "A new test title");
        // 保存之后直接刷新到数据库
        Question savedQuestion = questionRepository.saveAndFlush(question);
        // 清理掉jpa的缓存
        entityManager.detach(savedQuestion);
        Question foundQuestion = questionRepository.findById(savedQuestion.getId()).orElseThrow(AssertionError::new);
        assertSameQuestion(foundQuestion, question);
    }

    private void assertSameQuestion(Question expectedQuestion, Question actualQuestion) {
        assertThat(actualQuestion.getId(), is(notNullValue()));
        assertThat(expectedQuestion.getId(), equalTo(actualQuestion.getId()));
        assertThat(expectedQuestion.getDetail(), equalTo(actualQuestion.getDetail()));
        assertThat(expectedQuestion.getTitle(), equalTo(actualQuestion.getTitle()));
        assertThat(actualQuestion.getUpdatedRecords(), hasSize(expectedQuestion.getUpdatedRecords().size()));
        for (int i = 0; i < actualQuestion.getUpdatedRecords().size(); i++) {
            assertThat(expectedQuestion.getUpdatedRecords().get(i), equalTo(actualQuestion.getUpdatedRecords().get(i)));
        }
    }
}
