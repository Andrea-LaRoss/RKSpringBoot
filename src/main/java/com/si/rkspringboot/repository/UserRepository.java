package com.si.rkspringboot.repository;

import com.si.rkspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User searchUserByEmail(String email);

}
