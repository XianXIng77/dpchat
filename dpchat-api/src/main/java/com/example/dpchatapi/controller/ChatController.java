package com.example.dpchatapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
@Slf4j
@Tag(name = "AI接口", description = "AI相关的接口")
public class ChatController {

    private final ChatClient chatClient;
    /**
     * 发送消息到AI
     * @param prompt
     * @return
     */
    @PostMapping(value = "/chat", produces = "text/html;charset=utf-8")
    @Operation(summary = "发送消息到AI", description = "向AI模型发送消息并获取流式响应")
    public Flux<String> chat(@Parameter(description = "用户输入的提示词") String prompt) {
        log.info("向ai发送消息:{}",prompt);
        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }

}