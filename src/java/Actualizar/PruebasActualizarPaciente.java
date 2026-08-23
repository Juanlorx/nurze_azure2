package Actualizar;

import java.sql.Date;
import java.util.Scanner;

import Modelo.Paciente;
import Controlador.PacienteDAO;

public class PruebasActualizarPaciente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Paciente p = new Paciente();
        PacienteDAO dao = new PacienteDAO();

        System.out.print("ID Paciente: ");
        p.setIdPaciente(sc.nextInt());
        sc.nextLine();

        System.out.print("Nueva fecha (AAAA-MM-DD): ");
        p.setFecha_nacimiento(Date.valueOf(sc.nextLine()));

        System.out.print("Nuevo diagnóstico: ");
        p.setDiagnostico(sc.nextLine());

        dao.actualizarPaciente(p);

        sc.close();
    }
}