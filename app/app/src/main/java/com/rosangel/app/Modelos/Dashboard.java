package com.rosangel.app.Modelos;

public class Dashboard {
    private int pedidosTotales;
    private int pedidosPendientes;
    private int ventas;
    private int ganancias;
    private int clientes;
    private int productos;

    public Dashboard() {}

    public int getPedidosTotales() {
        return pedidosTotales;
    }

    public void setPedidosTotales(int pedidosTotales) {
        this.pedidosTotales = pedidosTotales;
    }

    public int getPedidosPendientes() {
        return pedidosPendientes;
    }

    public void setPedidosPendientes(int pedidosPendientes) {
        this.pedidosPendientes = pedidosPendientes;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getGanancias() {
        return ganancias;
    }

    public void setGanancias(int ganancias) {
        this.ganancias = ganancias;
    }

    public int getClientes() {
        return clientes;
    }

    public void setClientes(int clientes) {
        this.clientes = clientes;
    }

    public int getProductos() {
        return productos;
    }

    public void setProductos(int productos) {
        this.productos = productos;
    }
}
