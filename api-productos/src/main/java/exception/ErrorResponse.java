package com.tienda.api.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int codigoEstado;
    private String mensaje;
    private LocalDateTime fechaHora;

    public ErrorResponse(int codigoEstado, String mensaje) {
        this.codigoEstado = codigoEstado;
        this.mensaje = mensaje;
        this.fechaHora = LocalDateTime.now();
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}