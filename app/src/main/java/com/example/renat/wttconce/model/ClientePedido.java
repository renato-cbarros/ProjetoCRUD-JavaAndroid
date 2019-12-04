package com.example.renat.wttconce.model;

public class ClientePedido {

    private Long idClientePedido;
    private Cliente idCliente;
    private Pedido idPedido;

    @Override
    public String toString() {
        return "ID Cliente Pedido = " + idClientePedido + " ID Cliente = " + idCliente.getIdPessoa() + " ID Pedido = " + idPedido.getIdPedido();
    }

    public ClientePedido() {
    }

    public ClientePedido(Long idClientePedido, Long idCliente, Long idPedido) {
        this.idClientePedido = idClientePedido;
        Cliente cli = new Cliente();
        cli.setIdPessoa(idCliente);
        this.setIdCliente(cli);
        Pedido ped = new Pedido();
        ped.setIdPedido(idPedido);
        this.idPedido = ped;
    }

    public Long getIdClientePedido() {
        return idClientePedido;
    }

    public void setIdClientePedido(Long idClientePedido) {
        this.idClientePedido = idClientePedido;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }
}
