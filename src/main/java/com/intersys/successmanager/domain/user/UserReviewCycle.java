package com.intersys.successmanager.domain.user;

import com.intersys.successmanager.domain.review.ReviewCycle;
import com.intersys.successmanager.domain.template.Template;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@Builder
public class UserReviewCycle {

    @DBRef
    private ReviewCycle reviewCycle;
    @DBRef
    private Template template;
    private SurveyResponse surveyResponse;
    private boolean userSubmitted;
    private boolean managerReviewed;

}
