package tareas;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Escritura {
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	// Leer texto
    public static String readString() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Error al leer texto: " + e.getMessage());
            return "";
        }
    }

    // Leer una cadena no vacía
    public static String readStringChecked(String mensaje) {
        String texto;

        do {
            System.out.println(mensaje);
            texto = readString();

            if (texto == null || texto.trim().isEmpty()) {
                System.out.println("El campo no puede estar vacío.");
            }
        } while (texto == null || texto.trim().isEmpty());

        return texto.trim();
    }

    // Leer un entero positivo o cero
    public static int readIntChecked(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = readString();

            try {
                int numero = Integer.parseInt(texto);

                if (numero > 0) {
                    return numero;
                }

                System.out.println("Introduce un número mayor que cero.");

            } catch (NumberFormatException e) {
                System.out.println("Formato incorrecto. Introduce un número entero.");
            }
        }
    }

    // Leer un identificador Long positivo
    public static Long readLongChecked(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = readString();

            try {
                long numero = Long.parseLong(texto);

                if (numero >= 0) {
                    return numero;
                }

                System.out.println("El identificador debe ser mayor que cero.");

            } catch (NumberFormatException e) {
                System.out.println("Formato incorrecto. Introduce un identificador válido.");
            }
        }
    }

    // Leer texto compuesto por letras y espacios
    public static String readNameChecked(String mensaje) {
        while (true) {
            String texto = readStringChecked(mensaje);

            if (texto.matches("[\\p{L} ]+")) {
                return texto;
            }

            System.out.println("Introduce solo letras y espacios.");
        }
    }
    
    public static LocalDate readDateChecked(String mensaje) {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.println(mensaje);
            String texto = readString();

            try {
                return LocalDate.parse(texto, formato);

            } catch (DateTimeParseException e) {
                System.out.println(
                    "Fecha incorrecta. Utiliza el formato dd/MM/yyyy."
                );
            }
        }
    }
    
    public static LocalDateTime readDateTimeChecked(String mensaje) {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (true) {
            System.out.println(mensaje);
            String texto = readString();

            try {
                return LocalDateTime.parse(texto, formato);

            } catch (DateTimeParseException e) {
                System.out.println(
                    "Fecha incorrecta. Utiliza dd/MM/yyyy HH:mm."
                );
            }
        }
    }
}