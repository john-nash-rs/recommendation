package com.nulpointerexception.recommendation.ranker;

import com.nulpointerexception.recommendation.db.Preference;
import com.nulpointerexception.recommendation.db.Weight;
import com.nulpointerexception.recommendation.db.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

@Service
public class Ranker {

    @Autowired
    private WeightRepository weightRepository;
    public List<Preference> rank(List<Preference> preferences){
        List<Preference> rankedPreference = new ArrayList<>();
        Optional<Weight> weightOpt = weightRepository.findById(1l);
        if(!weightOpt.isPresent())
            return preferences;
        Weight weight = weightOpt.get();

        PriorityQueue<Preference> contributorPriorityQueue = new PriorityQueue<>();
        preferences.stream().forEach(preference -> calculateRankingCost(preference, weight, contributorPriorityQueue));
        while(!contributorPriorityQueue.isEmpty()){
            rankedPreference.add(contributorPriorityQueue.remove());
        }
        return rankedPreference;
    }

    private void calculateRankingCost(Preference preference, Weight weight, PriorityQueue<Preference> contributorPriorityQueue) {
        Double rankedCost = 1D;
        if(weight.getCategory() != null){
            switch (preference.getCategory()){
                case "gold":
                    rankedCost = rankedCost * Math.pow(101,weight.getCategory());
                case "silver":
                    rankedCost = rankedCost * Math.pow(1,weight.getCategory());
            }
        }

        if(weight.getCostPerWord() != null){
            Integer globalMax = 500;
            Integer globalMin = 0;
            Double costNormalized = 100 * ((preference.getCostPerWord().doubleValue() - globalMin.doubleValue()) / (globalMax.doubleValue() - globalMin.doubleValue()));
            rankedCost = rankedCost * Math.pow(costNormalized,weight.getCostPerWord());
        }

        System.out.println("=========== Rank is "+rankedCost+" for "+preference.getCostPerWord());
        preference.setRankingCost(rankedCost);
        contributorPriorityQueue.add(preference);
    }
}
