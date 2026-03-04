package com.tt.controller;

import com.tt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

/**
 * 用户控制器，@ApplicationScope：整个 Web 应用一份，所有用户共享
 */
@Controller
@ApplicationScope
public class UserController {

    private final String instanceId = UUID.randomUUID().toString();

    private int visitCount;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }

    public void incrementVisit() {

        visitCount++;
    }

    public int getVisitCount() {

        return visitCount;
    }

    public String getInstanceId() {

        return instanceId;
    }
}
