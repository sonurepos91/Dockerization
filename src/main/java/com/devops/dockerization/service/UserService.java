package com.devops.dockerization.service;

import com.devops.dockerization.customException.UserAlreadyExistsException;
import com.devops.dockerization.dto.UserVo;
import com.devops.dockerization.model.UserEntity;

import java.util.List;

public interface UserService {

    void validateUserRequest (UserVo userDetails);

    Long saveUser (UserVo userDetails);

    UserEntity getUserDetails (Long userId);

    List<UserEntity> getUserDetailsByAge (Integer age);

    List<UserEntity> getUsersOrderByName(Integer age);

    UserEntity getUserByFirstNameOrPhoneNo (String firstName, String phoneNo);

    List<UserEntity> getAllUsers (String pageNo, String pageSize, String sortBy);

    UserEntity getUserDetailsByEntityManager (Long userId);
}
