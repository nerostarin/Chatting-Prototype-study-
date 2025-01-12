package com.example.chatServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.chatServer.vo.MongoSequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceService {

    @Autowired
    private MongoTemplate mongoTemplate;
    public String getNextSequenceId(String seqName) {
        MongoSequence sequence = mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                MongoSequence.class
        );
        return String.valueOf(sequence.getSeq()); // 시퀀스를 문자열로 반환
    }
}
