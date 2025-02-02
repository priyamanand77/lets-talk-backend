package com.chat.lets_talk.service.message;

import com.chat.lets_talk.dto.message.MessageDto;
import com.chat.lets_talk.dto.response.SuccessResponse;

public interface MessageService {
    SuccessResponse addMessage(String roomId, MessageDto messageDto);

    SuccessResponse updateMessage(String roomId , MessageDto messageDto);
}
