package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.Group;
import com.Velvetrade.Velvetrade.Model.IdNotFoundException;
import com.Velvetrade.Velvetrade.Model.InvalidNewUserException;
import com.Velvetrade.Velvetrade.Model.Storage;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GroupDAO {

    public List<Group> searchByName(String search);
    public Group getGroupByID(String id) throws IdNotFoundException;
    public int updateGroupByID(String id,Group group);
    public int deleteGroupByID(String id);
    public Group createGroup(String userId, Group group,String password) throws ExecutionException, InterruptedException, InvalidNewUserException;
    public boolean checkIfGroupNameExists(String s) throws ExecutionException, InterruptedException;
    public boolean validateUserEntry(String groupID,String userId,String entered_password) throws IdNotFoundException, ExecutionException, InterruptedException;
    public int removeUserByID(String groupID,String userID) throws IdNotFoundException;

    public void createStorage(Storage s);

//probably not needed and can just use update instead

//    public int addToGroupByID(String groupId,String userId);
//    public int changeGroupName(String groupID,String newName);




}
