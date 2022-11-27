package com.quick.start.springmvc.service;

import com.quick.start.springmvc.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }

}
