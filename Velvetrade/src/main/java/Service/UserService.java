package Service;

import DAO.FakeUserDAO;
import Model.Posting;
import Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    FakeUserDAO fDAO;

    @Autowired
    public UserService(@Qualifier("UserDAO") FakeUserDAO fDAO) {
        this.fDAO = fDAO;

    }

    //Adds a user
    public int addNewUser(User user) {
        return fDAO.addNewUser(user);

    }
    //gets all the postings of a user
    public List<Posting> getAllPostingsPerUser(String id){
        return fDAO.getAllPostingsPerUser(id);
    }


    //updates user by id
    public int updateUserByID(String id,User user) {
        return fDAO.updateUserByID(id,user);
    }

    //deletes user by id
    public int deleteUserByID(String id) {
        return fDAO.deleteUserByID(id);
    }

    //gets a user by id
    public User getUserByID(String id) {
        return fDAO.getUserByID(id);
    }

    //Can be used to searh for a user or sign in a user
    public List<User> findUserByName(String name) {
        return fDAO.findUserByName(name);
    }
}

