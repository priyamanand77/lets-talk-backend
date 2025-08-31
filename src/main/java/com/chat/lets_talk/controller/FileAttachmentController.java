package com.chat.lets_talk.controller;

import com.chat.lets_talk.entity.FileAttachment;
import com.chat.lets_talk.repo.FileAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/attachments")
public class FileAttachmentController {

    @Autowired
    private FileAttachmentRepository repository;

    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getSize() > 30 * 1024 * 1024) { // 30MB
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(Map.of("message","File size exceeds 30MB"));
        }
        FileAttachment attachment = new FileAttachment();
        attachment.setFilename(file.getOriginalFilename());
        attachment.setFileData(Base64.getEncoder().encodeToString(file.getBytes()));
        FileAttachment saved = repository.save(attachment);
        return ResponseEntity.ok( Map.of("id",saved.getId()));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<?> downloadFile(@PathVariable String id) {
        Optional<FileAttachment> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        FileAttachment attachment = optional.get();
        byte[] fileBytes = Base64.getDecoder().decode(attachment.getFileData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileBytes);
    }
}

