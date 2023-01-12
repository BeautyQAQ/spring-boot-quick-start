package com.quick.start.middleware;

import com.quick.start.middleware.dao.UserDO;
import com.quick.start.middleware.repository.PageUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class PageUserRepositoryTest {

    @Resource
    private PageUserRepository pageUserRepository;

    @Test // 排序
    public void testFindAll() {
        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // 执行排序操作
        Iterable<UserDO> iterable = pageUserRepository.findAll(sort);
        // 打印
        iterable.forEach(System.out::println);
    }

    @Test // 分页
    public void testFindPage() {
        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // 创建分页条件
        Pageable pageable = PageRequest.of(1, 10, sort);
        // 执行分页操作
        Page<UserDO> page = pageUserRepository.findAll(pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

    @Test
    public void testFindByUsername() {
        UserDO user = pageUserRepository.findByUsername("haha2");
        System.out.println(user);
    }

    @Test
    public void testFindByCreateTimeAfter() {
        // 创建分页条件
        Pageable pageable = PageRequest.of(1, 10);
        // 执行分页操作  临时 Demo ，实际不建议这么写
        Date createTime = new Date(2018 - 1990, Calendar.FEBRUARY, 24);
        Page<UserDO> page = pageUserRepository.findByCreateTimeAfter(createTime, pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

    @Test
    public void testFindIdByUsername01() {
        UserDO user = pageUserRepository.findByUsername01("haha2");
        System.out.println(user);
    }

    @Test
    public void testFindIdByUsername02() {
        UserDO user = pageUserRepository.findByUsername02("haha2");
        System.out.println(user);
    }

    @Test
    public void testFindIdByUsername03() {
        UserDO user = pageUserRepository.findByUsername03("haha2");
        System.out.println(user);
    }

    @Test
    // 更新操作，需要在事务中。
    // 在单元测试中，事务默认回滚，所以可能这么测试，事务都不更新。
    @Transactional
    public void testUpdateUsernameById() {
        pageUserRepository.updateUsernameById(13, "haha");
    }
}
