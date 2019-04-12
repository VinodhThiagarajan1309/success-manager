package com.intersys.successmanager.domain.review;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "sm_review_cycles")
public class ReviewCycle {

    @Id
    private String id;
    private String cycleId;
    private String cycleName;
    private Date evalStartDate;
    private Date evalEndDate;
    private Date cycleKickDate;
    private Date cycleEndDate;
    private String cycleAuthor;

}
