package com.Velvetrade.Velvetrade.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String id;
    //identifier cannot be similar to other users
    private String userName;
    //can be blank
    private String email;

    private String password;
    //the following can be blank if the user hasn't put in the info
    private String state;
    private String streetAddress;
    private int zip;
    //must be encrypted and used for tax filing
    private String tin;
    //may not be used
    private boolean online;
    //list friend ids

    private ArrayList<String> friends;
    //list of all offers and listing
    private ArrayList<String> itemId;
    //may be used to show a trade was made
    private ArrayList<String> notifications;
    //All group ids
    private ArrayList<String> groups;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

//    public User(@JsonProperty("username") String userName,@JsonProperty("password") String password ,@JsonProperty("email") String email, @JsonProperty("state") String state, @JsonProperty("streetAddress") String streetAddress, @JsonProperty("zip") int zip, @JsonProperty("tin") String tin, @JsonProperty("online") boolean online) {
//        this.userName = userName;
//        this.email = email;
//        this.state = state;
//        this.password=password;
//        this.streetAddress = streetAddress;
//        this.zip = zip;
//        this.tin = tin;
//        this.online = online;
//        this.notifications = new ArrayList<>();
//        this.friends = new ArrayList<>();
//        this.itemId = new ArrayList<>();
//        this.groups = new ArrayList<>();
//        id = UUID.randomUUID().toString();
//    }

    public User() {

    }

    public User(@JsonProperty("id") String id,
                @JsonProperty("username") String userName,
                @JsonProperty("password") String password ,
                @JsonProperty("email") String email,
                @JsonProperty("state") String state,
                @JsonProperty("streetAddress") String streetAddress,
                @JsonProperty("zip") int zip,
                @JsonProperty("tin") String tin,
                @JsonProperty("online") boolean online,
                @JsonProperty("friends") ArrayList<String> friends,
                @JsonProperty("items") ArrayList<String> itemId,
                @JsonProperty("notifications") ArrayList<String> notifications,
                @JsonProperty("groups") ArrayList<String> groups) {
        if(id==null){
            id=UUID.randomUUID().toString();
        }
        if(groups==null){
            groups=new ArrayList<String>();
        }
        if(itemId==null){
            itemId=new ArrayList<String>();
        }
        if(friends==null){
            friends=new ArrayList<String>();
        }
        if(notifications==null){
            notifications=new ArrayList<String>();
        }
        

        this.id = id;
        this.online = online;
//        this.zip = zip;

//        this.email = email;
//        this.state = state;
//        this.streetAddress = streetAddress;
//        this.tin = tin;
        this.friends = friends;
        this.itemId = itemId;
        this.notifications = notifications;
       this.groups = groups;
//        this.password=password;
        //make sure to keep information if the user does not want to update
        if(userName!=null)
        {
            this.userName = userName;
        }
        if(password!=null)
        {
            this.password = password;
        }
        if(email!=null)
        {
            this.email = email;
        }
        if(state!=null)
        {
            this.state = state;
        }
        if(streetAddress!=null)
        {
            this.streetAddress = streetAddress;
        }
        System.out.println("the current zip is: " + zip);
        if(zip == 0)
        {
            this.zip = zip;
        }
        if(tin!=null);
        {
            this.tin = tin;
        }
//        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getItemId() {
        return itemId;
    }

    public void setItemId(ArrayList<String> itemId) {
        this.itemId = itemId;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<String> notifications) {
        this.notifications = notifications;
    }
}

