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
public class Preference implements Comparable<Preference> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_preference_contributor")
    private Contributor contributor;

    private String topic;

    private String subTopic;

    private Integer wordLimit;

    private Integer costPerWord;

    private RoleType role;

    private String category;

    @Transient
    private Double rankingCost;

    @Override
    public int compareTo(Preference o) {
        if(this.getRankingCost() > o.getRankingCost()) {
            return -1;
        } else if (this.getRankingCost() < o.getRankingCost()) {
            return 1;
        } else {
            return 0;
        }
    }
}
