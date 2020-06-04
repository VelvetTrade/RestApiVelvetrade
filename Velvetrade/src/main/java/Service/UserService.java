package Service;

import DAO.FakeUserDAO;
import Model.Posting;
import Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserService {
    FakeUserDAO fDAO;

    @Autowired
    public UserService(@Qualifier("UserDAO") FakeUserDAO fDAO) {
        this.fDAO = fDAO;

    }


    public int addNewUser(User user) {
        return fDAO.addNewUser(user);

    }
    public List<Posting> getAllPostingsPerUser(String id){
        return fDAO.getAllPostingsPerUser(id);
    }

    public int updateUserByID(String id,User user) {
        return fDAO.updateUserByID(id,user);
    }


    public int deleteUserByID(String id) {
        return fDAO.deleteUserByID(id);
    }


    public User getUserByID(String id) {
        return fDAO.getUserByID(id);
    }


    public List<User> findUserByName(String name) {
        return fDAO.findUserByName(name);
    }
}

