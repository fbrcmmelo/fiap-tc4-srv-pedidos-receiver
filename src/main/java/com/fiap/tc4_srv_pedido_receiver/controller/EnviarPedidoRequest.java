package com.fiap.tc4_srv_pedido_receiver.controller;

import com.fiap.tc4_srv_pedido_receiver.domain.DadosCartaoCliente;
import com.fiap.tc4_srv_pedido_receiver.domain.ProdutoPedido;

import java.util.List;

public record EnviarPedidoRequest(String clienteId, DadosCartaoCliente cartaoCliente,
                                  List<ProdutoPedido> produtosPedido) {

}
