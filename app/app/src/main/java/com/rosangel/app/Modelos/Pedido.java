package com.rosangel.app.Modelos;

import java.util.Date;

public class Pedido {
    private int id;
    private String producto;
    private String cliente;
    private String empleado;
    private String direccion;
    private String telefono;
    private String observaciones;
    private Date fecha;
    private String estatus;

    public Pedido(){}

    public Pedido(int id, String producto, String cliente, String empleado, String direccion, String telefono, String observaciones, Date fecha, String estatus) {
        this.id = id;
        this.producto = producto;
        this.cliente = cliente;
        this.empleado = empleado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.estatus = estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
