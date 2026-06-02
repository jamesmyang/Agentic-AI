package com.atucity.example.springai.ai.openai;

import com.atucity.example.springai.ai.ChatAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("com.atucity.example.springai.ai.openai.openAiChatClientBuilderAgent")
public class OpenAiChatClientBuilderAgent implements ChatAgent {

    private ChatClient chatClient;

    public OpenAiChatClientBuilderAgent(
            @Qualifier("com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.openAiChatClientBuilder") ChatClient.Builder clientBuilder) {
        System.out.println("clientBuilder: "+ clientBuilder);
        this.chatClient = clientBuilder.build();
        System.out.println("this.chatClient: "+ this.chatClient);
    }

    @Override
    public String chat(String message) {
        return this.chatClient.prompt().user(message).call().content();
    }
}
