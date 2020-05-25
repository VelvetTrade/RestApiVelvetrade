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
    @Autowired
    public GroupService(@Qualifier("fakeDao") FakeGroupDAO gDao) {
        this.gDao=gDao;

    }

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
    public void deleteGroupByID(String id){
        gDao.deleteGroupByID(id);
    }
    public void createGroup(Group group){

        gDao.createGroup(group);
    }

    public void updateGroupByID(String id, Group g){
        gDao.updateGroupByID(id,g);
    }
    public Chat getChatByGroupId(String groupId){
        return gDao.getChatByGroup(groupId);
    }

    public Chat getChat(String id){
        return gDao.getChat(id);
    }
    public void updateChatByID(String id,Chat c){
        gDao.updateChat(id,c);
    }
    public void createNewPosting(String groupID, Posting p){

        gDao.createPosting(groupID,p);

    }
    public List<Posting> getAllPostingsPerGroup(String groupID){
        return gDao.getAllPostingsPerGroup(groupID);
    }
    public List<Posting> getAllPostingsPerUser(String userID){
        return gDao.getAllPostingsPerUser(userID);
    }
    public List<Group> searchByName(String s){
        return gDao.searchByName(s);
    }
    public Posting getPostingByID(String id){
        return gDao.getPostingByID(id);
    }
    public int deletePosting(String id){
       return gDao.deletePosting(id);
    }
    public int updatePosting(String id,Posting posting){
        return gDao.updatePosting(id,posting);
    }

    public boolean validateUserEntry(String groupID,String entered_password){
        return gDao.validateUserEntry(groupID,entered_password);
    }
    public int removeUserByID(String groupID,String userID){
     return gDao.removeUserByID(groupID,userID);
    }

}
