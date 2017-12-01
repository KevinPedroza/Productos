/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author Kevin
 */
public class Compras_herencia {

    String nombre;
    String sexo;
    int precio;
    int id_prod;
    String fecha;

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public int getPrecio() {
        return precio;
    }

    public int getId_prod() {
        return id_prod;
    }

    public String getFecha() {
        return fecha;
    }

    public Compras_herencia(String nombre, String sexo, int precio, int id_prod, String fecha) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.precio = precio;
        this.id_prod = id_prod;
        this.fecha = fecha;
    }

}
