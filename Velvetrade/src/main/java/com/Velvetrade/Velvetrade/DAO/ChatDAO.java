package com.Velvetrade.Velvetrade.DAO;

import com.Velvetrade.Velvetrade.Model.Chat;

public interface ChatDAO {

    public void updateChat(String id, Chat chat);
    public Chat getChat(String id);
    public Chat getChatByGroup(String groupId);


}
