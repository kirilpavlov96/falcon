package org.falcon.shop.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.vertx.core.impl.ConcurrentHashSet;
import org.falcon.shop.services.OrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;

/**
 * WebSocket for orders.
 */
@ServerEndpoint("/orders")
@ApplicationScoped
public class OrderSocket {

    @Inject
    OrderService orderService;

    Set<Session> sessions = new ConcurrentHashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        broadcast(orderService.findAll());
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessions.remove(session);
    }

    public void broadcast(Object message) {
        sessions.forEach(s -> {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = null;
            try {
                json = ow.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            s.getAsyncRemote().sendObject(json, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }

}

