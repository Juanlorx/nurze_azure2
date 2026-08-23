/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Aprendiz
 */
public class Enfermeras {
    private int id_enfermeras;
    private String rethus_enfermeras;
    private String nombreCompleto;
    
    public int getid_Enfermeras (){
        return id_enfermeras;
    }
    public void setid_Enfermeras (int id_enfermeras){
        this.id_enfermeras = id_enfermeras;
    }
    public String getRethus_Enfermeras (){
        return rethus_enfermeras;
    }
    public void setRethus_Enfermeras (String rethus){
        this.rethus_enfermeras = rethus;
    }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
}
