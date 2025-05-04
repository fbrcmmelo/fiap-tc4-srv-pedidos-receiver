package com.fiap.tc4_srv_pedido_receiver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tc4_srv_pedido_receiver.domain.DadosCartaoCliente;
import com.fiap.tc4_srv_pedido_receiver.domain.ProdutoPedido;
import com.fiap.tc4_srv_pedido_receiver.usecases.IFazerPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PedidoReceiverControllerTest {

    @Mock
    private IFazerPedidoUseCase fazerPedidoUseCase;
    @InjectMocks
    private PedidoReceiverController pedidoReceiverController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoReceiverController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void makeOrder_Success() throws Exception {
        // Arrange
        final var request = new FazerPedidoRequest("1", new DadosCartaoCliente("123432"),
                List.of(new ProdutoPedido("sku-1", 5L)));

        doNothing().when(fazerPedidoUseCase).fazerPedido(any(FazerPedidoRequest.class));

        // Act & Assert
        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(fazerPedidoUseCase, times(1)).fazerPedido(any(FazerPedidoRequest.class));
    }

    @Test
    void makeOrder_Failure() throws Exception {
        // Arrange
        final var invalidRequest = new FazerPedidoRequest(null, new DadosCartaoCliente("123432"), null);

        doThrow(new RuntimeException("Test Exception")).when(fazerPedidoUseCase)
                .fazerPedido(any(FazerPedidoRequest.class));

        // Act & Assert
        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isUnprocessableEntity());

        verify(fazerPedidoUseCase, times(1)).fazerPedido(any(FazerPedidoRequest.class));
    }
}