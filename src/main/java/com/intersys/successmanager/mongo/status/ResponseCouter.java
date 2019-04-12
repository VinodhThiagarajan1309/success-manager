package com.intersys.successmanager.mongo.status;

import lombok.Data;

import java.util.List;

@Data
public class ResponseCouter {

    private String reviewCycleId;
    private String totalEmployees;
    private List<String> submittedUserIds;
}
