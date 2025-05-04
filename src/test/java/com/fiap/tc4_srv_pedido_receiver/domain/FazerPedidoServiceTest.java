package com.fiap.tc4_srv_pedido_receiver.domain;

import com.fiap.tc4_srv_pedido_receiver.adapters.RabbitMqServiceAdapter;
import com.fiap.tc4_srv_pedido_receiver.consts.FilaConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FazerPedidoServiceTest {

    @Mock
    private RabbitMqServiceAdapter adapter;

    @InjectMocks
    private FazerPedidoService fazerPedidoService;

    private Pedido samplePedido;

    @BeforeEach
    void setUp() {
        // Create a sample Pedido object before each test
        samplePedido = new Pedido("1L", new DadosCartaoCliente("123123123"), List.of(new ProdutoPedido("sku",
                10L)));
    }

    @Test
    void criarPedido_shouldCallAdapterSendWithCorrectArguments() {
        // Arrange

        // Act
        fazerPedidoService.criarPedido(samplePedido);

        // Assert
        verify(adapter).send(FilaConstants.PEDIDO_QUEUE, samplePedido);
    }
}
