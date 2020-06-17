package com.Velvetrade.Velvetrade.API;

import com.Velvetrade.Velvetrade.Model.*;
import com.Velvetrade.Velvetrade.Service.GroupService;
import com.Velvetrade.Velvetrade.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/trade")
@RestController
public class MainController {
    private final GroupService groupS;
    private final UserService userS;
    @Autowired
    public MainController(GroupService groupService, UserService userService){
        this.groupS = groupService;
        this.userS = userService;
    }
    @CrossOrigin
    @GetMapping(path = "/getGroup/{groupId}")
    public Group GetGroupById(@PathVariable("groupId") String groupId) throws IdNotFoundException {   System.out.println("Called Get Group");
        return groupS.getGroupByID(groupId);
    }
    @CrossOrigin
    @GetMapping(path = "/getGroups/{groupIds}")
    public ArrayList<Group> getAllGroupsFromIds( @PathVariable("groupIds") ArrayList<String> ids) throws IdNotFoundException {
        return groupS.getAllGroupsFromIds(ids);//maybe change the method in the service to return optional variable to add .orElse();
    }
    @CrossOrigin
    @DeleteMapping(path = "/deleteGroup/{groupId}")
    public void deleteGroupById(@PathVariable("groupId") String groupId)
    {
        groupS.deleteGroupByID(groupId);
    }
    @CrossOrigin
    @PostMapping(path = "/createGroup/{userId}/{password}")
    public Group createGroup(@PathVariable("userId") String userId,@NonNull @RequestBody Group group,@PathVariable("password") String password) throws IdNotFoundException, InterruptedException, ExecutionException, InvalidNewUserException {
        System.out.println("/Called Create Group");
        User u=getUserById(userId);
       Group g=groupS.createGroup(userId,group,password);
        u.getGroups().add(g.getId());
      return g;
    }
    @PostMapping(path = "/createGroup/{userId}/")
    public Group createGroup(@PathVariable("userId") String userId,@NonNull @RequestBody Group group) throws IdNotFoundException, InterruptedException, ExecutionException, InvalidNewUserException {
        System.out.println("/Called Create Group");
        User u=getUserById(userId);
        Group g=groupS.createGroup(userId,group,null);
        u.getGroups().add(g.getId());
        return g;
    }
    @CrossOrigin
    @PutMapping(path = "/update/{groupId}")
    public void updateGroupById(@PathVariable("groupId") String id, @NonNull @RequestBody Group g)
    {
        System.out.println("Updated "+ id);
        groupS.updateGroupByID(id,g);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @CrossOrigin
    @GetMapping(path = "/getChat/{ChatGroupId}")
    public Chat getChatByGroupId(@PathVariable("ChatGroupId") String groupId)
    {
        return groupS.getChatByGroupId(groupId);
    }
    @CrossOrigin
    @GetMapping(path = "/getChat/{chat}")
    public Chat getChat(@PathVariable("chat") String id)
    {
        return groupS.getChat(id);
    }

    @CrossOrigin
    @PutMapping(path = "/updateChatById/{chat}")
    public void updateChatById(@PathVariable("chat") String id,@Nonnull @RequestBody Chat c)
    {
        groupS.updateChatByID(id,c);
    }

//////////////////////////////////////////////////////////////////////////////////////////
    @CrossOrigin
    @PostMapping(path = "/createNewPosting/{groupId}")
    public Posting createNewPosting(@NonNull @PathVariable("groupId") String groupId, @NonNull @RequestBody Posting posting) throws IdNotFoundException {
        if (posting.isOffer()) {
        Posting p = getPostingById(groupId, posting.getAcceptedOfferID());
        p.getOffers().add(posting.getId());
        updatePosting(groupId,p);
    }
        return groupS.createNewPosting(groupId,posting);
    }

    @CrossOrigin
    @PostMapping(path = "/createNewUserPosting/{userId}")
    public Posting createNewUserPosting(@NonNull @PathVariable("userId") String userId, @NonNull @RequestBody Posting posting) throws IdNotFoundException {

        return userS.addNewPostingToUser(userId,posting);
    }
    @CrossOrigin
    @GetMapping(path = "/getAllPostingPerGroup/{PostGroupId}")
    public List<Posting> getAllPostingsPerGroup(@PathVariable("PostGroupId") String groupId)
    {

        return groupS.getAllPostingsPerGroup(groupId);
    }
    @CrossOrigin
    @GetMapping(path = "/searchByName/{searchName}")
    public List<Group> searchByName(@PathVariable("searchName") String name)
    {
        return groupS.searchByName(name);
    }
    @CrossOrigin
    @GetMapping(path = "/getPostingById/{groupId}/{gPostingId}")
    public Posting getPostingById(@PathVariable("groupId") String id,@PathVariable("gPostingId") String postingId) throws IdNotFoundException {
        return groupS.getPostingByID(id,postingId);
    }
    @CrossOrigin
    @GetMapping(path = "/getUserPostingById/{userId}/{uPostingId}")
    public Posting getUserPostingById(@PathVariable("userId") String id,@PathVariable("uPostingId") String postingId) throws IdNotFoundException, ExecutionException, InterruptedException {
        return userS.getUserPostingById(id,postingId);
    }
    @CrossOrigin
    @GetMapping(path = "/getUserPostingById/{userId}/{name}")
    public List<Posting> getUserPostingByName(@PathVariable("userId") String id,@PathVariable("name") String name) throws IdNotFoundException, ExecutionException, InterruptedException {
        return userS.getUserPostingByName(id,name);
    }


    @CrossOrigin
    @GetMapping(path = "/getPostingsByIds/{groupId}/{gPostingId}")
    public List<Posting> getPostingsByIds(@PathVariable("groupId") String id,@PathVariable("gPostingId") List<String> postingId) throws IdNotFoundException {
        return groupS.getPostingsByIDs(id,postingId);
    }

    @CrossOrigin
    @DeleteMapping(path = "/deletePosting/{groupId}/{postingId}")
    public int deletePosting(@PathVariable("groupId") String id,@PathVariable("postingId") String postingId)
    {
        return groupS.deletePosting(id,postingId);
    }
    @CrossOrigin
    @DeleteMapping(path = "/deleteUserPosting/{userId}/{postingId}")
    public void deleteUserPosting(@PathVariable("userId") String id,@PathVariable("postingId") String postingId)
    {
         userS.removeUserPostingById(id,postingId);
    }
    @CrossOrigin
    @PutMapping(path = "/updatePosting/{updatePostingId}")
    public int updatePosting(@PathVariable("updatePostingId") String id,@NonNull  @RequestBody Posting posting)
    {
        return groupS.updatePosting(id,posting);
    }
    @CrossOrigin
    @PutMapping(path = "/updateUserPosting/{userId}/{postingId}")
    public void updateUserPosting(@PathVariable("userId") String userid,@PathVariable("postingId") String postingId ,@NonNull  @RequestBody Posting posting) throws ExecutionException, InterruptedException {
        userS.updateUserPostingById(userid,postingId,posting);
    }

    @CrossOrigin
    @PostMapping(path = "/acceptTrade/{acceptGroupId}/{acceptPostingId}/{acceptOfferId}")
    public void acceptTrade(@NonNull @PathVariable("acceptGroupId") String groupId,
                            @NonNull @PathVariable("acceptPostingId") String postingId,
                            @NonNull @PathVariable("acceptOfferId") String offerId) throws IdNotFoundException
    {
        groupS.acceptTrade(groupId,postingId,offerId);
    }
    @CrossOrigin
    @GetMapping(path = "/validateUserEntry/{groupId}/{userId}/{validatePass}")
    public boolean validateUserEntry(@PathVariable("groupId") String groupID,@PathVariable("userId") String userID, @PathVariable("validatePass") String entered_password) throws IdNotFoundException, InvalidNewUserException, ExecutionException, InterruptedException {

        boolean b= groupS.validateUserEntry(groupID,userID,entered_password);
        if(b){
        User u=getUserById(userID);
        u.getGroups().add(groupID);
        updateUserById(userID,u);}else{throw new InvalidNewUserException();}
        return b;
    }
    @CrossOrigin
    @GetMapping(path = "/authenticateUser/{username}/{password}")
    public User authenticateUser(@PathVariable("username") String username, @PathVariable("password") String entered_password) throws InterruptedException, ExecutionException, AuthIsIncorrect {
        System.out.println("reached");
        return userS.authenticateUser(username,entered_password);
    }
    @CrossOrigin
    @DeleteMapping(path = "/removeUserFromGroupById/{groupId}/{UserId}")
    public int removeUserById(@PathVariable("groupId") String groupID,@PathVariable("UserId") String userID) throws IdNotFoundException {
        User u=getUserById(userID);
        u.getGroups().remove(groupID);
        updateUserById(u.getId(),u);
        return groupS.removeUserByID(groupID,userID);
    }
    @CrossOrigin
    @PostMapping(path = "/addNewUser/{password}")
    public User addNewUser( @NonNull @RequestBody User user,@PathVariable("password") String password) throws InvalidNewUserException, ExecutionException, InterruptedException {
        if(password!=null&&password.equals("")&&user.getUserName()!=null&& !user.getUserName().equals("")){
        return userS.addNewUser(user,password);}else{
            throw new InvalidNewUserException();
        }
    }
    @CrossOrigin
    @PutMapping(path = "/updateUserById/{updateUserId}")
    public int updateUserById(@PathVariable("updateUserId") String id,@NonNull@RequestBody User user) {

        return userS.updateUserByID(id,user);
    }
    @CrossOrigin
    @GetMapping(path = "/getAllPostingsPerUser/{allPostingUserId}")
    public List<Posting> getAllPostingsPerUser(@PathVariable("allPostingUserId") String userID) throws ExecutionException, InterruptedException {
        return userS.getAllPostingsPerUser(userID);
    }
    @CrossOrigin
    @DeleteMapping(path = "/deleteUserById/{deleteUserId}")
    public int deleteUserById(@PathVariable("deleteUserId") String id) {
        return userS.deleteUserByID(id);
    }

    @CrossOrigin
    @GetMapping(path = "/getUserById/{getUserId}")
    public User getUserById(@PathVariable("getUserId") String id) throws IdNotFoundException {
        return userS.getUserByID(id);
    }

    @CrossOrigin
    @GetMapping(path = "/getUsersByIds/{getUserId}")
    public List<User> getUserById(@PathVariable("getUserId") List<String> id) throws IdNotFoundException {
        return userS.getUsersByIDs(id);
    }

    @CrossOrigin
    @GetMapping(path = "/findUserByName/{UserName}")
    public List<User> findUserByName(@PathVariable("UserName") String name) {
        return userS.findUserByName(name);
    }
}
