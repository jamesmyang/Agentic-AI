package com.atucity.example.springai.ai.anthropic;

import com.atucity.example.springai.ai.ChatAgent;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("com.atucity.example.springai.ai.anthropic.anthropicChatModelAgent")
public class AnthropicChatModelAgent implements ChatAgent {

    private ChatModel chatModel;

    public AnthropicChatModelAgent(
            @Qualifier("com.atucity.example.springai.ai.anthropic.AnthropicChatModelConfig.anthropicChatModel") ChatModel chatModel
    ) {
        this.chatModel =  chatModel;
        //System.out.println("1 OpenAiChatModelAgent model: " + this.chatModel);
    }



    @Override
    public String chat(String message) {

        return this.chatModel.call(message);
    }

}

