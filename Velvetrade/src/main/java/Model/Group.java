package Model;

import java.util.UUID;

public class Group {
    private String id;
    private String name;
    private String password;
    private boolean isPrivate;
    private String description;

    public Group(String name, String password, boolean isPrivate, String description) {
        this.name = name;
        this.password = password;
        this.isPrivate = isPrivate;
        this.description = description;
        id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Group(String id, String name, String password, boolean isPrivate, String description) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isPrivate = isPrivate;

        this.description = description;
    }
}
