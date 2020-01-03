package com.nulpointerexception.recommendation.controller;

import com.nulpointerexception.recommendation.db.Contributor;
import com.nulpointerexception.recommendation.db.RecommendParam;
import com.nulpointerexception.recommendation.db.RoleType;
import com.nulpointerexception.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "recommend")
public class RecommendController {
    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping(method= RequestMethod.POST,path = "/writer")
    public ResponseEntity recommend(@RequestBody RecommendParam recommendParam) throws Exception {
        List<Contributor> contributors =  recommendationService.recommend(recommendParam);
        return new ResponseEntity<>(contributors, HttpStatus.OK);
    }
}
