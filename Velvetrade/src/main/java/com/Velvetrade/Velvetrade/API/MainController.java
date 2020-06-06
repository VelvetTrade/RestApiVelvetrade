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
import javax.validation.Valid;
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
    public void createGroup(@Valid @NonNull @RequestBody Group group)
    {
        System.out.println("/Called Create Group");
        groupS.createGroup(group);
    }
    @PutMapping(path = "/update/{groupId}")
    public void updateGroupById(@PathVariable("groupId") String id, @Valid @NonNull @RequestBody Group g)
    {
        groupS.updateGroupByID(id,g);
    }
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
    public void updateChatById(@PathVariable("chat") String id,@Valid@Nonnull @RequestBody Chat c)
    {
        groupS.updateChatByID(id,c);
    }
    @PostMapping(path = "/createNewPosting")
    public void createNewPosting(@Valid @NonNull @RequestBody Posting posting)
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
    @GetMapping(path = "/getPostingById/{getPostingId}/message/{gPostingId}")
    public Posting getPostingById(@PathVariable("getPostingId") String id,@PathVariable("gPostingId") String postingId)
    {
        return groupS.getPostingByID(id,postingId);
    }
    @DeleteMapping(path = "/deletePosting/{deletePostingId}/message/{dPostingId}")
    public int deletePosting(@PathVariable("deletePostingId") String id,@PathVariable("dPostingId") String postingId)
    {
        return groupS.deletePosting(id,postingId);
    }
    @PutMapping(path = "/updatePosting/{updatePostingId}")
    public int updatePosting(@PathVariable("updatePostingId") String id,@NonNull @Valid @RequestBody Posting posting)
    {
        return groupS.updatePosting(id,posting);
    }
    @GetMapping(path = "/validateUserEntry/{validateId}/message/{validatePass}")
    public boolean validateUserEntry(@PathVariable("validateId") String groupID,@RequestBody @PathVariable("validatePass") String entered_password){
        return groupS.validateUserEntry(groupID,entered_password);
    }
    @DeleteMapping(path = "/removeUserById/{deleteUserId}/message/{dUserId}")
    public int removeUserById(@PathVariable("deleteUserId") String groupID,@PathVariable("dUserId") String userID){
        return groupS.removeUserByID(groupID,userID);
    }
    @PostMapping(path = "/addNewUser")
    public int addNewUser(@Valid @NonNull @RequestBody User user) {
        return userS.addNewUser(user);
    }
    @PutMapping(path = "/updateUserById/{updateUserId}")
    public int updateUserById(@PathVariable("updateUserId") String id,@Valid@NonNull@RequestBody User user) {
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
