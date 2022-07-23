package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.dto.MenuIndexDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DaoTest {

    @Autowired
    protected MenuDao menuDao;

    @Test
    public void should_return_MenuIndexDto(){
        List<MenuIndexDto> menuIndexDtoList = menuDao.listByUserId(1);
        menuIndexDtoList.forEach(System.out::println);
    }
}
