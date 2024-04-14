package com.devops.dockerization.repository;

import com.devops.dockerization.model.EntityAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityAddressRepository extends JpaRepository<EntityAddress,Long> {


}
