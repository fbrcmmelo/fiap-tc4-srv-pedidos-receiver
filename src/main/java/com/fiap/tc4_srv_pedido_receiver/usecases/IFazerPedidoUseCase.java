package com.fiap.tc4_srv_pedido_receiver.usecases;

import com.fiap.tc4_srv_pedido_receiver.controller.FazerPedidoRequest;

public interface IFazerPedidoUseCase {

    void fazerPedido(FazerPedidoRequest request);
}
