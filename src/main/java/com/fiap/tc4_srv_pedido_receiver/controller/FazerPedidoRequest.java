package com.fiap.tc4_srv_pedido_receiver.controller;

import java.util.List;

public record FazerPedidoRequest(String clienteId, DadosCartaoCliente cartaoCliente,
                                 List<ProdutoPedido> produtosPedido) {

}
