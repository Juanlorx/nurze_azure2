/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aprendiz
 */
public class Tratamiento {
    private int id_tratamiento;
    private String descripcion;
    private int pacienteId;
    private String pacienteNombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String indicaciones;
    private List<Integer> medicamentosIds = new ArrayList<>();
    private List<String> medicamentosNombres = new ArrayList<>();
    
    public int getid_Tratamiento (){
        return id_tratamiento;
    }
    public void setid_Tratamiento (int id_tratamiento){
        this.id_tratamiento = id_tratamiento;
    }
    public String getDescripcion (){
        return descripcion;
    }
    public void setDescripcion (String descripcion){
        this.descripcion = descripcion;
    }

    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

    public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String pacienteNombre) { this.pacienteNombre = pacienteNombre; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getIndicaciones() { return indicaciones; }
    public void setIndicaciones(String indicaciones) { this.indicaciones = indicaciones; }

    public List<Integer> getMedicamentosIds() { return medicamentosIds; }
    public void setMedicamentosIds(List<Integer> medicamentosIds) { this.medicamentosIds = medicamentosIds; }

    public List<String> getMedicamentosNombres() { return medicamentosNombres; }
    public void setMedicamentosNombres(List<String> medicamentosNombres) { this.medicamentosNombres = medicamentosNombres; }

    public boolean tieneMedicamento(int idMedicamento) { return medicamentosIds.contains(idMedicamento); }
}
