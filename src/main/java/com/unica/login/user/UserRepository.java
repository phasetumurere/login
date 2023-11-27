package com.unica.login.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Integer countById(Integer id);

    @Query("select u.role from User u")
    List<User>findAllByRoles(String username);

    org.springframework.security.core.userdetails.User findByEmail(String username);
}
