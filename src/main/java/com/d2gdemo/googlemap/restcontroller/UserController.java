package com.d2gdemo.googlemap.restcontroller;

import com.d2gdemo.googlemap.dao.UserDao;
import com.d2gdemo.googlemap.dto.UserDto;
import com.d2gdemo.googlemap.entity.User;
import com.d2gdemo.googlemap.restcontroller.exception.ServerException;
import com.d2gdemo.googlemap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService dao;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addUser(@RequestBody UserDto userDto) throws ServerException {

        dao.saveUser(userDto);


    }


}
