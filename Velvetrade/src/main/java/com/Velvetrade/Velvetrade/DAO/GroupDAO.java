package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.Group;
import com.Velvetrade.Velvetrade.Model.IdNotFoundException;

import java.util.List;

public interface GroupDAO {

    public List<Group> searchByName(String search);
    public Group getGroupByID(String id) throws IdNotFoundException;
    public int updateGroupByID(String id,Group group);
    public int deleteGroupByID(String id);
    public Group createGroup(Group group);

    public boolean validateUserEntry(String groupID,String userId,String entered_password) throws IdNotFoundException;
    public int removeUserByID(String groupID,String userID) throws IdNotFoundException;



//probably not needed and can just use update instead

//    public int addToGroupByID(String groupId,String userId);
//    public int changeGroupName(String groupID,String newName);




}
