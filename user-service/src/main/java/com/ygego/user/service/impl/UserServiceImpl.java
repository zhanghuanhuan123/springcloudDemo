package com.ygego.user.service.impl;

import com.ygego.user.dao.IUserDao;
import com.ygego.user.model.User;
import com.ygego.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * 简单用户链接Mysql数据库微服务（通过@Service注解标注该类为持久化操作对象）。
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao iUserDao;

    @Override
    public User findUserById(Long id) {
        return iUserDao.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return iUserDao.findAllUsers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int insertUser(User user) throws Exception {
        try {
            iUserDao.insertUser(user);
        } catch (Exception e) {
            throw new IOException("qqq");
        }
        return 1;
    }
}