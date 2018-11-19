package com.zyl.dao;

import com.zyl.model.UserDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by z1761 on 2018/10/10.
 */
public interface UserDao {
    int insert(UserDomain record);



    List<UserDomain> selectUsers();


    UserDomain findUserById(@Param("id") String name);


    UserDomain findByUserObject(UserDomain userDomain);
}
