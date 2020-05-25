package DAO;

import Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("UserDAO")
public class FakeUserDAO implements UserDAO {
    @Override
    public int addNewUser(User user) {
        return 0;
    }

    @Override
    public int updateUserByID(String id) {
        return 0;
    }

    @Override
    public int deleteUserByID(String id) {
        return 0;
    }

    @Override
    public User getUserByID(String id) {
        return null;
    }

    @Override
    public List<User> findUserByName(String name) {
        return null;
    }
}
