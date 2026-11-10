/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 *                  comparado con la programacion academica
 * Entidad        : Apoyo para las dos entidades (leer y validar datos)
 * Programado por : Hilary Perez Zuniga y Luijeimm Agudelo Godoy
 * Fecha          : 04/11/2026 (ajustado a los mockups el 05/11/2026)
 * -----------------------------------------------------------
 * Formatos del diccionario de datos (Tablas 51 y 52):
 *   hora  HH:MM de 00:00 a 23:59, siempre con dos digitos
 *   fecha dd/mm/aaaa, con una fecha que exista
 *   texto en minuscula y sin tilde (vacia, miercoles, sabado, si)
 * RNF-04 Cada falla muestra un mensaje que nombra el campo o la condicion
 * RNF-05 Un dato mal escrito no cierra el programa: se avisa y se vuelve al menu
 * RN-04  La observacion no puede ser posterior al momento actual
 * -----------------------------------------------------------
 * Por compatibilidad con la consola de Windows, los textos del
 * programa se escriben sin tildes.
 * =========================================================== */
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Util {
    static Scanner sc = new Scanner(System.in);
    static final String[] DIAS = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};

    // Lee una linea sin espacios a los lados y sin tildes. Si se acaba la entrada, cierra el programa.
    static String leer(String pregunta) {
        System.out.print(pregunta);
        if (!sc.hasNextLine()) {
            System.out.println();
            System.exit(0);
        }
        String t = sc.nextLine().trim();
        // quita tildes (escritas como codigo unicode para que compile igual en Windows)
        t = t.replace("\u00e1", "a").replace("\u00e9", "e").replace("\u00ed", "i").replace("\u00f3", "o")
             .replace("\u00fa", "u").replace("\u00c1", "A").replace("\u00c9", "E").replace("\u00cd", "I")
             .replace("\u00d3", "O").replace("\u00da", "U");
        return t;
    }

    // Linea MENSAJE de los mockups
    static void aviso(String texto) { System.out.println("MENSAJE: " + texto); }

    // Linea RESULTADO del mockup del detalle (Figura 26)
    static void resultado(String texto) { System.out.println("RESULTADO: " + texto); }

    // Opcion de menu; -1 si no es un numero (RNF-05: no se cierra)
    static int opcion() {
        try { return Integer.parseInt(leer("  Opcion: ")); }
        catch (NumberFormatException e) { return -1; }
    }

    // Botones de los mockups: [G] Guardar, [L] Limpiar, [C] Cancelar, [B] Buscar...
    // Devuelve la letra escogida (en mayuscula); vuelve a preguntar si no es una de las dadas.
    static char boton(String textoBotones, String letras) {
        System.out.println("  " + textoBotones);
        while (true) {
            String t = leer("  Opcion: ").toUpperCase();
            if (t.length() == 1 && letras.indexOf(t.charAt(0)) >= 0) return t.charAt(0);
            aviso("Escriba una de estas letras: " + String.join(", ", letras.split("")) + ".");
        }
    }

    // Entero positivo; -1 si esta vacio o no es un entero positivo (CA-01.6, CA-07.8)
    static int idValido(String t) {
        try {
            int n = Integer.parseInt(t);
            return n > 0 ? n : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // HH:MM con dos digitos, de 00:00 a 23:59 (CA-01.5, CA-07.6: 8:00, 2:45, 14:60 y 25:00 se rechazan)
    static boolean horaValida(String t) {
        if (!t.matches("\\d{2}:\\d{2}")) return false;
        int h = Integer.parseInt(t.substring(0, 2));
        int m = Integer.parseInt(t.substring(3));
        return h <= 23 && m <= 59;
    }

    // dd/mm/aaaa con una fecha que exista (CA-07.4, CA-13.4: 31/02/2026 y 2026-11-02 se rechazan)
    static LocalDate fecha(String t) {
        if (!t.matches("\\d{2}/\\d{2}/\\d{4}")) return null;
        try {
            return LocalDate.of(Integer.parseInt(t.substring(6)), Integer.parseInt(t.substring(3, 5)),
                    Integer.parseInt(t.substring(0, 2)));
        } catch (DateTimeException e) {
            return null;
        }
    }

    static boolean diaValido(String t) {
        for (String d : DIAS) if (d.equals(t)) return true;
        return false;
    }

    // RN-04 . CA-07.5 - true si la fecha y la hora estan despues de este momento
    static boolean esFutura(String fecha, String hora) {
        return LocalDateTime.of(fecha(fecha), LocalTime.parse(hora)).isAfter(LocalDateTime.now());
    }

    // Dia de la semana de una fecha, escrito igual que dia_semana (02/11/2026 -> lunes)
    static String diaDe(String fecha) {
        return DIAS[fecha(fecha).getDayOfWeek().getValue() - 1];
    }

    static int minutos(String hhmm) {
        return Integer.parseInt(hhmm.substring(0, 2)) * 60 + Integer.parseInt(hhmm.substring(3));
    }

    // 270 -> "4 h 30 min"; 120 -> "2 h" (CA-05.1)
    static String horasYMinutos(int min) {
        return min / 60 + " h" + (min % 60 > 0 ? " " + min % 60 + " min" : "");
    }

    // Rellena con espacios a la derecha para alinear las columnas de los listados
    static String col(String t, int ancho) {
        StringBuilder s = new StringBuilder(t);
        while (s.length() < ancho) s.append(' ');
        return s.toString();
    }
}
