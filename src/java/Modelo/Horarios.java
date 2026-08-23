package Modelo;

import java.sql.Date;
import java.sql.Time;

public class Horarios {

    private int id_horarios;
    private Date fecha;
    private Time hora_inicial;
    private Time hora_final;

    public int getid_Horarios() {
        return id_horarios;
    }

    public void setid_Horarios(int id_horarios) {
        this.id_horarios = id_horarios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(Time hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public Time getHora_final() {
        return hora_final;
    }

    public void setHora_final(Time hora_final) {
        this.hora_final = hora_final;
    }
}