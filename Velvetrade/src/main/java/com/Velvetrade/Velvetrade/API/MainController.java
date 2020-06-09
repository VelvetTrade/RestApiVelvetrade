package com.Velvetrade.Velvetrade.API;

import com.Velvetrade.Velvetrade.Model.Chat;
import com.Velvetrade.Velvetrade.Model.Group;
import com.Velvetrade.Velvetrade.Model.Posting;
import com.Velvetrade.Velvetrade.Model.User;
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
public class MainController {
    private final GroupService groupS;
    private final UserService userS;
    @Autowired
    public MainController(GroupService groupService, UserService userService){
        this.groupS = groupService;
        this.userS = userService;
    }
    @GetMapping(path = "/getGroup/{groupId}")
    public Group GetGroupById(@PathVariable("groupId") String groupId)
    {   System.out.println("Called Get Group");
        return groupS.getGroupByID(groupId);
    }
    @GetMapping(path = "/getGroups/{groupIds}")
    public ArrayList<Group> getAllGroupsFromIds( @PathVariable("groupIds") ArrayList<String> ids)
    {
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
    public void updateGroupById(@PathVariable("groupId") String id,  @NonNull @RequestBody Group g)
    {
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
    @PostMapping(path = "/createNewPosting")
    public void createNewPosting(@NonNull @RequestBody Posting posting)
    {
        groupS.createNewPosting(posting.getId(),posting);
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
    public Posting getPostingById(@PathVariable("groupId") String id,@PathVariable("gPostingId") String postingId)
    {
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
    @GetMapping(path = "/validateUserEntry/{validateId}/{validatePass}")
    public boolean validateUserEntry(@PathVariable("validateId") String groupID, @PathVariable("validatePass") String entered_password){
        return groupS.validateUserEntry(groupID,entered_password);
    }
    @DeleteMapping(path = "/removeUserFromGroupById/{groupId}/{UserId}")
    public int removeUserById(@PathVariable("groupId") String groupID,@PathVariable("UserId") String userID){
        return groupS.removeUserByID(groupID,userID);
    }
    @PostMapping(path = "/addNewUser")
    public int addNewUser( @NonNull @RequestBody User user) {
        return userS.addNewUser(user);
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
    public User getUserById(@PathVariable("getUserId") String id) {
        return userS.getUserByID(id);
    }
    @GetMapping(path = "/findUserByName/{UserName}")
    public List<User> findUserByName(@PathVariable("UserName") String name) {
        return userS.findUserByName(name);
    }
}
