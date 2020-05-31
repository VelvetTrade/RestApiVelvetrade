package com.example.Trade.API;
import com.example.Trade.Service.GroupService;
import com.example.Trade.Service.UserService;
import com.example.Trade.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/trade")
@RestController
public class MainController {
    private final GroupService groupService;
    private final UserService userService;
    @Autowired
    public MainController(GroupService groupService, UserService userService){
        this.groupService = groupService;
        this.userService = userService;
    }
    @GetMapping(path = "{groupId}")
    public Group GetGroupById(@PathVariable("groupId") String groupId)
    {
        return groupService.getGroupByID(groupId);
    }
    @GetMapping(path = "{groupIds}")
    public ArrayList<Group> getAllGroupsFromIds( @PathVariable("groupIds") ArrayList<String> ids)
    {
        return groupService.getAllGroupsFromIds(ids);//maybe change the method in the service to return optional variable to add .orElse();
    }
    @DeleteMapping(path = "{groupId}")
    public void deleteGroupById(@PathVariable("groupId") String groupId)
    {
        groupService.deleteGroupByID(groupId);
    }
    @PostMapping(path = "{groupId}")
    public void createGroup(@PathVariable("groupId") Group group)
    {
        groupService.createGroup(group);
    }
    @PutMapping(path = "{groupId}")
    public void updateGroupById(@PathVariable("groupId") String id, @Valid @NonNull @RequestBody Group g)
    {
        groupService.updateGroupByID(id,g);
    }
    @GetMapping(path = "{ChatGroupId}")
    public Chat getChatByGroupId(@PathVariable("ChatGroupId") String groupId)
    {
        return groupService.getChatByGroupId(groupId);
    }
    @GetMapping(path = "chat")
    public Chat getChat(@PathVariable("chat") String id)
    {
        return groupService.getChat(id);
    }
    @PutMapping(path = "chat")
    public void updateChatById(@PathVariable("chat") String id,@Valid@Nonnull @RequestBody Chat c)
    {
        groupService.updateChatByID(id,c);
    }
    @PostMapping(path = "{newPostingId}/{newPosting}")
    public void createNewPosting(@PathVariable("newPostingId") String postingId,@PathVariable("newPosting") Posting posting)
    {
        groupService.createNewPosting(postingId,posting);
    }
    @GetMapping(path = "PostGroupId")
    public List<Posting> getAllPostingsPerGroup(@PathVariable("PostGroupId") String groupId)
    {
        return groupService.getAllPostingsPerGroup(groupId);
    }
    @GetMapping(path = "searchName")
    public List<Group> searchByName(@PathVariable("searchName") String name)
    {
        return groupService.searchByName(name);
    }
    @GetMapping(path = "{getPostingId}/{gPostingId}")
    public Posting getPostingById(@PathVariable("getPostingId") String id,@PathVariable("gPostingId") String postingId)
    {
        return groupService.getPostingByID(id,postingId);
    }
    @DeleteMapping(path = "{deletePostingId}/{dPostingId}")
    public int deletePosting(@PathVariable("deletePostingId") String id,@PathVariable("dPostingId") String postingId)
    {
        return groupService.deletePosting(id,postingId);
    }
    @PutMapping(path = "{updatePostingId}")
    public int updatePosting(@PathVariable("updatePostingId") String id,@NonNull @Valid @RequestBody Posting posting)
    {
        return groupService.updatePosting(id,posting);
    }
    @GetMapping(path = "{validateId}/{validatePass}")
    public boolean validateUserEntry(@PathVariable("validateId") String groupID,@RequestBody @PathVariable("validatePass") String entered_password){
        return groupService.validateUserEntry(groupID,entered_password);
    }
    @DeleteMapping(path = "{deleteUserId}/{dUserId}")
    public int removeUserByID(@PathVariable("deleteUserId") String groupID,@PathVariable("dUserId") String userID){
        return groupService.removeUserByID(groupID,userID);
    }
    @PostMapping(path = "{newUser}")
    public int addNewUser(@PathVariable("newUser") User user) {
        return userService.addNewUser(user);
    }
    @PutMapping(path = "{updateUserId}")
    public int updateUserByID(@PathVariable("updateUserId") String id,@Valid@NonNull@RequestBody User user) {
        return userService.updateUserByID(id,user);
    }
    @GetMapping(path = "{allPostingUserId}")
    public List<Posting> getAllPostingsPerUser(@PathVariable("allPostingUserId") String userID){
        return userService.getAllPostingsPerUser(userID);
    }
    @DeleteMapping(path = "{deleteUserId}")
    public int deleteUserByID(@PathVariable("deleteUserId") String id) {
        return userService.deleteUserByID(id);
    }
    @GetMapping(path = "{getUserId}")
    public User getUserByID(@PathVariable("getUserId") String id) {
        return userService.getUserByID(id);
    }
    @GetMapping(path = "{UserName}")
    public List<User> findUserByName(@PathVariable("UserName") String name) {
        return userService.findUserByName(name);
    }
}

