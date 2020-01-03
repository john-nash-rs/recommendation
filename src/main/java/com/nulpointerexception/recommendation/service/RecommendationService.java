package com.nulpointerexception.recommendation.service;

import com.nulpointerexception.recommendation.db.*;
import com.nulpointerexception.recommendation.filter.Filter;
import com.nulpointerexception.recommendation.ranker.Ranker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private PreferenceRepository preferenceRepository;
    @Autowired
    private Ranker ranker;
    @Autowired
    private Filter filter;

    public List<Contributor> recommend(RecommendParam recommendParam){
        List<Preference> preferencesBeforeRanking = preferenceRepository.findAll(filter.filterQuery(recommendParam));
        List<Preference> preferences = ranker.rank(preferencesBeforeRanking);
        List<Contributor> contributors = preferences.stream().map(Preference::getContributor).collect(Collectors.toList());

        System.out.println("Hello size "+contributors.size());
        return contributors;
    }



}
