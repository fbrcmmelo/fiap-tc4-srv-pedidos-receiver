package com.fiap.tc4_srv_pedido_receiver.gateway;

import com.fiap.tc4_srv_pedido_receiver.domain.Pedido;

public interface EnviarPedidoGateway {

    void enviarPedido(Pedido pedido);
}
