package com.unica.login;

import com.unica.login.user.User;
import com.unica.login.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class LoginApplicationTests {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void TestCreate() {
        User user = new User();
        user.setEmail("phase1@gmail.com");
        user.setFirstname("Phase");
        user.setLastname("TUMURERE");
        user.setPassword("343434");
        user.setRole("Admin");

        User savedUser = repository.save(user);
        User userExist = entityManager.find(User.class, savedUser.getId());
        Assertions.assertThat(userExist.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void viewUsers() {
        Iterable<User> allUsers = repository.findAll();
        Assertions.assertThat(allUsers).hasSizeGreaterThan(0);

        for (User user : allUsers) {
            System.out.println(user);
        }
    }
//    @Test
//    public void usersByRoles(User user){
//        List<User> roles = repository.findAllByRoles(Collections.singletonList(user.getRole()));
//        Assertions.assertThat(roles).hasSizeGreaterThan(0);
//        for(User users:roles){
//            System.out.println(users);
//        }
//    }

}
