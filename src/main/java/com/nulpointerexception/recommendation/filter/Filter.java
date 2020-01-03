package com.nulpointerexception.recommendation.filter;

import com.nulpointerexception.recommendation.db.Contributor;
import com.nulpointerexception.recommendation.db.Preference;
import com.nulpointerexception.recommendation.db.RecommendParam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class Filter {
    public static Specification<Preference> filterQuery(RecommendParam recommendParam) {
        return new Specification<Preference>() {
            @Override
            public Predicate toPredicate(Root<Preference> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(recommendParam.getTopic() != null)
                    predicates.add(criteriaBuilder.equal(root.get("topic"), recommendParam.getTopic()));
                if(recommendParam.getSubTopic() != null)
                    predicates.add(criteriaBuilder.equal(root.get("subTopic"), recommendParam.getSubTopic()));
                if(recommendParam.getWordLimit() != null)
                    predicates.add(criteriaBuilder.ge(root.get("wordLimit"), recommendParam.getWordLimit()));
                if(recommendParam.getCostPerWord() != null)
                    predicates.add(criteriaBuilder.ge(root.get("costPerWord"), recommendParam.getCostPerWord()));
                if(recommendParam.getCategory() != null)
                    predicates.add(criteriaBuilder.equal(root.get("category"), recommendParam.getCategory()));
                if(recommendParam.getRoleType() != null)
                    predicates.add(criteriaBuilder.equal(root.get("role"), recommendParam.getRoleType()));
                return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
            }
        };
    }
}
