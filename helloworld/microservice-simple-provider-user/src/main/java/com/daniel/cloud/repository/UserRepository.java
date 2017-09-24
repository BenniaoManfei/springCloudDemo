package com.daniel.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.cloud.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
