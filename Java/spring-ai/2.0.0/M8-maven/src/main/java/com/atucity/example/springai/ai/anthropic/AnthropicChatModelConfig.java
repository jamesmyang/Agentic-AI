package com.atucity.example.springai.ai.anthropic;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.model.ApiKey;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-anthropic-chatmodel.properties")
public class AnthropicChatModelConfig {

    @Bean(name="com.atucity.example.springai.ai.anthropic.AnthropicChatModelConfig.customApiKey")
    public ApiKey customApiKey() {
        return new ApiKey() {

            @Value("${spring.ai.anthropic.api-key}") // or substitute this property from properties file
            String apiKey;

            @Override
            public String getValue() {
                // or custom logic to retrieve API key
                return apiKey;
            }
        };
    }

    @Bean(name="com.atucity.example.springai.ai.anthropic.AnthropicChatModelConfig.anthropicChatOptions")
    public AnthropicChatOptions anthropicChatOptions(
            @Value("${spring.ai.anthropic.chat.model}") String model,
            @Value("${spring.ai.anthropic.api-key}") String apiKey,
            @Value("${spring.ai.anthropic.chat.temperature}") Double temperature,
            @Value("${spring.ai.anthropic.chat.max-tokens}") Integer maxTokens
    ) {
        AnthropicChatOptions anthropicChatOptions = AnthropicChatOptions.builder()
                .model(model)
                .apiKey(apiKey)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .build();
        //System.out.println("apiKey: " + apiKey);
        return anthropicChatOptions;
    }

    @Bean(name="com.atucity.example.springai.ai.anthropic.AnthropicChatModelConfig.anthropicChatModel")
    public AnthropicChatModel openAiChatModel(
            @Qualifier("com.atucity.example.springai.ai.anthropic.AnthropicChatModelConfig.anthropicChatOptions") AnthropicChatOptions anthropicChatOptions
    ) {
        AnthropicChatModel chatModel= AnthropicChatModel.builder()
                .options(anthropicChatOptions)
                .build();
        //System.out.println("openAiChatModel: " + model);
        //System.out.println("openAiChatOptions1: " + openAiChatOptions);
        return chatModel;

    }

}
