package Eliminar;

import java.util.Scanner;
import Controlador.PacienteDAO;

public class PruebasEliminarPaciente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PacienteDAO dao = new PacienteDAO();

        System.out.print("ID Paciente: ");
        int id = sc.nextInt();

        dao.eliminarPaciente(id);

        sc.close();
    }
}