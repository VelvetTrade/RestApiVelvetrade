package com.Velvetrade.Velvetrade.Service;

import com.Velvetrade.Velvetrade.DAO.FakeUserDAO;
import com.Velvetrade.Velvetrade.Model.IdNotFoundException;
import com.Velvetrade.Velvetrade.Model.Posting;
import com.Velvetrade.Velvetrade.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
     FakeUserDAO fDAO;

    @Autowired
    public UserService(@Qualifier("UserDAO") FakeUserDAO fDAO) {
        this.fDAO = fDAO;

    }

    //Adds a user
    public User addNewUser(User user) {
        return fDAO.addNewUser(user);

    }
    //gets all the postings of a user
    public List<Posting> getAllPostingsPerUser(String id) throws ExecutionException, InterruptedException {
        return fDAO.getAllPostingsPerUser(id);
    }

    public User authenticateUser(String username,String password){
        System.out.println("reached 2");
        return fDAO.authenticateUser(username,password);
    }

    //updates user by id
    public int updateUserByID(String id,User user) {
        user.setId(id);
        return fDAO.updateUserByID(id,user);
    }

    //deletes user by id
    public int deleteUserByID(String id) {
        return fDAO.deleteUserByID(id);
    }

    //gets a user by id
    public User getUserByID(String id) throws IdNotFoundException {
        return fDAO.getUserByID(id);
    }

    //Can be used to search for a user or sign in a user
    public List<User> findUserByName(String name) {
        return fDAO.findUserByName(name);
    }

    public List<User> getUsersByIDs(List<String> id) {
        return fDAO.getUsersByIDs(id);
    }
}

