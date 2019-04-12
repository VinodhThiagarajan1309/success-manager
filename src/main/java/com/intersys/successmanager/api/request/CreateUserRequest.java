package com.intersys.successmanager.api.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserRequest {

    private String userId;
    private String emailId;
    private String name;
    private String managerId;

}
