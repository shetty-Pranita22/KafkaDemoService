package com.demo.kafkaProject.springkafkademo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="tweets")
public class Tweet {
    @Id
    private Long _id;

    @Field(value = "tweet")
    private String tweetContent;
    private String username;
    private String Hashtags;
    private String Mentions;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public void setTweetContent(String tweetContent) {
        this.tweetContent = tweetContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashtags() {
        return Hashtags;
    }

    public void setHashtags(String hashtags) {
        Hashtags = hashtags;
    }

    public String getMentions() {
        return Mentions;
    }

    public void setMentions(String mentions) {
        Mentions = mentions;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "_id=" + _id +
                ", tweetContent='" + tweetContent + '\'' +
                ", username='" + username + '\'' +
                ", Hashtags='" + Hashtags + '\'' +
                ", Mentions='" + Mentions + '\'' +
                '}';
    }
}
