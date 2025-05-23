package com.fiap.tc4_srv_pedido_receiver.controller;


import com.fiap.tc4_srv_pedido_receiver.usecases.IEnviarPedidoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pedidos", produces = "application/json")
public class PedidoReceiverController {

    private final IEnviarPedidoUseCase enviarPedidoUseCase;

    @PostMapping
    public ResponseEntity<Void> makeOrder(@RequestBody EnviarPedidoRequest request) {
        try {
            this.enviarPedidoUseCase.enviarPedido(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Erro ao tentar realizar o pedido, erro: {}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
