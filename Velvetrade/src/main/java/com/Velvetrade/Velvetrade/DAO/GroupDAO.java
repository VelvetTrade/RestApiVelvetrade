package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.Group;

import java.util.List;

public interface GroupDAO {

    public List<Group> searchByName(String search);
    public Group getGroupByID(String id);
    public int updateGroupByID(String id,Group group);
    public int deleteGroupByID(String id);
    public int createGroup(Group group);

    public boolean validateUserEntry(String groupID,String entered_password);
    public int removeUserByID(String groupID,String userID);



//probably not needed and can just use update instead

//    public int addToGroupByID(String groupId,String userId);
//    public int changeGroupName(String groupID,String newName);




}
