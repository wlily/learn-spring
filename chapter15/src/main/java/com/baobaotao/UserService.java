package com.baobaotao;

import com.baobaotao.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static Map<String, User> userMap = new HashMap<String, User>();
    private int id = 0;

    public void createUser(User user) {
        id++;
        user.setUserId(String.valueOf(id));
        userMap.put(user.getUserId(), user);
        System.out.println("save user: " + user);
    }

    public User getUserById(String userId) {
        if(userMap.containsKey(userId)){
            return userMap.get(userId);
        }
        else{
            User user = new User();
            return user;
        }
    }
}
