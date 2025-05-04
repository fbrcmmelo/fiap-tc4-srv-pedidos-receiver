package com.fiap.tc4_srv_pedido_receiver.usecases;

public interface SendQueueMessageUseCase {

    void send(String queue, Object message);

}
