package com.si.rkspringboot.repository;

import com.si.rkspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> searchByEmailContaining(String email);

    List<User> searchByFirstNameContaining(String firstName);

    List<User> searchByLastNameContaining(String lastName);

    List<User> searchByBirthdayContaining(LocalDate birthday);

    User searchUserByEmail(String email);

}
