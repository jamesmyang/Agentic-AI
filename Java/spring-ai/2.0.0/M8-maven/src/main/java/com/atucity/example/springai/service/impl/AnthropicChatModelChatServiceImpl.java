package com.atucity.example.springai.service.impl;

import com.atucity.example.springai.ai.ChatAgent;
import com.atucity.example.springai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("com.atucity.example.springai.service.impl.AnthropicChatModelChatServiceImpl")
public class AnthropicChatModelChatServiceImpl implements ChatService {

    @Autowired
    @Qualifier("com.atucity.example.springai.ai.anthropic.anthropicChatModelAgent")
    private ChatAgent chatAgent;

    @Override
    public String chat(String message) {
        return chatAgent.chat(message);
    }

}
