/*
    Estudiante.css

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez

*/

package modelo.entidades;

import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;


public class Estudiante {

    public Estudiante() {
    }

    public Estudiante(String id, int nrc, String apellidos, String nombre, Date ultimoAcceso, int grupo, int secuencia, boolean activo) {
        this.id = id;
        this.nrc = nrc;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.ultimoAcceso = ultimoAcceso;
        this.grupo = grupo;
        this.secuencia = secuencia;
        this.activo = activo;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public long getTiempoInactivo(){
        long actual = Calendar.getInstance().getTimeInMillis();
        return actual - getUltimoAcceso().getTime();
    }
    private static final int MAX_Tiempo_ACTIVO = 5;
    private String id;
    private int nrc;
    private String apellidos;
    private String nombre;
    private Date ultimoAcceso;
    private int grupo;
    private int secuencia;
    private boolean activo;  
}
