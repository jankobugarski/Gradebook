package com.iktpreobuka.Gradebook.servicies;

import com.iktpreobuka.Gradebook.entities.User;
import com.iktpreobuka.Gradebook.repositories.AdminRepository;
import com.iktpreobuka.Gradebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<User> findUserByUsername(String username) {

//        String sql = "SELECT u from User u where u.username=:username";
        String sql="SELECT a FROM Admin a inner join fetch a.user u where u.username= :username";

        Query query=em.createQuery(sql);
        query.setParameter("username", username);
        List<User> queryResult=new ArrayList<>();
        queryResult=query.getResultList();
        return queryResult;
    }
}
