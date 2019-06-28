package com.demo.kafkaProject.springkafkademo.controller;

import com.demo.kafkaProject.springkafkademo.model.Tweet;
import com.demo.kafkaProject.springkafkademo.repository.TweetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
import java.util.stream.Collectors;


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
            html += t + "<br>";
        }


        tweets.forEach(tweet -> {
            Arrays.stream(tweet.getMentions().split(",")).forEach(mention -> {
                mentions.add(mention);
            });
            Arrays.stream(tweet.getHashtags().split(",")).forEach(hashtag -> {
                hashtags.add(hashtag);
            });
        });
        Map<String,Long > mmO1 =
                mentions.stream().filter(mention -> !mention.equals("")).collect(Collectors.groupingBy(w -> w,  Collectors.counting())).entrySet().stream()
                        .sorted((Map.Entry.<String, Long>comparingByValue().reversed()))
                        .limit(3)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map<String,Long > hhO1 =
                hashtags.stream().filter(hashtag -> !hashtag.equals("")).collect(Collectors.groupingBy(w -> w, Collectors.counting())).entrySet().stream()
                        .sorted((Map.Entry.<String, Long>comparingByValue().reversed()))
                        .limit(3)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(mmO1);
        System.out.println(hhO1);


//        Map<String,Long > mmO =
//                mentions.stream().filter(mention -> !mention.equals("")).collect(Collectors.groupingBy(w -> w, TreeMap::new, Collectors.counting()));
//        Map<String,Long > hhO =
//                hashtags.stream().filter(hashtag -> !hashtag.equals("")).collect(Collectors.groupingBy(w -> w, TreeMap::new, Collectors.counting()));
//
//
//
//
//        mmO.forEach((k,v)->{
//            if (v==(Collections.max(mmO.values()))) {
//                System.out.println(k+ " "+ v );
//            }
//        });
//        hhO.forEach((k,v)->{
//            if (v==(Collections.max(hhO.values()))) {
//                System.out.println(k+ " "+ v );
//            }
//        });

        return html;
    }
}
