package com.quick.start.ddd.domain.module.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String questionerId;
    private String title;
    private String detail;

    public Question(String questionerId, String title, String detail) {
        this.questionerId = questionerId;
        this.title = title;
        this.detail = detail;
    }

    protected Question() {

    }

    public String getId() {
        return id;
    }

    public String getQuestionerId() {
        return questionerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
