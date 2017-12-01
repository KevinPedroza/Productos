package productos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kevin
 */
public class Productos_herencia {

    String nombre;
    int id_prod;
    String tipo;
    int precio;

    public Productos_herencia(String nombre, int id_prod, String tipo, int precio) {
        this.nombre = nombre;
        this.id_prod = id_prod;
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId_prod() {
        return id_prod;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return this.nombre;

    }

}
