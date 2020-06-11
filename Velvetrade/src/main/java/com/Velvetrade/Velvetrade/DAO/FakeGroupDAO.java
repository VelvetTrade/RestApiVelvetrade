package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.Chat;
import com.Velvetrade.Velvetrade.Model.Group;
import com.Velvetrade.Velvetrade.Model.Posting;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository("GroupDAO")
public class FakeGroupDAO implements ChatDAO, GroupDAO, PostingDAO {
String current = "";
    @Override
    public void updateChat(String id, Chat chat) {
        try{
            Chat c = getChatByGroup(id);
            Chat e = new Chat(id,(chat.getUserNames()==null?c.getUserNames():chat.getUserNames()),
                    (chat.getMessages()==null?c.getMessages():chat.getMessages()));
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> ndoc = dbFirestore.collection("Groups").document(id).collection("Chat").document().set(e);
        }catch(Exception e)
        {

        }
       // FirestoreClient.getFirestore().collection("Groups").document(id).collection("Chat").document().set(chat);

    }

    @Override
    public Chat getChat(String id) {
        ApiFuture<DocumentSnapshot> ds = FirestoreClient.getFirestore().collection("Groups").document(id).collection("Chat").document().get();
        try {
            return ds.get().toObject(Chat.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Chat getChatByGroup(String groupId) {
        ApiFuture<DocumentSnapshot> ds = FirestoreClient.getFirestore().collection("Groups").document(groupId).collection("Chat").document().get();
        try {
            return ds.get().toObject(Chat.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Group> searchByName(String s) {

        CollectionReference cr = FirestoreClient.getFirestore().collection("Groups");
        Query q = cr.whereEqualTo("name", s);
        ApiFuture<QuerySnapshot> a = q.get();
        List<Group> users = new ArrayList<>();
        try {
            for (DocumentSnapshot ds : a.get().getDocuments()) {
                users.add(ds.toObject(Group.class));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Group getGroupByID(String id) {
        ApiFuture<DocumentSnapshot> ds = FirestoreClient.getFirestore().collection("Groups").document(id).get();
        try {
            return ds.get().toObject(Group.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateGroupByID(String id, Group group) {
        try{
            Group g = getGroupByID(id);
            Group e = new Group(id,(group.getName()==null?g.getName():group.getName()),
                    (group.getPassword()==null?g.getPassword():group.getPassword()),
                    group.isPrivate(),(group.getDescription()==null?g.getPassword():group.getPassword()),
                    (group.getMembers()==null?g.getMembers():group.getMembers()));
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> ndoc = dbFirestore.collection("Groups").document(id).set(e);
        }catch(Exception e)
        {
            return 0;
        }
       // ApiFuture<WriteResult> ds = FirestoreClient.getFirestore().collection("Groups").document(id).set(group);
        return 1;
    }

    @Override
    public int deleteGroupByID(String id) {
        ApiFuture<WriteResult> ds = FirestoreClient.getFirestore().collection("Groups").document(id).delete();
        return ds.isDone() ? 1 : 0;
    }

    @Override
    public Group createGroup(String userId, Group group) {
        Group gr= new Group(group.getId(),group.getName(),group.getPassword(),group.isPrivate(),group.getDescription(),group.getMembers());
        gr.getMembers().add(userId);
        ApiFuture<WriteResult> ds = FirestoreClient.getFirestore().collection("Groups").document(gr.getId()).set(gr);
        FirestoreClient.getFirestore().collection("Groups").document(group.getId()).collection("Chat").document().set(new Chat());

        return gr;
    }

    @Override
    public boolean validateUserEntry(String groupID,String userId ,String entered_password) {
        Group g=getGroupByID(groupID);
        if(g.getPassword()==entered_password){
            g.getMembers().add(userId);
            updateGroupByID(groupID,g);}
        return g.getPassword()==entered_password;
    }

    @Override
    public int removeUserByID(String groupID, String userID) {
        Group g = getGroupByID(groupID);
        boolean b = g.getMembers().remove(userID);
        updateGroupByID(groupID, g);
        return b ? 1 : 0;

    }

    @Override
    public List<Posting> getAllPostingsPerGroup(String id) {
        Iterable<DocumentReference> ds = FirestoreClient.getFirestore().collection("Groups").document(id).collection("Postings").listDocuments();
        ArrayList<Posting> a = new ArrayList();
        for (DocumentReference dr : ds) {
            try {
             //  if((dr.get().get().getString("acceptedOfferID")==null||dr.get().get().getString("acceptedOfferID").equals(""))&&dr.get().get().getBoolean("isOffer")==false){
                a.add(dr.get().get().toObject(Posting.class));//}
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return a;
    }


    @Override
    public Posting getPostingByID(String id,String postingId) {

        try {
            return FirestoreClient.getFirestore().collection("Groups").document(id).collection("Postings").document(postingId).get().get().toObject(Posting.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public int deletePosting(String groupId,String postingId) {

        FirestoreClient.getFirestore().collection("Groups").document(groupId).collection("Postings").document(postingId).delete();

        return 0;

    }

    @Override
    public int updatePosting(String groupId, Posting posting) {
        try{
            current = getGroupByID(groupId).getId();
            Posting p = getPostingByID(groupId,current);
            Posting e = new Posting(posting.getId(),posting.getOffers(),posting.getUserId(),(posting.getPrice()==0?p.getPrice():posting.getPrice()),(posting.getDescription()==null?p.getDescription():posting.getDescription()),(posting.getDesiredItems()==null?p.getDesiredItems():posting.getDesiredItems()), (posting.getItemTitle()==null?p.getItemTitle():posting.getItemTitle()), posting.isOffer(), posting.getAcceptedOfferID());
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> ndoc = dbFirestore.collection("Groups").document(groupId).collection("Postings").document(posting.getId()).set(e);
        }catch(Exception e)
        {
            return 0;
        }
        //FirestoreClient.getFirestore().collection("Groups").document(groupId).collection("Postings").document(posting.getId()).set(posting);

        return 1;
    }

    @Override
    public Posting createPosting(String groupId, Posting posting) {
        Posting p= new Posting(posting.getId(),posting.getOffers(),posting.getUserId(),posting.getPrice(),posting.getDescription(),posting.getDesiredItems(),posting.getItemTitle(),posting.isOffer(),posting.getAcceptedOfferID());
        FirestoreClient.getFirestore().collection("Groups").document(groupId).collection("Postings").document(posting.getId()).set(p);
        return p;
    }

    public List<Posting> getPostingsByIDs(String sid, List<String> postingId) {
        List<Posting> p= new ArrayList<Posting>();

        for(String id:postingId){
           p.add(getPostingByID(sid,id));
        }
        return p;

    }
}
