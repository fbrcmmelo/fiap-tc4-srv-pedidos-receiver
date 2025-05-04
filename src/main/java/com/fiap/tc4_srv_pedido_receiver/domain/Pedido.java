package com.fiap.tc4_srv_pedido_receiver.domain;

import com.fiap.tc4_srv_pedido_receiver.controller.DadosCartaoCliente;
import com.fiap.tc4_srv_pedido_receiver.controller.ProdutoPedido;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
public class Pedido {

    private String clienteId;
    private DadosCartaoCliente dadosCartao;
    private List<ProdutoPedido> produtoPedidos;

    public Pedido(String clienteId, DadosCartaoCliente dadosCartao, List<ProdutoPedido> produtoPedidos) {
        Objects.requireNonNull(clienteId, "Id do cliente não pode ser nulo");
        Objects.requireNonNull(dadosCartao, "Dados cartao do cliente não pode ser nulo");

        final var produtoList = Optional.ofNullable(produtoPedidos).orElse(new ArrayList<>());

        if (produtoList.isEmpty()) {
            throw new IllegalArgumentException("Lista de produtos do pedido não pode estar vazia");
        }

        this.clienteId = clienteId;
        this.dadosCartao = dadosCartao;
        this.produtoPedidos = produtoPedidos;
    }
}
