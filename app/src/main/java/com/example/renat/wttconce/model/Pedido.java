package com.example.renat.wttconce.model;

public class Pedido {

    private Long idPedido;
    private String dtCompra;
    private String valor;
    private String observacao;
    private String status;
    private String modeloCarro;

    @Override
    public String toString() {
        return "ID Pedido = " + idPedido + " Data compra = " + dtCompra + " valor = " + valor + " observação = " + observacao + " status = " + status + " modelo carro = " + modeloCarro;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getDtCompra() {
        return dtCompra;
    }

    public void setDtCompra(String dtCompra) {
        this.dtCompra = dtCompra;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }
}
