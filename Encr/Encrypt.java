/**
 * @author Ishan Parikh
 * Purpose: this package holds encryption operations for the back-end
 *
 * I would recommend changing or randomizing the key every time you want to encrypt or decrypt just to stay random
 * 
 * Attach this where it is necessary, I would attach it to the main controller in my opinion,
 * there might be better options
 **/
package Encr;
import java.util.*;
import Model.Posting;
import Model.User;
import Service.GroupService;
import Service.UserService;
import javax.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Base64;
 import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
    private final GroupService groupService;
    private final UserService userService;
    private API api;
    private String username;
    private String password;
    private String id; //user id
    private String key;
    private SecretKeySpec speckey;

    public Encrypt(String username, String password, GroupService group, UserService user) {
        this.api = new API(group, user);
        this.username = username;
        this.password = password;
        this.id = null; //the id is set as null until it is confirmed
        this.groupService = group;
        this.userService = user;
    }
    public void setUserID(String id){
        this.id = id;
    }
    public void setKey(String key){
        this.key = key;
        this.speckey = new SecretKeySpec(key.getBytes().clone(),"AES");
    }
    
    public boolean passwordValid(){
        
        return decrypt(api.getUserByID(id).getUserName(), this.key) == this.username; //not sure how to access the password from the user account
    }
    
    public String encrypt(String input, String key) {
        try
        {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.speckey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            return null;
        }
    }
    
    public String decrypt(String input, String key) {
        try
        {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, this.speckey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(input)));
        } 
        catch (Exception e) 
        {
            return null;
        }
    }
   /*
    Would be useful if I could integrate API operations into this somehow
    That is if we want to get and commit stuff directly to the db
    */
    class API {
        private final GroupService groupService;
        private final UserService userService;
    
        API(GroupService groupService, UserService userService){
            this.groupService = groupService;
            this.userService = userService;
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
}