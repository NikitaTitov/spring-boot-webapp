package app.dao;


import app.model.User;

public interface UserDao extends GenericDao<Long, User> {
    User getUserByName(String name);
}
