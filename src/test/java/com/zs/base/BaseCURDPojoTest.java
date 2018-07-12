package com.zs.base;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import com.zs.base.Modle.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created on 2018-07-11 13:48
 * emm
 * @author zhshuo
 */
public class BaseCURDPojoTest {

    private static Logger logger = LoggerFactory.getLogger(BaseCURDPojoTest.class);

    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    private MongoCollection<User> collection;

    @Before
    public void init(){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(com.mongodb.MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings build = MongoClientSettings.builder().codecRegistry(codecRegistry).build();

        mongoClient = MongoClients.create(new ConnectionString("mongodb://192.168.2.122:27022"));
        mongoDatabase = mongoClient.getDatabase("zhshuo").withCodecRegistry(codecRegistry);
        collection = mongoDatabase.getCollection("user",User.class);
    }

    @Test
    public void insert(){
        collection.insertOne(new User().setAge(22).setId(23).setName("fdfd"));
    }


    @Test
    public void updateOne(){
        UpdateResult updateResult = collection.updateOne(Filters.eq("name", "zhshuo"), new Document("$set", new Document("age", 99)));
        logger.error("符合条件数:"+updateResult.getMatchedCount());
        logger.error("修改数:"+updateResult.getModifiedCount());
        logger.error("修改得ID:"+updateResult.getUpsertedId());
    }

    @Test
    public void updateManey(){
        UpdateResult updateResult = collection.updateMany(Filters.eq("age", 99), new Document("$set", new Document("age", 88)));
        logger.error("符合条件数:"+updateResult.getMatchedCount());
        logger.error("修改数:"+updateResult.getModifiedCount());
        logger.error("修改得ID:"+updateResult.getUpsertedId());
    }


}
