package com.ansh.service;

import com.ansh.model.User;
import com.ansh.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(User user){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{user.getId(), user.getName(), user.getEmail()});
        userRepository.save(list);
        return "saved";
    }

    public List<User> findAllUser(){
        return userRepository.getUserList();
    }

    public User findUser(Integer id){
        return userRepository.getUser(id);
    }

    public String updateUser(User user){
        userRepository.updateUser(user);
        return "updated";
    }

    public String deleteUser(Integer id){
        userRepository.deleteUser(id);
        return "deleted";
    }
}
