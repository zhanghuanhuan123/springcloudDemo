package com.ygego.user.dao.impl;

import com.ygego.user.dao.IUserDao;
import com.ygego.user.mapper.IUserMapper;
import com.ygego.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 简单用户链接Mysql数据库微服务（通过@Repository注解标注该类为持久化操作对象）。
 */
@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private IUserMapper iUserMapper;

    @Override
    public User findUserById(Long id) {
        return iUserMapper.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return iUserMapper.findAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return iUserMapper.insertUser(user);
    }
}
