package Service;

import DAO.FakeGroupDAO;
import Model.Chat;
import Model.Group;
import Model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    FakeGroupDAO gDao;

    ///Initializes the DAO
    @Autowired
    public GroupService(@Qualifier("fakeDao") FakeGroupDAO gDao) {
        this.gDao=gDao;

    }
    //Gets group by id
    public Group getGroupByID(String groupId) {
        return gDao.getGroupByID(groupId);
    }
    public ArrayList<Group> getAllGroupsFromIds(ArrayList<String> ids){
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
    public void createGroup(Group group){

        gDao.createGroup(group);
    }
///Updates Group by ID
    public void updateGroupByID(String id, Group g){
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
        gDao.updateChat(id,c);
    }

    //creates a new trade listing
    public void createNewPosting(String groupID, Posting p){

        gDao.createPosting(groupID,p);

    }
    //gets all listings in a group where the offerings have not been accepted
    public List<Posting> getAllPostingsPerGroup(String groupID){
        return gDao.getAllPostingsPerGroup(groupID);
    }

    //searching for a group by name can be used
    public List<Group> searchByName(String s){
        return gDao.searchByName(s);
    }
    //Accepts trades
    public void acceptTrade(String groupId,String postingID,String offerID){
        Posting p=getPostingByID(groupId,postingID);
        p.setAcceptedOfferID(offerID);
        Posting po=getPostingByID(groupId,offerID);
        po.setAcceptedOfferID(postingID);
    }

    //gets a specific posting from a specific group
    public Posting getPostingByID(String groupId, String postingId){
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
    public boolean validateUserEntry(String groupID,String entered_password){
        return gDao.validateUserEntry(groupID,entered_password);
    }
    //Removes a specific user from a group
    public int removeUserByID(String groupID,String userID){
        return gDao.removeUserByID(groupID,userID);
    }

}
