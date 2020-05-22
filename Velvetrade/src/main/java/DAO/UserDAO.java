package DAO;

import Model.User;

import java.util.List;

public interface UserDAO {
    public int addNewUser(User user);
    public int updateUserByID(String id);
    public int deleteUserByID(String id);
    public User getUserByID(String id);
    public List<User> findUserByName(String name);
}
