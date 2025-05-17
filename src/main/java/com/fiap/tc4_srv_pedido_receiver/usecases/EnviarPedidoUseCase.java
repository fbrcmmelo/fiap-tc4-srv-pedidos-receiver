package com.fiap.tc4_srv_pedido_receiver.usecases;

import com.fiap.tc4_srv_pedido_receiver.controller.EnviarPedidoRequest;
import com.fiap.tc4_srv_pedido_receiver.domain.Pedido;
import com.fiap.tc4_srv_pedido_receiver.gateway.EnviarPedidoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnviarPedidoUseCase implements IEnviarPedidoUseCase {

    private final EnviarPedidoGateway pedidoReceiverGateway;

    @Override
    public void enviarPedido(EnviarPedidoRequest request) {
        final var pedido = new Pedido(request.clienteId(), request.cartaoCliente(), request.produtosPedido());
        this.pedidoReceiverGateway.enviarPedido(pedido);
    }
}
