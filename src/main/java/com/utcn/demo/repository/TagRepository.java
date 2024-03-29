package com.utcn.demo.repository;

import com.utcn.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
    List<Tag> findTagsByQuestionsId(Long questionId);
}
