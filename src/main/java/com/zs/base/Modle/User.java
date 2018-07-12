package com.zs.base.Modle;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created on 2018-07-12 17:13
 *
 * @author zhshuo
 */
@Document(collection = "user")
public class User {

    private long id;

    private String name;

    private Integer age;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
