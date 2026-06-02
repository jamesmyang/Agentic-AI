package com.atucity.example.springai.ai.openai;

import com.atucity.example.springai.ai.ChatAgent;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("com.atucity.example.springai.ai.openai.openAiChatModelAgent")
public class OpenAiChatModelAgent implements ChatAgent {

    private ChatModel chatModel;

    public OpenAiChatModelAgent(
            @Qualifier("com.atucity.example.springai.ai.openai.OpenAiChatModelConfig.openAiChatModel") ChatModel chatModel
    ) {
        this.chatModel =  chatModel;
        //System.out.println("1 OpenAiChatModelAgent model: " + this.chatModel);
    }



    @Override
    public String chat(String message) {

        return this.chatModel.call(message);
    }
}
