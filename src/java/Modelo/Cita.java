package Modelo;

import java.sql.Date;
import java.sql.Time;

public class Cita {
    private int idCita;
    private int pacienteId;
    private String pacienteNombre;
    private int enfermeraId;
    private String enfermeraNombre;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private String tipo;
    private String estado;
    private String notas;

    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
    public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String pacienteNombre) { this.pacienteNombre = pacienteNombre; }
    public int getEnfermeraId() { return enfermeraId; }
    public void setEnfermeraId(int enfermeraId) { this.enfermeraId = enfermeraId; }
    public String getEnfermeraNombre() { return enfermeraNombre; }
    public void setEnfermeraNombre(String enfermeraNombre) { this.enfermeraNombre = enfermeraNombre; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Time getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }
    public Time getHoraFin() { return horaFin; }
    public void setHoraFin(Time horaFin) { this.horaFin = horaFin; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
    public String getHoraInicioTexto() { return horaInicio == null ? "" : horaInicio.toString().substring(0, 5); }
    public String getHoraFinTexto() { return horaFin == null ? "" : horaFin.toString().substring(0, 5); }
}
