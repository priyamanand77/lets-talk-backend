package com.chat.lets_talk.repo;

import com.chat.lets_talk.entity.FileAttachment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileAttachmentRepository extends MongoRepository<FileAttachment , String> {
}
