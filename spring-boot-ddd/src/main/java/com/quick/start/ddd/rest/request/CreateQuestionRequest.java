package com.quick.start.ddd.rest.request;

public record CreateQuestionRequest(
        String questionId,
        String title,
        String detail
) {
}
