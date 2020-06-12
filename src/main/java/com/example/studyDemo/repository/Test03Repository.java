package com.example.studyDemo.repository;

import com.example.studyDemo.entity.TestEntity;
import com.example.studyDemo.entity.TestEntity03;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-04-27
 * @since 1.0.0
 */
@Repository("Test03Repository")
public interface Test03Repository extends
        JpaRepository<TestEntity03, String>,
        JpaSpecificationExecutor<TestEntity03> {

    List<TestEntity03> findByName(String name);
}