package com.devops.dockerization.repository;

import com.devops.dockerization.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByFirstNameAndPhoneNo (String name, String phoneNo);

    Optional<UserEntity> findById (Long userId);

    List<UserEntity> findByAgeGreaterThanEqual (Integer age);

    List<UserEntity> findByAgeOrderByLastNameAsc (Integer age);

    List<UserEntity> findByFirstNameOrderByLastNameDesc(String name);

    Optional<UserEntity> findByFirstNameOrPhoneNo (String firstName , String phoneNo);


}
