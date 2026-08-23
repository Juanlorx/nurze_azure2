package Insertar;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

import Modelo.Horarios;
import Controlador.HorariosDAO;

public class PruebasInsertarHorarios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Horarios horario = new Horarios();
        HorariosDAO dao = new HorariosDAO();

        System.out.println("=== INSERTAR HORARIO ===");

        System.out.print("ID Horario: ");
        horario.setid_Horarios(sc.nextInt());
        sc.nextLine();

        System.out.print("Fecha (DD-MM-AAAA): ");
        horario.setFecha(Date.valueOf(sc.nextLine()));

        System.out.print("Hora inicial (HH:MM:SS): ");
        horario.setHora_inicial(Time.valueOf(sc.nextLine()));

        System.out.print("Hora final (HH:MM:SS): ");
        horario.setHora_final(Time.valueOf(sc.nextLine()));

        if (dao.insertarHorario(horario)) {
            System.out.println("✅ Horario registrado.");
        } else {
            System.out.println("❌ Error al registrar.");
        }

        sc.close();
    }
}