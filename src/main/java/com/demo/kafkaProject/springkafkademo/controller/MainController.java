package com.demo.kafkaProject.springkafkademo.controller;

import com.demo.kafkaProject.springkafkademo.model.Tweet;
import com.demo.kafkaProject.springkafkademo.repository.TweetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class MainController {

    @Autowired
    private TweetsRepository tweetsRepository;

   @RequestMapping("/")
    public String home() {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/showAllEmployee'>Show All Tweets</a></li>";
        html += " <li><a href='/deleteAllEmployee'>Delete All Tweets</a></li>";
        html += "</ul>";
        return html;
    }

    @RequestMapping("/showAllTweets")
    public String showAllTweets() {

        List<Tweet> tweets = this.tweetsRepository.findAll();
        List<String> mentions = new ArrayList<>();
        List<String> hashtags = new ArrayList<>();
        String html = "";
        for (Tweet t : tweets) {

            for (String mention: t.getMentions().split(",")) {
                mentions.add(mention);
            }
            for (String hashtag: t.getHashtags().split(",")) {
                hashtags.add(hashtag);
            }

            html += t.getMentions() + "<br>";
        }

        Map<String,Long > mentionOccurence =
                mentions.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        Map<String,Long > hashtagOccurence =
                hashtags.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        if(mentionOccurence.containsKey("")){
            mentionOccurence.remove("");
        }
        if(hashtagOccurence.containsKey("")){
            hashtagOccurence.remove("");
        }


        for (Map.Entry<String, Long> entry : mentionOccurence.entrySet()) {
            if (entry.getValue()==(Collections.max(mentionOccurence.values()))) {
                System.out.println(entry.getKey()+ " "+ entry.getValue());
            }
        }
        for (Map.Entry<String, Long> entry : hashtagOccurence.entrySet()) {
            if (entry.getValue()==(Collections.max(hashtagOccurence.values()))) {
                System.out.println(entry.getKey()+" "+entry.getValue());
            }
        }

        return html;
    }
}
