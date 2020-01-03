package com.nulpointerexception.recommendation.controller;

import com.nulpointerexception.recommendation.db.Contributor;
import com.nulpointerexception.recommendation.db.RecommendParam;
import com.nulpointerexception.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/recommend")
    public String recommend(Model model) {
        model.addAttribute("recommendParam", new RecommendParam());
        return "recommend";
    }

    @PostMapping("/recommend")
    public ModelAndView recommend1(@ModelAttribute RecommendParam recommendParam) {
        List<Contributor> contributors = recommendationService.recommend(recommendParam);
        contributors.stream().forEach(x -> System.out.println(x.getName()));
        ModelAndView mav = new ModelAndView("recommendation");
        mav.addObject("contributors", contributors);
        return mav;
    }
}
