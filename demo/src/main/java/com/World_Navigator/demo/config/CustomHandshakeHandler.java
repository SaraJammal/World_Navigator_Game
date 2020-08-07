package com.World_Navigator.demo.config;

import java.security.Principal;
import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

class CustomHandshakeHandler extends DefaultHandshakeHandler {
  @Override
  protected Principal determineUser(
      ServerHttpRequest request,
      WebSocketHandler wsHandler,
      Map<String, Object> attributes
  ) {
    String ip = request.getRemoteAddress().getHostString();
    return new StompPrincipal(ip);
  }
}