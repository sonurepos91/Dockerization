package com.devops.dockerization.controller;

import com.devops.dockerization.dto.UserVo;
import com.devops.dockerization.model.UserEntity;
import com.devops.dockerization.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserManagementController<T> {

    Logger log = LogManager.getLogger(UserManagementController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> saveUserDetails(@Valid @RequestBody UserVo userDetails){

        log.info(":::::::::::::::: Save User Process Initiated :::::::::::::::::");
        userService.validateUserRequest(userDetails);
        Long userId = userService.saveUser(userDetails);
        return new ResponseEntity<>((T)userId,HttpStatus.CREATED);
    }

    @GetMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getAllUsers(@RequestParam(defaultValue = "0") String pageNo,
                                         @RequestParam(defaultValue = "2") String pageSize,
                                         @RequestParam(defaultValue = "firstName") String sortBy){

        log.info(":::::::::::::::: Get User Process Initiated :::::::::::::::::");
        return new ResponseEntity<>((T) userService.getAllUsers(pageNo,pageSize,sortBy),HttpStatus.CREATED);
    }

    @GetMapping(value = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getUserDeatils(@PathVariable Long userId){

        log.info(":::::::::::::::: Get User Process By UserId Initiated :::::::::::::::::");
        return new ResponseEntity<>((T)userService.getUserDetails(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/users/entityManager/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getUserDeatilsByEntityManager(@PathVariable Long userId){

        log.info(":::::::::::::::: Get User Process By UserId Initiated :::::::::::::::::");
        return new ResponseEntity<>((T)userService.getUserDetailsByEntityManager(userId), HttpStatus.OK);
    }

    @GetMapping(value="/users/age/{age}")
    public ResponseEntity<T> getAllUsersWithAgeGreaterThanThirty(@PathVariable Integer age){

        log.info(":::::::::::::::: Get User Process By Age Initiated :::::::::::::::::");
        List<UserEntity> userDetails = userService.getUserDetailsByAge(age);
        return new ResponseEntity<>((T)userDetails,HttpStatus.OK);
    }

    @GetMapping(value = "/users/age/{age}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> getAllUsersOrderByLastName( @PathVariable Integer age){

        log.info(":::::::::::::::: Get All Users Order By Name Initiated :::::::::::::::::");
        List<UserEntity> userEntities= userService.getUsersOrderByName(age);
        return new ResponseEntity<>((T)userEntities, HttpStatus.OK);
    }

    @GetMapping(value = "/users/firstname/{firstname}/phoneNo/{phoneNo}")
    public ResponseEntity<T> getUserByFirstNameOrPhoneNo(@PathVariable String firstname , @PathVariable String phoneNo){

        log.info(":::::::::::::::: Get User By  Name Or Phone No Initiated :::::::::::::::::");
        return new ResponseEntity<>((T)userService.getUserByFirstNameOrPhoneNo(firstname,phoneNo),HttpStatus.OK);
    }
}
