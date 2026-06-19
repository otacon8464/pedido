package com.examen.pedidos.exception;

import com.examen.pedidos.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PedidoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<Object> handlePedidoNotFound(PedidoNotFoundException ex) {
        return BaseResponse.builder()
                .codigo(404)
                .mensaje(ex.getMessage())
                .objeto(null)
                .build();
    }

    @ExceptionHandler(StockInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleStockInsuficiente(StockInsuficienteException ex) {
        return BaseResponse.builder()
                .codigo(400)
                .mensaje(ex.getMessage())
                .objeto(null)
                .build();
    }
}