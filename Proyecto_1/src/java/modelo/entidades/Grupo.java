/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

/**
 *
 * @author gaspa
 */
public class Grupo {

    public Grupo() {
    }

    public Grupo(int id, int secuencia, String nombre, int cupo, String activo) {
        this.id = id;
        this.secuencia = secuencia;
        this.nombre = nombre;
        this.cupo = cupo;
        this.activo = activo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    private int id;
    private int secuencia;
    private String nombre;
    private int cupo;
    private String activo;
}
