package com.quick.start.ddd.domain.application;

import com.quick.start.ddd.domain.application.command.CreateQuestionCommand;
import com.quick.start.ddd.domain.application.resule.QuestionCreatedResult;
import com.quick.start.ddd.domain.model.entity.Question;
import com.quick.start.ddd.domain.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionApplicationService {

    private final QuestionRepository questionRepository;

    public QuestionApplicationService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionCreatedResult createQuestion(CreateQuestionCommand command) {
        Question question = new Question(command.questionId(), command.title(), command.detail());
        questionRepository.save(question);
        return new QuestionCreatedResult(question.getId());
    }
}
