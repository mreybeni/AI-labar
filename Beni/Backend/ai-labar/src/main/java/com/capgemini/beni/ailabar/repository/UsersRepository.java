package com.capgemini.beni.ailabar.repository;

import com.capgemini.beni.ailabar.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UsersRepository extends JpaRepository<UsersEntity, String> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UsersEntity u WHERE u.user = :user")
    Boolean existsByUser(@Param("user") String user);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UsersEntity u WHERE u.user = :user AND u.token = :token")
    Boolean existsByUserAndToken(@Param("user") String user, @Param("token") String token);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UsersEntity u WHERE u.email = :email")
    Boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM UsersEntity u WHERE u.user = :user")
    UsersEntity findByUser(@Param("user") String user);

    @Modifying
    @Query("DELETE FROM UsersEntity u WHERE u.user = :user")
    void deleteByUser(@Param("user") String user);

    @Query("SELECT u.email FROM UsersEntity u WHERE u.user IN :userList")
    List<String> getEmailsByUserList(@Param("userList") List<String> userList);
}
