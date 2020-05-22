package DAO;

import Model.Group;
import Model.Posting;
import Model.User;

import java.util.List;

public interface PostingDAO {
    public List<Posting> getAllPostingsPerGroup(Group g);
    public List<Posting> getAllPostingsPerUser(User user);
    public Posting getPostingByID(String id);
    public int deletePosting(String id);
    public int updatePosting(String id,Posting posting);
    public void createPosting(Posting posting);
}

