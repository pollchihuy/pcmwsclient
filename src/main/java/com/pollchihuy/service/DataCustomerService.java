package com.pollchihuy.service;

import com.pollchihuy.repo.DataCustomerRepo;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class DataCustomerService extends DefaultHandshakeHandler {

    @Autowired
    private DataCustomerRepo dataCustomerRepo;

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        String [] arr = request.getURI().toString().split("/");
        System.out.println("Masuk ke sini !!");
        String randomId = arr[5];
        System.out.println("User dengan ID {"+randomId+"} baru saja terhubung");
        return new UserPrincipal(randomId);
    }
}