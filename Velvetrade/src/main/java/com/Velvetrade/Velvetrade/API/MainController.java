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

@RequestMapping("/api/v1/trade")
@RestController
@CrossOrigin(origins = "*")
public class MainController {
    private final GroupService groupS;
    private final UserService userS;
    @Autowired
    public MainController(GroupService groupService, UserService userService){
        this.groupS = groupService;
        this.userS = userService;
    }
    @GetMapping(path = "/getGroup/{groupId}")
    public Group GetGroupById(@PathVariable("groupId") String groupId) throws IdNotFoundException {   System.out.println("Called Get Group");
        return groupS.getGroupByID(groupId);
    }
    @GetMapping(path = "/getGroups/{groupIds}")
    public ArrayList<Group> getAllGroupsFromIds( @PathVariable("groupIds") ArrayList<String> ids) throws IdNotFoundException {
        return groupS.getAllGroupsFromIds(ids);//maybe change the method in the service to return optional variable to add .orElse();
    }
    @DeleteMapping(path = "/deleteGroup/{groupId}")
    public void deleteGroupById(@PathVariable("groupId") String groupId)
    {
        groupS.deleteGroupByID(groupId);
    }
    @PostMapping(path = "/createGroup")
    public void createGroup( @NonNull @RequestBody Group group)
    {
        System.out.println("/Called Create Group");
        groupS.createGroup(group);
    }
    @PutMapping(path = "/update/{groupId}")
    public void updateGroupById(@PathVariable("groupId") String id, @NonNull @RequestBody Group g)
    {
        System.out.println("Updated "+ id);
        groupS.updateGroupByID(id,g);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping(path = "/getChat/{ChatGroupId}")
    public Chat getChatByGroupId(@PathVariable("ChatGroupId") String groupId)
    {
        return groupS.getChatByGroupId(groupId);
    }
    @GetMapping(path = "/getChat/{chat}")
    public Chat getChat(@PathVariable("chat") String id)
    {
        return groupS.getChat(id);
    }
    @PutMapping(path = "/updateChatById/{chat}")
    public void updateChatById(@PathVariable("chat") String id,@Nonnull @RequestBody Chat c)
    {
        groupS.updateChatByID(id,c);
    }

//////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping(path = "/createNewPosting/{groupId}")
    public void createNewPosting(@NonNull @PathVariable("groupId") String groupId,@NonNull @RequestBody Posting posting)
    {
        groupS.createNewPosting(groupId,posting);
    }
    @GetMapping(path = "/getAllPostingPerGroup/{PostGroupId}")
    public List<Posting> getAllPostingsPerGroup(@PathVariable("PostGroupId") String groupId)
    {
        return groupS.getAllPostingsPerGroup(groupId);
    }
    @GetMapping(path = "/searchByName/{searchName}")
    public List<Group> searchByName(@PathVariable("searchName") String name)
    {
        return groupS.searchByName(name);
    }
    @GetMapping(path = "/getPostingById/{groupId}/{gPostingId}")
    public Posting getPostingById(@PathVariable("groupId") String id,@PathVariable("gPostingId") String postingId) throws IdNotFoundException {
        return groupS.getPostingByID(id,postingId);
    }
    @DeleteMapping(path = "/deletePosting/{groupId}/{postingId}")
    public int deletePosting(@PathVariable("groupId") String id,@PathVariable("postingId") String postingId)
    {
        return groupS.deletePosting(id,postingId);
    }
    @PutMapping(path = "/updatePosting/{updatePostingId}")
    public int updatePosting(@PathVariable("updatePostingId") String id,@NonNull  @RequestBody Posting posting)
    {
        return groupS.updatePosting(id,posting);
    }
    @GetMapping(path = "/validateUserEntry/{groupId}/{userId}/{validatePass}")
    public boolean validateUserEntry(@PathVariable("groupId") String groupID,@PathVariable("userId") String userID, @PathVariable("validatePass") String entered_password) throws IdNotFoundException {

        boolean b= groupS.validateUserEntry(groupID,userID,entered_password);
        if(b){
        User u=getUserById(userID);
        u.getGroups().add(groupID);}
        return b;
    }

    @GetMapping(path = "/authenticateUser/{username}/{password}")
    public User authenticateUser(@PathVariable("username") String username, @PathVariable("password") String entered_password){
        System.out.println("reached");
        return userS.authenticateUser(username,entered_password);
    }

    @DeleteMapping(path = "/removeUserFromGroupById/{groupId}/{UserId}")
    public int removeUserById(@PathVariable("groupId") String groupID,@PathVariable("UserId") String userID) throws IdNotFoundException {
        return groupS.removeUserByID(groupID,userID);
    }
    @PostMapping(path = "/addNewUser")
    public int addNewUser( @NonNull @RequestBody User user) throws  InvalidNewUserException {
        if(user.getPassword()!=null&&!user.getPassword().equals("")&&user.getUserName()!=null&& !user.getUserName().equals("")){
        return userS.addNewUser(user);}else{
            throw new InvalidNewUserException();
        }
    }

    @PutMapping(path = "/updateUserById/{updateUserId}")
    public int updateUserById(@PathVariable("updateUserId") String id,@NonNull@RequestBody User user) {

        return userS.updateUserByID(id,user);
    }
    @GetMapping(path = "/getAllPostingsPerUser/{allPostingUserId}")
    public List<Posting> getAllPostingsPerUser(@PathVariable("allPostingUserId") String userID){
        return userS.getAllPostingsPerUser(userID);
    }
    @DeleteMapping(path = "/deleteUserById/{deleteUserId}")
    public int deleteUserById(@PathVariable("deleteUserId") String id) {
        return userS.deleteUserByID(id);
    }

    @GetMapping(path = "/getUserById/{getUserId}")
    public User getUserById(@PathVariable("getUserId") String id) throws IdNotFoundException {
        return userS.getUserByID(id);
    }


    @GetMapping(path = "/findUserByName/{UserName}")
    public List<User> findUserByName(@PathVariable("UserName") String name) {
        return userS.findUserByName(name);
    }
}
