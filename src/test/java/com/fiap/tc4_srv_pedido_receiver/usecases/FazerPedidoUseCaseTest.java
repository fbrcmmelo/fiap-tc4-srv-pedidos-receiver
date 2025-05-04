package com.fiap.tc4_srv_pedido_receiver.usecases;

import com.fiap.tc4_srv_pedido_receiver.domain.DadosCartaoCliente;
import com.fiap.tc4_srv_pedido_receiver.controller.FazerPedidoRequest;
import com.fiap.tc4_srv_pedido_receiver.domain.ProdutoPedido;
import com.fiap.tc4_srv_pedido_receiver.domain.Pedido;
import com.fiap.tc4_srv_pedido_receiver.gateway.FazerPedidoGateway;
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
class FazerPedidoUseCaseTest {

    @Mock
    private FazerPedidoGateway pedidoReceiverGateway;
    @InjectMocks
    private FazerPedidoUseCase fazerPedidoUseCase;

    private FazerPedidoRequest request;

    @BeforeEach
    void setUp() {
        request = new FazerPedidoRequest("1L", new DadosCartaoCliente("123123123"), List.of(new ProdutoPedido("sku",
                10L)));
    }

    @Test
    void fazerPedido_shouldCreatePedido() {
        // Arrange
        doNothing().when(pedidoReceiverGateway).criarPedido(any(Pedido.class));

        // Act
        fazerPedidoUseCase.fazerPedido(request);

        // Assert
        verify(pedidoReceiverGateway).criarPedido(any(Pedido.class));
    }

    @Test
    void fazerPedido_gatewayFails() {
        // Arrange
        doThrow(RuntimeException.class).when(pedidoReceiverGateway).criarPedido(any(Pedido.class));

        // Act
        assertThatThrownBy(() -> fazerPedidoUseCase.fazerPedido(request))
                .isInstanceOf(RuntimeException.class);

        // Assert
        verify(pedidoReceiverGateway).criarPedido(any(Pedido.class));
    }
}