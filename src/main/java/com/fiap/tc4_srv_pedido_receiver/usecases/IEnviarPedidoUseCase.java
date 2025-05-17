package com.fiap.tc4_srv_pedido_receiver.usecases;

import com.fiap.tc4_srv_pedido_receiver.controller.EnviarPedidoRequest;

public interface IEnviarPedidoUseCase {

    void enviarPedido(EnviarPedidoRequest request);
}
