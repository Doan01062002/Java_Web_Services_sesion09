package com.example.session09.service;

import com.example.session09.exception.UserNotFoundException;
import com.example.session09.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static final Map<Long, User> userMap = new HashMap<>();

    static {
        userMap.put(1L, new User(1L, "Nguyễn Văn A"));
        userMap.put(2L, new User(2L, "Trần Thị B"));
    }

    public User getUserById(Long id) {
        if (!userMap.containsKey(id)) {
            throw new UserNotFoundException("Không tìm thấy user với ID: " + id);
        }
        return userMap.get(id);
    }
}
