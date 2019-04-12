package com.intersys.successmanager.domain.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SurveyResponse {

    private List<Answer> answerList;


}
