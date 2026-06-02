package com.atucity.example.springai.ai.openai;

import com.atucity.example.springai.ai.ChatAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("com.atucity.example.springai.ai.openai.openAiChatClientAgent")
public class OpenAiChatClientAgent implements ChatAgent {


    private ChatClient chatClient;

    public OpenAiChatClientAgent(
            @Qualifier("com.atucity.example.springai.ai.openai.OpenAiChatClientConfig.openAiChatModel") ChatModel chatModel
    ) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @Override
    public String chat(String message) {
        return this.chatClient.prompt().user(message).call().content();
    }
}
