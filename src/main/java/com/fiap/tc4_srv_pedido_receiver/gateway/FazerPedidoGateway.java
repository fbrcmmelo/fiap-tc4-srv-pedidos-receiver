package com.fiap.tc4_srv_pedido_receiver.gateway;

import com.fiap.tc4_srv_pedido_receiver.domain.Pedido;

public interface FazerPedidoGateway {

    void criarPedido(Pedido pedido);
}
