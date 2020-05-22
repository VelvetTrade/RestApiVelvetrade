package DAO;

import Model.Chat;

public interface ChatDAO {
    public void deleteChat(String id);
    public void updateChat(String id, Chat chat);
    public Chat getChat(String id);
    public Chat getChatByGroup(String groupId);
    public void creatChat(Chat c);

}
