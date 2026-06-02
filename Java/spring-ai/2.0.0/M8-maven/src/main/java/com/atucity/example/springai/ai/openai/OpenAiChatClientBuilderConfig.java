package com.atucity.example.springai.ai.openai;

import io.micrometer.observation.ObservationRegistry;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClientBuilder;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
import org.springframework.ai.chat.client.advisor.observation.AdvisorObservationConvention;
import org.springframework.ai.chat.client.observation.ChatClientObservationConvention;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.ApiKey;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-openai-chatclient-builder.properties")
public class OpenAiChatClientBuilderConfig {

    @Bean(name="com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.openAiChatOptions")
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


    @Bean(name="com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.openAiChatModel")
    public OpenAiChatModel openAiChatModel(
            @Qualifier("com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.openAiChatOptions") OpenAiChatOptions openAiChatOptions
    ) {
        OpenAiChatModel chatModel= OpenAiChatModel.builder()
                .options(openAiChatOptions)
                .build();
        //System.out.println("openAiChatModel: " + model);
        //System.out.println("openAiChatOptions1: " + openAiChatOptions);
        return chatModel;

    }

    @Bean(name="com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.observationRegistry")
    public ObservationRegistry observationRegistry() {
        return ObservationRegistry.NOOP;
    }

    @Bean(name="com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.openAiChatClientBuilder")
    public ChatClient.Builder openAiChatClientBuilder(
            @Qualifier("com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.openAiChatModel") OpenAiChatModel openAiChatModel,
            @Qualifier("com.atucity.example.springai.ai.openai.OpenAiChatClientBuilderConfig.observationRegistry") ObservationRegistry observationRegistry
    ) {
        return new DefaultChatClientBuilder(openAiChatModel, observationRegistry, null,  null, null);
    }

}
