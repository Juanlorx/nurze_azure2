package Consultar;

import java.util.Scanner;
import Modelo.Paciente;
import Controlador.PacienteDAO;

public class PruebasConsultarPaciente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PacienteDAO dao = new PacienteDAO();

        System.out.print("ID Paciente: ");
        int id = sc.nextInt();

        Paciente p = dao.consultarPaciente(id);

        if(p != null){

            System.out.println("ID: " + p.getIdPaciente());
            System.out.println("Fecha: " + p.getFecha_nacimiento());
            System.out.println("Diagnóstico: " + p.getDiagnostico());

        }

        sc.close();
    }
}