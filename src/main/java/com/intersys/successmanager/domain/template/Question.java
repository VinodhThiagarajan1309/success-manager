package com.intersys.successmanager.domain.template;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Question {

    private String questionId;
    private String questionType;
    private String questionText;
    private List<QuestionsOptions> questionsOptions;
}
