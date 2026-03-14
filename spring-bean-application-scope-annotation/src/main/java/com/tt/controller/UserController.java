package com.tt.controller;

import com.tt.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

/**
 * 用户控制器，@ApplicationScope：整个 Web 应用一份，所有用户共享
 */
@Getter
@Controller
@ApplicationScope
public class UserController {

    private final String instanceId = UUID.randomUUID().toString();

    private int visitCount;

    @Setter
    @Autowired
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }

    public void incrementVisit() {
        visitCount++;
    }
}
