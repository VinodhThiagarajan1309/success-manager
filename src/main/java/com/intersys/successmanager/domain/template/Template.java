package com.intersys.successmanager.domain.template;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "sm_review_templates")
public class Template {

    @Id
    private String id;
    private String templateId;
    private String templateName;
    private String templateAuthor;
    private List<Question> questions;
}
