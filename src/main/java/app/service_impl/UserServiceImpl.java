package app.service_impl;


import app.dao.UserDao;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getByKey(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public boolean addUser(User user) {
        boolean hasPersonInDb;
        try {
            userDao.getUserByName(user.getLogin());
            hasPersonInDb = false;
        } catch (NoResultException|EmptyResultDataAccessException e) {
            hasPersonInDb = true;
            userDao.saveEntity(user);
        }
        return hasPersonInDb;
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteByKey(user.getId());
    }
}
