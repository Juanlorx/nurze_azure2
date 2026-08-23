package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgendaMensual {
    private final List<Cita> citas;
    private final int anio;
    private final int mes;

    public AgendaMensual(List<Cita> citas, int anio, int mes) {
        this.citas = citas;
        this.anio = anio;
        this.mes = mes;
    }

    public List<Cita> getCitasDia(int dia) {
        return citas.stream().filter(c -> c.getFecha() != null
                && c.getFecha().toLocalDate().equals(LocalDate.of(anio, mes, dia)))
                .collect(Collectors.toList());
    }

    // Método invocado desde la expresión EL de Calendario.jsp.
    public List<Cita> citasDia(Integer dia) {
        return getCitasDia(dia.intValue());
    }

    public List<Cita> getCitas() { return new ArrayList<>(citas); }
}
