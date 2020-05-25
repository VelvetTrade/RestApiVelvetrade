package DAO;

import Model.Posting;

import java.util.List;

public interface PostingDAO {
    public List<Posting> getAllPostingsPerGroup(String id);
    public List<Posting> getAllPostingsPerUser(String id);
    public Posting getPostingByID(String id);
    public int deletePosting(String id);
    public int updatePosting(String id,Posting posting);
    public void createPosting(String groupID,Posting posting);
}

