package DAO;

import Model.Chat;
import Model.Group;
import Model.Posting;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("GroupDAO")
public class FakeGroupDAO implements ChatDAO, GroupDAO, PostingDAO {


    @Override
    public void updateChat(String id, Chat chat) {

    }

    @Override
    public Chat getChat(String id) {
        return null;
    }

    @Override
    public Chat getChatByGroup(String groupId) {
        return null;
    }


    @Override
    public List<Group> searchByName(String s) {
        return null;
    }

    @Override
    public Group getGroupByID(String id) {
        return null;
    }

    @Override
    public int updateGroupByID(String id, Group group) {
        return 0;
    }

    @Override
    public int deleteGroupByID(String id) {
        return 0;
    }

    @Override
    public int createGroup(Group group) {
        return 0;
    }

    @Override
    public boolean validateUserEntry(String groupID, String entered_password) {
        return false;
    }

    @Override
    public int removeUserByID(String groupID, String userID) {
        return 0;
    }

    @Override
    public List<Posting> getAllPostingsPerGroup(String id) {
        return null;
    }

    @Override
    public List<Posting> getAllPostingsPerUser(String id) {
        return null;
    }

    @Override
    public Posting getPostingByID(String id) {
        return null;
    }

    @Override
    public int deletePosting(String id) {
        return 0;
    }

    @Override
    public int updatePosting(String id, Posting posting) {
        return 0;
    }

    @Override
    public void createPosting(String groupID, Posting posting) {

    }
}
