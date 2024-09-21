package org.example.sseexample.Session;

import org.example.sseexample.EventSource;

import java.util.concurrent.ConcurrentHashMap;

public class UserSession {
    private static ConcurrentHashMap<String, EventSource> userSessions = new ConcurrentHashMap<>();

    public static void addUser(String username, EventSource eventSource) {
        userSessions.put(username, eventSource);
    }

    public static void removeUser(String username) {
        userSessions.remove(username);
    }

    public static ConcurrentHashMap<String, EventSource> getUserSessions() {
        return userSessions;
    }
}