package com.atucity.example.springai;

import com.atucity.example.springai.service.ChatService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the bean from the Spring container
        ChatService service = context.getBean("com.atucity.example.springai.service.impl.OpenAiChatClientChatServiceImpl", ChatService.class);

        System.out.println(service.chat("Tell me a bad joke"));

    }
}
