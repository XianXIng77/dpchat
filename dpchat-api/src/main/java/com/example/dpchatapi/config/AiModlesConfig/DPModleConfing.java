package com.example.dpchatapi.config.AiModlesConfig;



import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DPModleConfing {
    @Bean
    public ChatClient chatClient(OllamaChatModel model){
        return ChatClient
                .builder(model)
                /*设置提示词
                .defaultSystem("")*/
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
