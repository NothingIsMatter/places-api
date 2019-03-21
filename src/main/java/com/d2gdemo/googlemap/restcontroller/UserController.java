package com.d2gdemo.googlemap.restcontroller;

import com.d2gdemo.googlemap.dao.UserDao;
import com.d2gdemo.googlemap.dto.UserDto;
import com.d2gdemo.googlemap.entity.User;
import com.d2gdemo.googlemap.restcontroller.exception.ServerException;
import com.d2gdemo.googlemap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.certpath.OCSPResponse;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService dao;
    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody UserDto userDto) throws ServerException{
       return new ResponseEntity<>(dao.signIn(userDto.getUsername(),userDto.getPassword()),HttpStatus.ACCEPTED);

    }
    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto) throws ServerException {
        dao.saveUser(userDto);

    }
    @GetMapping("get/{userId}")
    public User getUser(@PathVariable(name = "userId") String id)throws ServerException{
        return dao.getUser(id);
    }


}
