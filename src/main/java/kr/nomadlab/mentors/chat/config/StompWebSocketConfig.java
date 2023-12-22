package kr.nomadlab.mentors.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // stomp 사용 선언 어노테이션
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    // 어플리케이션 내부에서 사용할 path를 지정 할 수 있음
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub"); // client에서 SEND 요청을 처리
        registry.enableSimpleBroker("/sub"); // 해당 경로를 구독(SUBSCRIBE)하는 Client에게 메세지를 전달
    }
}
