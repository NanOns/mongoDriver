package com.zs.base;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zs.base.Modle.User;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018-07-11 13:48
 * emm
 * @author zhshuo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-mongodb.xml")
public class BaseCURDSpringTest {

    @Resource
    private MongoOperations mongoOperations;

    private static Logger logger = LoggerFactory.getLogger(BaseCURDSpringTest.class);

    @Test
    public void insert(){
        mongoOperations.insert(new User().setAge(20).setId(1).setName("zdsd"));
    }

    @Test
    public void query(){
        List<User> users = mongoOperations.find(Query.query(Criteria.where("name").is("zdsd")), User.class);
        System.out.println(users);
    }

    @Test
    public void update(){
        UpdateResult updateResult = mongoOperations.updateMulti(Query.query(Criteria.where("age").is(20)), Update.update("name", "hhh"), User.class);
        System.out.println(updateResult.getModifiedCount());
    }

    @Test
    public void delete(){
        DeleteResult age = mongoOperations.remove(Query.query(Criteria.where("age").is(20)), User.class);
        System.out.println(age.getDeletedCount());
    }

}
