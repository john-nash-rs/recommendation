package com.nulpointerexception.recommendation.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendParam {
    private String topic;
    private String subTopic;
    private Integer wordLimit;
    private RoleType roleType;
    private Integer costPerWord;
    private String category;
}
