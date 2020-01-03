package com.nulpointerexception.recommendation.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weight {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Double topic;

    private Double subTopic;

    private Double wordLimit;

    private Double costPerWord;

    private Double category;
}
