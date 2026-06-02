package com.atucity.example.springai.ai.openai;

import org.springframework.ai.model.ApiKey;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-openai-model.properties")
public class OpenAiChatModelConfig {


    @Bean(name="com.atucity.example.springai.ai.openai.OpenAiChatModelConfig.customApiKey")
    public ApiKey customApiKey() {
        return new ApiKey() {

            @Value("${spring.ai.openai.chat.api-key}") // or substitute this property from properties file
            String apiKey;

            @Override
            public String getValue() {
                // or custom logic to retrieve API key
                return apiKey;
            }
        };
    }

    @Bean(name="com.atucity.example.springai.ai.openai.OpenAiChatModelConfig.openAiChatOptions")
    public OpenAiChatOptions openAiChatOptions(
            @Value("${spring.ai.openai.chat.model}") String model,
            @Value("${spring.ai.openai.chat.api-key}") String apiKey,
            @Value("${spring.ai.openai.chat.temperature}") Double temperature,
            @Value("${spring.ai.openai.chat.max-tokens}") Integer maxTokens
    ) {
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model(model)
                .apiKey(apiKey)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .build();
        //System.out.println("apiKey: " + apiKey);
        return openAiChatOptions;
    }


    @Bean(name="com.atucity.example.springai.ai.openai.OpenAiChatModelConfig.openAiChatModel")
    public OpenAiChatModel openAiChatModel(
            @Qualifier("com.atucity.example.springai.ai.openai.OpenAiChatModelConfig.openAiChatOptions") OpenAiChatOptions openAiChatOptions
    ) {
        OpenAiChatModel chatModel= OpenAiChatModel.builder()
                .options(openAiChatOptions)
                .build();
        //System.out.println("openAiChatModel: " + model);
        //System.out.println("openAiChatOptions1: " + openAiChatOptions);
        return chatModel;

    }

    /*@Bean
    public ChatModel openAiChatModel1() {
        ChatModel chatModel= OpenAiChatModel.builder().build();
        System.out.println("openAiChatModel1: " + chatModel);
        return chatModel;

    }*/
}
