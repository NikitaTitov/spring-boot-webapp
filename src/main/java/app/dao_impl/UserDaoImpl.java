package app.dao_impl;


import app.dao.UserDao;
import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    @Override
    public User getUserByName(String name) {
        User user = (User) entityManager.createQuery("SELECT u from User u where u.login =:name").setParameter("name", name).getSingleResult();
        return user;
    }

}
