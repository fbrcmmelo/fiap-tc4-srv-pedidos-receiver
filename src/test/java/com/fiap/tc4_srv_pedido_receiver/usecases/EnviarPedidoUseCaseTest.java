package com.fiap.tc4_srv_pedido_receiver.usecases;

import com.fiap.tc4_srv_pedido_receiver.domain.DadosCartaoCliente;
import com.fiap.tc4_srv_pedido_receiver.controller.EnviarPedidoRequest;
import com.fiap.tc4_srv_pedido_receiver.domain.ProdutoPedido;
import com.fiap.tc4_srv_pedido_receiver.domain.Pedido;
import com.fiap.tc4_srv_pedido_receiver.gateway.EnviarPedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnviarPedidoUseCaseTest {

    @Mock
    private EnviarPedidoGateway pedidoReceiverGateway;
    @InjectMocks
    private EnviarPedidoUseCase enviarPedidoUseCase;

    private EnviarPedidoRequest request;

    @BeforeEach
    void setUp() {
        request = new EnviarPedidoRequest("1L", new DadosCartaoCliente("123123123"), List.of(new ProdutoPedido("sku",
                10L)));
    }

    @Test
    void enviarPedido_shouldCreatePedido() {
        // Arrange
        doNothing().when(pedidoReceiverGateway).enviarPedido(any(Pedido.class));

        // Act
        enviarPedidoUseCase.enviarPedido(request);

        // Assert
        verify(pedidoReceiverGateway).enviarPedido(any(Pedido.class));
    }

    @Test
    void enviarPedido_gatewayFails() {
        // Arrange
        doThrow(RuntimeException.class).when(pedidoReceiverGateway).enviarPedido(any(Pedido.class));

        // Act
        assertThatThrownBy(() -> enviarPedidoUseCase.enviarPedido(request))
                .isInstanceOf(RuntimeException.class);

        // Assert
        verify(pedidoReceiverGateway).enviarPedido(any(Pedido.class));
    }
}