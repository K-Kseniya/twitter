package com.twitter.service;

import com.twitter.model.UserMessage;
import com.twitter.model.dto.UserMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TwitterServiceTest {
    private TwitterService service;
    private static final String USER_EMAIL = "test@gmail.com";
    private static final String FRIEND_EMAIL = "test2@gmail.com";
    private static final String USER_MESSAGE = "test";
    private static final String USER_SECOND_MESSAGE = "test_test";
    private static final String FRIEND_MESSAGE = "test2";

    @BeforeEach
    public void init(){
        service = new TwitterService();
    }

    @Test
    void shouldAddFirstMessage() {
        // GIVEN
        UserMessageRequest message = new UserMessageRequest(USER_EMAIL, USER_MESSAGE);
        // WHEN
        UserMessage userMessage = service.addMessage(message);
        // THEN
        assertEquals(USER_MESSAGE, userMessage.getMessage());
        assertEquals(USER_EMAIL, userMessage.getUserEmail());
    }

    @Test
    void shouldAddSingleMessageToExistingAndReturnedInReversedOrder() {
        // GIVEN
        UserMessageRequest firstMessage = new UserMessageRequest(USER_EMAIL, USER_MESSAGE);
        service.addMessage(firstMessage);

        UserMessageRequest secondMessage = new UserMessageRequest(USER_EMAIL, USER_SECOND_MESSAGE);
        service.addMessage(secondMessage);

        // WHEN
        Collection<UserMessage> wall = service.getWall(USER_EMAIL);
        // THEN
        assertEquals(2, wall.size());
        List<UserMessage> wallAsList = new ArrayList<>(wall);
        assertEquals(USER_SECOND_MESSAGE, wallAsList.get(0).getMessage());
        assertEquals(USER_MESSAGE, wallAsList.get(1).getMessage());
    }

    @Test
    void shouldAddMessagesForDifferentUsers() {
        // GIVEN
        UserMessageRequest firstMessage = new UserMessageRequest(USER_EMAIL, USER_MESSAGE);
        service.addMessage(firstMessage);

        UserMessageRequest secondMessage = new UserMessageRequest(FRIEND_EMAIL, FRIEND_MESSAGE);
        service.addMessage(secondMessage);

        // WHEN
        Collection<UserMessage> wall = service.getWall(USER_EMAIL);
        // THEN
        List<UserMessage> wallAsList = new ArrayList<>(wall);
        assertEquals(1, wall.size());
        assertEquals(USER_MESSAGE, wallAsList.get(0).getMessage());

    }

    @Test
    void shouldReturnTimelineIfThereIsOneFriend() {
        // GIVEN
        UserMessageRequest secondMessage = new UserMessageRequest(FRIEND_EMAIL, FRIEND_MESSAGE);
        service.addMessage(secondMessage);
        service.follow(USER_EMAIL, FRIEND_EMAIL);
        // WHEN
        Set<UserMessage> timeline = service.getTimeline(USER_EMAIL);
        // THEN
        List<UserMessage> timelineAsList = new ArrayList<>(timeline);
        assertEquals(1, timelineAsList.size());
        assertEquals(FRIEND_EMAIL, timelineAsList.get(0).getUserEmail());
        assertEquals(FRIEND_MESSAGE, timelineAsList.get(0).getMessage());
    }

    @Test
    void shouldReturnEmptyTimelineIfThereIsNoFriends() {
        // WHEN
        Set<UserMessage> timeline = service.getTimeline(USER_EMAIL);
        // THEN
        assertEquals(0, timeline.size());
    }
    
}