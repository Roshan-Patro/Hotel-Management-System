package com.hotelmanagement.userService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanagement.userService.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
