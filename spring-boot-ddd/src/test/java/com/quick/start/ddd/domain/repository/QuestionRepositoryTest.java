package com.quick.start.ddd.domain.repository;

import com.quick.start.ddd.core.JpaRepositoryTest;
import com.quick.start.ddd.domain.module.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@JpaRepositoryTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void repository_should_successfully_save_question() {
        var question = new Question("UID_00001", "A test title", "A test detail");
        Question saveQuestion = questionRepository.save(question);
        assertThat(saveQuestion.getId(), is(notNullValue()));
        assertThat(saveQuestion.getId(), equalTo(question.getId()));
        assertThat(saveQuestion.getDetail(), equalTo(question.getDetail()));
        assertThat(saveQuestion.getTitle(), equalTo(question.getTitle()));
    }
}
