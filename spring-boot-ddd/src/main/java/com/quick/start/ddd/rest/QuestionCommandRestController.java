package com.quick.start.ddd.rest;

import com.quick.start.ddd.domain.application.QuestionApplicationService;
import com.quick.start.ddd.domain.application.command.CreateQuestionCommand;
import com.quick.start.ddd.domain.application.resule.QuestionCreatedResult;
import com.quick.start.ddd.rest.request.CreateQuestionRequest;
import com.quick.start.ddd.rest.response.QuestionCreatedResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionCommandRestController {

    private final QuestionApplicationService questionApplicationService;

    public QuestionCommandRestController(QuestionApplicationService questionApplicationService) {
        this.questionApplicationService = questionApplicationService;
    }

    @PostMapping("/create-question")
    public QuestionCreatedResponse createQuestion(@RequestBody CreateQuestionRequest request) {
        CreateQuestionCommand command = new CreateQuestionCommand(request.questionId(), request.title(), request.detail());
        QuestionCreatedResult result = questionApplicationService.createQuestion(command);
        return new QuestionCreatedResponse(result.questionId());
    }
}
