package web.spring_boot.dao;

import org.springframework.stereotype.Repository;
import web.spring_boot.model.User;
import web.spring_boot.utill.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(int id) {
        Optional<User> userRemove = Optional.ofNullable(em.find(User.class, id));
        if (userRemove.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
        em.remove(userRemove.get());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = em.createQuery("from User", User.class).getResultList();

        return userList == null ? new ArrayList<>() : userList;
    }

    @Override
    public void updateUser(User editedUser, int id) {
        Optional<User> userUpdate = Optional.ofNullable(em.find(User.class, id));
        if (userUpdate.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
        User persistedUser = userUpdate.get();
        persistedUser.setName(editedUser.getName());
        persistedUser.setLastName(editedUser.getLastName());
        persistedUser.setAge(editedUser.getAge());

    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = Optional.ofNullable(em.find(User.class, id));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
        return user.get();
    }
}
