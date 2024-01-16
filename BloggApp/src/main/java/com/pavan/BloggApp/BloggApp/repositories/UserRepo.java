package com.pavan.BloggApp.BloggApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.BloggApp.BloggApp.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
