package com.Velvetrade.Velvetrade.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Posting {

    private String id;
    private String userId;

    //ids of all offers
    private ArrayList<String> offers;
    // will be blank if not accepted
    private String acceptedOfferID;
    //U.S dollars
    private double price;

    private String description;
    //can be blank if offer
    private String desiredItems;

    private String itemTitle;
    //if the posting is an offer instead of a listing
    private boolean isOffer;
    public Posting(){

    }

    public Posting(@JsonProperty("id") String id, @JsonProperty("offers") List<String> offers, @JsonProperty("userId")String userId, @JsonProperty("price") double price, @JsonProperty("description") String description, @JsonProperty("desiredItems") String desiredItems, @JsonProperty("itemTitle")String itemTitle, @JsonProperty("isOffer") boolean isOffer, @JsonProperty("acceptedOfferId")String acceptedOfferID) {
        if(id==null){
            id=UUID.randomUUID().toString();
        }
        if(offers==null){
            offers=new ArrayList<String>();
        }
        if(acceptedOfferID==null){
            acceptedOfferID="";
        }

        this.id = id;
        this.userId = userId;
        this.offers = (ArrayList<String>) offers;
        this.price = price;
        this.description = description;
        this.desiredItems = desiredItems;
        this.itemTitle = itemTitle;
        this.isOffer=isOffer;
        this.acceptedOfferID =acceptedOfferID;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public void setOffer(boolean offer) {
        isOffer = offer;
    }

//    public Posting(@JsonProperty("userId")String userId,@JsonProperty("price") double price,@JsonProperty("description") String description,@JsonProperty("desiredItems") String desiredItems, @JsonProperty("itemTitle")String itemTitle,@JsonProperty("isOffer") boolean isOffer) {
//        this.userId = userId;
//        id= UUID.randomUUID().toString();
//        this.offers = new ArrayList<>();
//        this.price = price;
//        this.description = description;
//        this.desiredItems = desiredItems;
//        this.itemTitle = itemTitle;
//        this.isOffer=isOffer;
//        acceptedOfferID ="";
//    }

    public String getAcceptedOfferID() {
        return acceptedOfferID;
    }

    public void setAcceptedOfferID(String acceptedOfferID) {
        this.acceptedOfferID = acceptedOfferID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<String> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<String> offers) {
        this.offers = offers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesiredItems() {
        return desiredItems;
    }

    public void setDesiredItems(String desiredItems) {
        this.desiredItems = desiredItems;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
