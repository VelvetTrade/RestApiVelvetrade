package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserDAO {
    public User addNewUser(User user,String password) throws ExecutionException, InterruptedException, InvalidNewUserException;
    public int updateUserByID(String id,User user);
    public int deleteUserByID(String id);
    public User getUserByID(String id) throws IdNotFoundException;
    public List<User> findUserByName(String name);
    public List<Posting> getAllPostingsPerUser(String id) throws ExecutionException, InterruptedException;
    public User authenticateUser(String username,String password) throws ExecutionException, InterruptedException, AuthIsIncorrect;
    public boolean checkIfUserNameExists(String s) throws ExecutionException, InterruptedException;
    List<User> getUsersByIDs(List<String> id);
    public void createStorage(Storage s);
    public Posting createPosting(String userId, Posting posting);
    public void  removeUserPostingById(String userId, String postingId);
    public void  updateUserPostingById(String userId, String postingId,Posting p) throws ExecutionException, InterruptedException;
    public Posting  getUserPostingById(String userId,String postingId ) throws ExecutionException, InterruptedException;
    public List<Posting>  getUserPostingsByName(String userId,String item ) throws ExecutionException, InterruptedException;
}
