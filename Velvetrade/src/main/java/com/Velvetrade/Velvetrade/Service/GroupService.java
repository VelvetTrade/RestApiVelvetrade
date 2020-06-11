package com.Velvetrade.Velvetrade.Service;

import com.Velvetrade.Velvetrade.DAO.FakeGroupDAO;
import com.Velvetrade.Velvetrade.Model.Chat;
import com.Velvetrade.Velvetrade.Model.Group;
import com.Velvetrade.Velvetrade.Model.IdNotFoundException;
import com.Velvetrade.Velvetrade.Model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    FakeGroupDAO gDao;

    ///Initializes the com.Velvetrade.Velvetrade.DAO
    @Autowired
    public GroupService(@Qualifier("GroupDAO") FakeGroupDAO gDao) {
        this.gDao=gDao;

    }
    //Gets group by id
    public Group getGroupByID(String groupId) throws IdNotFoundException {
        return gDao.getGroupByID(groupId);
    }
    public ArrayList<Group> getAllGroupsFromIds(ArrayList<String> ids) throws IdNotFoundException {
        ArrayList<Group> a= new ArrayList<>();
        for(String id:ids){
            a.add(gDao.getGroupByID(id));
        }
        return a;
    }
    //deletes group by Id
    public void deleteGroupByID(String id){
        gDao.deleteGroupByID(id);
    }

    //creates group
    public Group createGroup(String userId, Group group){

      return  gDao.createGroup(userId,group);
    }
    ///Updates Group by ID
    public void updateGroupByID(String id, Group g){
        g.setId(id);
        gDao.updateGroupByID(id,g);
    }
    //Gets the one chat per group
    public Chat getChatByGroupId(String groupId){
        return gDao.getChatByGroup(groupId);
    }
    //gets the chat per group per id
    public Chat getChat(String id){
        return gDao.getChat(id);
    }

    //update chat
    public void updateChatByID(String id,Chat c){
        c.setId(id);
        gDao.updateChat(id,c);
    }

    //creates a new trade listing
    public Posting createNewPosting(String groupID, Posting p){

        return gDao.createPosting(groupID,p);

    }
    //gets all listings in a group where the offerings have not been accepted
    public List<Posting> getAllPostingsPerGroup(String groupID){
        System.out.println("Getting Postings per Group");
        return gDao.getAllPostingsPerGroup(groupID);
    }

    //searching for a group by name can be used
    public List<Group> searchByName(String s){
        return gDao.searchByName(s);
    }
    //Accepts trades
    public void acceptTrade(String groupId,String postingID,String offerID) throws IdNotFoundException {
        Posting p=getPostingByID(groupId,postingID);
        p.setAcceptedOfferID(offerID);
        Posting po=getPostingByID(groupId,offerID);
        po.setAcceptedOfferID(postingID);
        updatePosting(groupId,p);
    }

    //gets a specific posting from a specific group
    public Posting getPostingByID(String groupId, String postingId) throws IdNotFoundException {
        return gDao.getPostingByID(groupId,postingId);
    }
    //deletes a specific posting from a specific group
    public int deletePosting(String id,String postingId){
        return gDao.deletePosting(id, postingId);
    }
    //updates a specific posting
    public int updatePosting(String id,Posting posting){
        return gDao.updatePosting(id,posting);
    }

    //Validates if a User can enter a group
    public boolean validateUserEntry(String groupId, String userId, String entered_password) throws IdNotFoundException {

        return gDao.validateUserEntry(groupId,userId,entered_password);
    }
    //Removes a specific user from a group
    public int removeUserByID(String groupID,String userID) throws IdNotFoundException {
        return gDao.removeUserByID(groupID,userID);
    }

    public List<Posting> getPostingsByIDs(String id, List<String> postingId) {
        return gDao.getPostingsByIDs(id,postingId);
    }
}
