package com.example.restapiproject.service;

import com.example.restapiproject.model.User;
import com.example.restapiproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getListUserById(long id) {
//        return userRepository.findByIdAndInActiveFalse(id);
        return userRepository.findByIdAndInActive(id,"N");

    }

    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User updateUser(long id, User user) {
        User getDataUser=getListUserById(id);
        getDataUser.setUserName(user.getUserName());
        getDataUser.setPassword(user.getPassword());
        getDataUser.setInActive(user.getInActive());
        userRepository.saveAndFlush(getDataUser);
        return getDataUser;
    }

    public User delete(long id) {
        User getDataUser=getListUserById(id);
        getDataUser.setInActive("Y");
        userRepository.saveAndFlush(getDataUser);
        return getDataUser;
    }
}
