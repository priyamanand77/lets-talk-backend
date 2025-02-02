package com.chat.lets_talk.controller;

import com.chat.lets_talk.dto.message.MessageDto;
import com.chat.lets_talk.dto.response.SuccessResponse;
import com.chat.lets_talk.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @PostMapping("/add-message/{room-id}")
    public ResponseEntity<SuccessResponse> addMessageToRoom(@PathVariable(name = "room-id") String roomId , @RequestBody MessageDto messageDto){
        log.info(":: Inside MessageController : method addMessageToRoom : roomId  = {} : messageDto = {}",roomId,messageDto);
        SuccessResponse successResponse = messageService.addMessage(roomId , messageDto);
        return ResponseEntity.ok(successResponse);

    }

    @PostMapping("/update-message/{room-id}")
    public ResponseEntity<SuccessResponse> updateMessageToRoom(@PathVariable(name = "room-id") String roomId, @RequestBody MessageDto messageDto){
        log.info(":: Inside MessageController : method updateMessageToRoom : roomId : {} , messageDto = {}",roomId,messageDto);
        SuccessResponse successResponse = messageService.updateMessage( roomId , messageDto);
        return ResponseEntity.ok(successResponse);

    }



}
