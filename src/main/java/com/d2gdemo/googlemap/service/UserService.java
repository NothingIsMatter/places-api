package com.d2gdemo.googlemap.service;

import com.d2gdemo.googlemap.dao.UserDao;
import com.d2gdemo.googlemap.dto.UserDto;
import com.d2gdemo.googlemap.entity.Role;
import com.d2gdemo.googlemap.entity.User;
import com.d2gdemo.googlemap.restcontroller.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder encoder;

    public void saveUser(UserDto user) throws ServerException{

        if (userDao.findByLogin(user.getUsername())!=null) throw new ServerException();
        User newUser = new User();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setLogin(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setLocation(user.getLocation());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.getRoles().add(Role.USER);
        userDao.save(newUser);

    }
}
