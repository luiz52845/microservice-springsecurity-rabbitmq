package com.luizfernandes.auth.repository;

import com.luizfernandes.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM User p WHERE p.userName =:userName")
    User findByUserName(@Param("userName") String userName);
}


