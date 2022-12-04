package com.quick.start.code.snippets.service;

import com.quick.start.code.snippets.dataobject.UserDo;
import com.quick.start.code.snippets.dao.UserDao;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public UserDo get(Integer id) {
        return userDao.selectById(id);
    }

    public Integer addUser(String username) {
        System.out.println("user dao adduser [username=" + username + "]");
        if (username == null) {
            return 0;
        }
        return 1;
    }

    public Integer testNotNull(@NonNull String username) {
        return 1;
    }

    /**
     * 这个注解会使用try-catch包装代码
     * @return int
     */
    @SneakyThrows(Exception.class)
    public Integer testException() {
        return 1;
    }

    /**
     * `@Synchronized`
     * 这个注解用在类方法或者实例方法上，效果和synchronized关键字相同，区别在于锁对象不同，对于类方法和实例方法，synchronized关键字的锁对象分别是类的class对象和this对象，
     * 而@Synchronized的锁对象分别是私有静态final对象LOCK和私有final对象lock，当然，也可以自己指定锁对象
     */
    private final Object readLock = new Object();

    @Synchronized
    public static void hello() {
        System.out.println("world");
    }

    @Synchronized
    public int answerToLife() {
        return 42;
    }

    @Synchronized("readLock")
    public void foo() {
        System.out.println("bar");
    }
}
