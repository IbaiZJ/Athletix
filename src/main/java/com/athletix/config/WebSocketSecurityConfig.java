package com.athletix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

// @Configuration
// public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

//     // @Override
//     // protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//     //     messages
//     //             .simpDestMatchers("/app/**").authenticated()
//     //             .simpSubscribeDestMatchers("/user/queue/**").authenticated()
//     //             .anyMessage().denyAll();
//     // }

//     // @Override
//     // protected boolean sameOriginDisabled() {
//     //     return true; // Solo para desarrollo, en producción configurar CORS adecuadamente
//     // }
// }