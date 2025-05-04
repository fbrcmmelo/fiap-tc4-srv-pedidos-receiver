package com.fiap.tc4_srv_pedido_receiver.domain;

import java.util.Optional;

public record DadosCartaoCliente(String numero) {

    public DadosCartaoCliente {
        final var numeroCartao = Optional.ofNullable(numero).orElse("");

        if (numeroCartao.isBlank()) {
            throw new IllegalArgumentException("Número do cartão inválido");
        }
    }
}
