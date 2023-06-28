package com.capgemini.beni.ailabar.repository;


import com.capgemini.beni.ailabar.entity.TopicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicsRepository extends JpaRepository<TopicsEntity, Integer> {
    @Query("SELECT t FROM TopicsEntity t WHERE t.author = :user OR t.members LIKE %:user%")
    List<TopicsEntity> findByUser(@Param("user") String user);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TopicsEntity t WHERE t.title = :title AND t.author = :author")
    Boolean existsByTitleAndAuthor(@Param("title") String title, @Param("author") String author);
}