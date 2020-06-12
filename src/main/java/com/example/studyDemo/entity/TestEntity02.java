package com.example.studyDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-05-19
 * @since 1.0.0
 */
@Entity
@Table(name = "study_test02")
public class TestEntity02 extends UuidEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}