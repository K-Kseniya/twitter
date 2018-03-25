package com.twitter.service;

import com.twitter.model.UserMessage;
import com.twitter.model.dto.UserMessageRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TwitterService {
    private final Map<String, Set<UserMessage>> messages;
    private final Map<String, Set<String>> followers;

    TwitterService() {
        messages = new ConcurrentHashMap<>();
        followers = new ConcurrentHashMap<>();
    }

    public UserMessage addMessage(UserMessageRequest post) {
        UserMessage userMessage = new UserMessage(post);
        messages.computeIfAbsent(post.getEmail(), (k) -> new TreeSet<>()).add(userMessage);
        return userMessage;
    }

    public Collection<UserMessage> getWall(String email) {
        return messages.get(email);
    }

    public void follow(String userEmail, String followerEmail) {
        followers.computeIfAbsent(userEmail, (k) -> new HashSet<>()).add(followerEmail);
    }

    public Set<UserMessage> getTimeline(String email) {
        Set<String> emails = followers.get(email);
        if (emails == null) {
            return Collections.EMPTY_SET;
        }

        Set<UserMessage> result = new TreeSet<>();
        for (String friendEmail : emails) {
            Set<UserMessage> friendsMessages = messages.get(friendEmail);
            if (friendsMessages != null) {
                result.addAll(friendsMessages);
            }
        }

        return result;

    }

}