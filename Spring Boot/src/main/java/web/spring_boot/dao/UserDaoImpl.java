package web.spring_boot.dao;

import org.springframework.stereotype.Repository;
import web.spring_boot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(int id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = em.createQuery("from User", User.class).getResultList();

        return userList == null ? new ArrayList<>() : userList;
    }

    @Override
    public void updateUser(User editedUser, int id) {
        User persistedUser = em.find(User.class, id);
        persistedUser.setName(editedUser.getName());
        persistedUser.setLastName(editedUser.getLastName());
        persistedUser.setAge(editedUser.getAge());

    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }
}
