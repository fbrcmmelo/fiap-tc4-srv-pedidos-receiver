package com.fiap.tc4_srv_pedido_receiver.domain;

import com.fiap.tc4_srv_pedido_receiver.adapters.RabbitMqServiceAdapter;
import com.fiap.tc4_srv_pedido_receiver.consts.FilaConstants;
import com.fiap.tc4_srv_pedido_receiver.gateway.EnviarPedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnviarPedidoService implements EnviarPedidoGateway {

    private final RabbitMqServiceAdapter adapter;

    @Override
    public void enviarPedido(Pedido pedido) {
        this.adapter.send(FilaConstants.PEDIDO_QUEUE, pedido);
    }
}
