package com.zyl.dao;

import com.zyl.model.UserDomain;

import java.util.List;

/**
 * Created by z1761 on 2018/10/10.
 */
public interface UserDao {
    int insert(UserDomain record);



    List<UserDomain> selectUsers();
}
