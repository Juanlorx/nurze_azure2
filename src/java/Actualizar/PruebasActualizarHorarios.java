package Actualizar;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

import Modelo.Horarios;
import Controlador.HorariosDAO;

public class PruebasActualizarHorarios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Horarios horario = new Horarios();
        HorariosDAO dao = new HorariosDAO();

        System.out.print("ID Horario: ");
        horario.setid_Horarios(sc.nextInt());
        sc.nextLine();

        System.out.print("Nueva fecha (AAAA-MM-DD): ");
        horario.setFecha(Date.valueOf(sc.nextLine()));

        System.out.print("Nueva hora inicial (HH:MM:SS): ");
        horario.setHora_inicial(Time.valueOf(sc.nextLine()));

        System.out.print("Nueva hora final (HH:MM:SS): ");
        horario.setHora_final(Time.valueOf(sc.nextLine()));

        if (dao.actualizarHorario(horario)) {
            System.out.println("✅ Horario actualizado.");
        } else {
            System.out.println("❌ Error al actualizar.");
        }

        sc.close();
    }
}