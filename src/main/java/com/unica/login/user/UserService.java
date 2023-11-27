package com.unica.login.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    
    public void saveUser(User user){
        repository.save(user);
    }
    public List<User> viewAll(){
        return (List<User>) repository.findAll();
    }

   public User findById(Integer id) throws UserNotFound {
       Optional<User> optionalUser = repository.findById(id);
       if(optionalUser.isPresent()){
           return optionalUser.get();
       }else {
           throw new UserNotFound("The User not found");
       }
   }
    public void deleteById(Integer id) throws UserNotFound {
       Integer count=repository.countById(id);

       if(count==0 || count==null){
           throw new UserNotFound("The user do not Exist");
       }  else {
           repository.deleteById(id);
       }
    }
    public List<User> allRoles(User user){
        return repository.findAllByRoles(user.getRole());
    }
    public org.springframework.security.core.userdetails.User findByUsername(String email) {
        return repository.findByEmail(email);
    }
   
}
