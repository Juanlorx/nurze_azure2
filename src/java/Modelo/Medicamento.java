/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Aprendiz
 */
public class Medicamento {
    private int id_medicamento;
    private String nombre;
    private String funcion;
    private String presentacion;
    private String lote;
    private Date fechaVencimiento;
    private int existencias;
    
    public int getid_Medicamento (){
        return id_medicamento;
    }
    public void setid_Medicamento (int id_medicamento){
        this.id_medicamento = id_medicamento;
    }
    public String getNombre (){
        return nombre;
    }
    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public String getFuncion() { return funcion; }
    public void setFuncion(String funcion) { this.funcion = funcion; }

    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getExistencias() { return existencias; }
    public void setExistencias(int existencias) { this.existencias = existencias; }
}
