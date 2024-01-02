package com.sonld.ps.finalflashcard.business.model;

import com.sonld.ps.finalflashcard.components.Topic;
import javafx.scene.control.Button;

import java.util.List;

public class TopicResponse {
    private String id;
    private String name;
    private String description;
    private List<String> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Topic createTopic() {
        return new Topic(this.getName());
    }
}
