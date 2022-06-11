package com.quick.start.ddd.domain.application.command;

public record CreateQuestionCommand(
        String questionId,
        String title,
        String detail
) {
}
