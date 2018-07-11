package com.zs.base;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018-07-11 13:48
 *
 * @author zhshuo
 */
public class BaseCURDTest {

    private static Logger logger = LoggerFactory.getLogger(BaseCURDTest.class);

    private MongoClient mongoClient;

    private MongoDatabase mongoDatabase;

    private MongoCollection<Document> collection;

    @Before
    public void init(){
        mongoClient = MongoClients.create(new ConnectionString("mongodb://192.168.2.122:27022"));
        mongoDatabase = mongoClient.getDatabase("zhshuo");
        collection = mongoDatabase.getCollection("user");
    }

    @Test
    public void insert(){
        Document document = new Document();
        document.append("name","wangwu").append("age","120");

        Map map = new HashMap();
        map.put("color", Arrays.asList("red","yellow"));
        document.append("favorties",map);

        collection.insertOne(document);
    }

    @Test
    public void query(){
        FindIterable<Document> documents = collection.find();
        documents.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document);
            }
        });
    }

    @Test
    public void queryAnd(){
        FindIterable<Document> documents = collection.find(Filters.and(Filters.eq("name", "zhshuo"), Filters.eq("age", new BigDecimal(22))));
        documents.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document);
            }
        });
    }

    @Test
    public void queryOr(){
        FindIterable<Document> documents = collection.find(Filters.or(Filters.eq("name", "zhshuo"), Filters.eq("age", "99")));
        documents.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document);
            }
        });
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

    @Test
    public void updateRegex(){
        String regex = "s";

        FindIterable<Document> name = collection.find(Filters.regex("name", regex));
        name.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document);
            }
        });
    }

}
