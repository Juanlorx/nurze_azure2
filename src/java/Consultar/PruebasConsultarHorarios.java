package Consultar;

import java.util.Scanner;

import Modelo.Horarios;
import Controlador.HorariosDAO;

public class PruebasConsultarHorarios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HorariosDAO dao = new HorariosDAO();

        System.out.print("ID Horario: ");
        int id = sc.nextInt();

        Horarios horario = dao.consultarHorario(id);

        if (horario != null) {

            System.out.println("\n=== DATOS DEL HORARIO ===");
            System.out.println("ID: " + horario.getid_Horarios());
            System.out.println("Fecha: " + horario.getFecha());
            System.out.println("Hora Inicial: " + horario.getHora_inicial());
            System.out.println("Hora Final: " + horario.getHora_final());

        } else {
            System.out.println("❌ Horario no encontrado.");
        }

        sc.close();
    }
}