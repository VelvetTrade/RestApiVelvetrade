package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.IdNotFoundException;
import com.Velvetrade.Velvetrade.Model.Posting;
import com.Velvetrade.Velvetrade.Model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserDAO {
    public User addNewUser(User user);
    public int updateUserByID(String id,User user);
    public int deleteUserByID(String id);
    public User getUserByID(String id) throws IdNotFoundException;
    public List<User> findUserByName(String name);
    public List<Posting> getAllPostingsPerUser(String id) throws ExecutionException, InterruptedException;
    public User authenticateUser(String username,String password);

    List<User> getUsersByIDs(List<String> id);
}
