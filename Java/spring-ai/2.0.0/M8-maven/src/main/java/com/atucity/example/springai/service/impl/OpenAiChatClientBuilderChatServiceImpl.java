package com.atucity.example.springai.service.impl;

import com.atucity.example.springai.ai.ChatAgent;
import com.atucity.example.springai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("com.atucity.example.springai.service.impl.OpenAiChatClientBuilderChatServiceImpl")
public class OpenAiChatClientBuilderChatServiceImpl implements ChatService {

    @Autowired
    @Qualifier("com.atucity.example.springai.ai.openai.openAiChatClientBuilderAgent")
    private ChatAgent chatAgent;

    @Override
    public String chat(String message) {
        return chatAgent.chat(message);
    }
}
