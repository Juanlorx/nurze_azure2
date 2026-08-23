package Insertar;

import Controlador.PacienteDAO;
import Modelo.Paciente;
import java.sql.Date;
import java.util.Scanner;

public class PruebasInsertarPaciente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Paciente paciente = new Paciente();
        PacienteDAO dao = new PacienteDAO();

        System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
        paciente.setFecha_nacimiento(Date.valueOf(sc.nextLine()));

        System.out.print("Diagnóstico: ");
        paciente.setDiagnostico(sc.nextLine());

        System.out.print("ID Usuario: ");
        paciente.setUsuariosIdUsuarios(sc.nextInt());

        if (dao.insertarPaciente(paciente)) {
            System.out.println("Paciente insertado correctamente.");
        } else {
            System.out.println("No fue posible insertar el paciente.");
        }

        sc.close();
    }
}