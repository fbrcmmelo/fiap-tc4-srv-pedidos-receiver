package com.fiap.tc4_srv_pedido_receiver.usecases;

import com.fiap.tc4_srv_pedido_receiver.controller.FazerPedidoRequest;
import com.fiap.tc4_srv_pedido_receiver.domain.Pedido;
import com.fiap.tc4_srv_pedido_receiver.gateway.FazerPedidoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FazerPedidoUseCase implements IFazerPedidoUseCase {

    private final FazerPedidoGateway pedidoReceiverGateway;

    @Override
    public void fazerPedido(FazerPedidoRequest request) {
        final var pedido = new Pedido(request.clienteId(), request.cartaoCliente(), request.produtosPedido());
        this.pedidoReceiverGateway.criarPedido(pedido);
    }
}
