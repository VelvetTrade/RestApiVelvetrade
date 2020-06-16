package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.common.hash.Hashing;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository("UserDAO")
public class FakeUserDAO implements UserDAO {
      @Override
    public User addNewUser(User user,String password) throws ExecutionException, InterruptedException, InvalidNewUserException {
        User u =new User(user.getId(),user.getUserName(),user.getEmail(),user.getState(),user.getStreetAddress(),user.getZip(),user.getTin(),user.isOnline(),user.getFriends(),user.getItemId(),user.getNotifications(),user.getGroups());
           if(!checkIfUserNameExists(user.getUserName())){
               throw new InvalidNewUserException();
           }

        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> ndoc = dbFirestore.collection("Users").document(user.getId()).set(u);
            createStorage(new Storage(u.getId(), Hashing.sha256().hashString(password,StandardCharsets.US_ASCII).toString(),u.getUserName()));

        }catch (Exception e){
            return null;
        }
        return u;
    }

    @Override
    public List<Posting> getAllPostingsPerUser(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        User u=getUserByID(id);
        List<Posting> l= new ArrayList<>();
        for(String ids:u.getGroups()){
            l.addAll( dbFirestore.collection("Groups").document(ids).collection("Postings").
                    whereEqualTo("userId",id).get().get().toObjects(Posting.class));
        }
        return l;
    }
    @Override
    public User authenticateUser(String username, String password) throws ExecutionException, InterruptedException, AuthIsIncorrect {
        List<User> users=findUserByName(username);
       User user=users.get(0);
       String p= Hashing.sha256().hashString(password, StandardCharsets.US_ASCII).toString();
       String pa= (String) FirestoreClient.getFirestore().collection("uStore").document(user.getId()).get().get().get("p");
      if(pa==null&&!p.equals(pa)){
          throw new AuthIsIncorrect();
      }
        return user;
    }

    @Override
    public int updateUserByID(String id,User user) {
        try {
            User u= getUserByID(id);
            User e= new User(id,(user.getUserName()==null?u.getUserName():user.getUserName()),
                    (user.getEmail()==null?u.getEmail():user.getEmail()),
                    (user.getState()==null?u.getState():user.getState()),
                    (user.getStreetAddress()==null?u.getStreetAddress():user.getStreetAddress()),
                    (user.getZip()==0?u.getZip():user.getZip()),
                    (user.getTin()==null?u.getTin():user.getTin()),user.isOnline(),
                    user.getFriends(),user.getItemId(),
                    user.getNotifications(),user.getGroups());
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> ndoc = dbFirestore.collection("Users").document(id).set(e);

        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteUserByID(String id) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> ndoc = dbFirestore.collection("Users").document(id).delete();
        }catch (Exception e){
            return 0;
        }

        return 1;
    }

    @Override
    public User getUserByID(String id) {
        DocumentReference dr=FirestoreClient.getFirestore().collection("Users").document(id);
        ApiFuture<DocumentSnapshot> future = dr.get();
        try {
            DocumentSnapshot ds=future.get();
            if(ds.exists()){
                return ds.toObject(User.class);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findUserByName(String name) {
        CollectionReference cr=FirestoreClient.getFirestore().collection("Users");
        Query q= cr.whereEqualTo("userName",name);
        ApiFuture<QuerySnapshot> a = q.get();
        List<User> users=new ArrayList<>();
        try {
            for(DocumentSnapshot ds:a.get().getDocuments()){
                users.add(ds.toObject(User.class));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return users;
    }
    public void createStorage(Storage s){
        FirestoreClient.getFirestore().collection("uStore").add(s);
    }
    @Override
    public boolean checkIfUserNameExists(String s) throws ExecutionException, InterruptedException {
        return  FirestoreClient.getFirestore().collection("uStore").whereEqualTo("name",s).get().get().size()>0;
    }
    @Override
    public List<User> getUsersByIDs(List<String> id) {
          List<User> u= new ArrayList<>();
          for(String i:id){
             u.add(getUserByID(i));

          }
          return u;
    }
}
