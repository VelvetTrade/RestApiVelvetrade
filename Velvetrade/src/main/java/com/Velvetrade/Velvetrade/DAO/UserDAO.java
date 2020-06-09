package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.Posting;
import com.Velvetrade.Velvetrade.Model.User;

import java.util.List;

public interface UserDAO {
    public int addNewUser(User user);
    public int updateUserByID(String id,User user);
    public int deleteUserByID(String id);
    public User getUserByID(String id);
    public List<User> findUserByName(String name);
    public List<Posting> getAllPostingsPerUser(String id);
    public User authenticateUser(String username,String password);
}
