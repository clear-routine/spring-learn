package com.tt.repository;

import org.springframework.stereotype.Repository;

/**
 * 用户数据访问层
 */
@Repository
public class UserRepository {

    public String getUser() {
        return "TT";
    }
}
