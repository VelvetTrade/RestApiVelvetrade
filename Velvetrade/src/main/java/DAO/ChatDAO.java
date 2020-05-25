package DAO;

import Model.Chat;

public interface ChatDAO {

    public void updateChat(String id, Chat chat);
    public Chat getChat(String id);
    public Chat getChatByGroup(String groupId);


}
