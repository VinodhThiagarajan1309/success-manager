package com.intersys.successmanager.domain.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "sm_review_users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;
    private String emailId;
    private String name;
    private String managerId;
    private List<UserReviewCycle> userReviewCycleList;
}
