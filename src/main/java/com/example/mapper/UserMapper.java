package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectByNameAndPwd(User user);

    List<User> selectAllUser();

    int addUser(User user);

    int deleteUser(Integer id);

    int update(User user);

    User selectById(Integer id);
}
