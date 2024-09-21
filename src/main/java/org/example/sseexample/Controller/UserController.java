package org.example.sseexample.Controller;

import org.example.sseexample.Session.UserSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public List<String> getUsers() {
        return new ArrayList<>(UserSession.getUserSessions().keySet());
    }
}