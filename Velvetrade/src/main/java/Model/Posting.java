package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Posting {

    private String id;
    private String userId;
    private ArrayList<String> offers;
    private double price;
    private String description;
    private String desiredItems;
    private String itemTitle;

    public Posting(String id, String userId, ArrayList<String> offers, double price, String description, String desiredItems, String itemTitle) {
        this.id = id;
        this.userId = userId;
        this.offers = offers;
        this.price = price;
        this.description = description;
        this.desiredItems = desiredItems;
        this.itemTitle = itemTitle;
    }

    public Posting(String userId, double price, String description, String desiredItems, String itemTitle) {
        this.userId = userId;
        id= UUID.randomUUID().toString();
        this.offers = new ArrayList<>();
        this.price = price;
        this.description = description;
        this.desiredItems = desiredItems;
        this.itemTitle = itemTitle;
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
