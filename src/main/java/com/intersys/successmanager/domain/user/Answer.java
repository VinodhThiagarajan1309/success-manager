package com.intersys.successmanager.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {

    private String questionId;
    private String selectedChoice;
    private String commentedText;
    private String managerSelectedChoice;
    private String managerComments;

}
