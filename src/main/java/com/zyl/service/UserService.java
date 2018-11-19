package com.zyl.service;

import com.github.pagehelper.PageInfo;
import com.zyl.model.UserDomain;

/**
 * Created by z1761 on 2018/10/10.
 */
public interface UserService {
    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);


    UserDomain findByUserID(String name);


    UserDomain findByUserObject(UserDomain userDomain);
}
