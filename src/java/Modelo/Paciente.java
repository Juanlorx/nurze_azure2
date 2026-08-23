package Modelo;

import java.sql.Date;

public class Paciente {

    private int idPaciente;
    private Date fecha_nacimiento;
    private String diagnostico;
    private int usuariosIdUsuarios;
    private String nombreCompleto;
    private String identificacion;

    public Paciente() {
    }

    public Paciente(int idPaciente, Date fecha_nacimiento,
            String diagnostico, int usuariosIdUsuarios) {

        this.idPaciente = idPaciente;
        this.fecha_nacimiento = fecha_nacimiento;
        this.diagnostico = diagnostico;
        this.usuariosIdUsuarios = usuariosIdUsuarios;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getUsuariosIdUsuarios() {
        return usuariosIdUsuarios;
    }

    public void setUsuariosIdUsuarios(int usuariosIdUsuarios) {
        this.usuariosIdUsuarios = usuariosIdUsuarios;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

}
