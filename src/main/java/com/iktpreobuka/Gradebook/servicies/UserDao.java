package com.iktpreobuka.Gradebook.servicies;

import com.iktpreobuka.Gradebook.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserDao {
    public List<User> findUserByUsername(String username);
}
