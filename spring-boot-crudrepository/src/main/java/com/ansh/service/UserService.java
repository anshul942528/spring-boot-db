package com.ansh.service;

import com.ansh.model.User;
import com.ansh.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(User user){
        userRepository.save(user);
        return "saved";
    }

    public Iterable<User> findAllUser(){
        return userRepository.findAll();
    }

    public User findUser(Integer id){
        return userRepository.findById(id).get();
    }

    public String updateUser(User user){
        userRepository.save(user);
        return "updated";
    }

    public String deleteUser(Integer id){
        userRepository.deleteById(id);
        return "deleted";
    }
}
