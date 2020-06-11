package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.IdNotFoundException;
import com.Velvetrade.Velvetrade.Model.Posting;

import java.util.List;

public interface PostingDAO {
    public List<Posting> getAllPostingsPerGroup(String id);

    public Posting getPostingByID(String groupId,String postingId) throws IdNotFoundException;
    public int deletePosting(String groupId,String postingId);
    public int updatePosting(String id,Posting posting);
    public void createPosting(String groupID,Posting posting);
}

