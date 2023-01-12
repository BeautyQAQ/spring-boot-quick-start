package com.quick.start.middleware.repository;

import com.quick.start.middleware.dao.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PageUserRepository extends PagingAndSortingRepository<UserDO, Integer> {
    UserDO findByUsername(String username);
    Page<UserDO> findByCreateTimeAfter(Date createTime, Pageable pageable);

    @Query("SELECT u FROM UserDO u WHERE u.username = ?1")
    UserDO findByUsername01(String username);

    @Query("SELECT u FROM UserDO u WHERE u.username = :username")
    UserDO findByUsername02(@Param("username") String username);

    @Query(value = "SELECT * FROM users u WHERE u.username = :username", nativeQuery = true)
    UserDO findByUsername03(@Param("username") String username);

    @Query("UPDATE UserDO  u SET u.username = :username WHERE u.id = :id")
    @Modifying
    int updateUsernameById(Integer id, String username);
}
