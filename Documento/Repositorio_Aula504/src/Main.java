/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 *                  comparado con la programacion academica
 * Entidad        : Pantalla principal (une las dos entidades) - Figura 16
 * Programado por : Hilary Perez Zuniga y Luijeimm Agudelo Godoy
 * Fecha          : 04/11/2026 (ajustado a los mockups el 05/11/2026)
 * -----------------------------------------------------------
 * Al iniciar carga programacion.txt y registros.txt (RF-26, RF-60);
 * si no existen, avisa y sigue (RF-28, RF-62).
 * 1 y 2 abren el menu de cada entidad; 3 y 4 abren las consultas
 * (CU-13, CU-14); 0 cierra el programa.
 * RNF-02 Los datos se guardan despues de cada cambio y siguen al volver a abrir
 * RNF-05 Un error inesperado no cierra el programa: se avisa y se vuelve al menu
 * =========================================================== */
public class Main {
    public static void main(String[] args) {
        GestorProgramacion.cargar();
        GestorRegistro.cargar();
        System.out.println("Al iniciar: se cargaron " + GestorProgramacion.contador + " bloques (programacion.txt) y "
                + GestorRegistro.contador + " registros (registros.txt).");
        while (true) {
            System.out.println();
            System.out.println("========  REGISTRO DEL ESTADO ENERGETICO - AULA 504  ========");
            System.out.println("  bloques: " + GestorProgramacion.contador + "   registros: " + GestorRegistro.contador);
            System.out.println();
            System.out.println("  1. Programacion academica");
            System.out.println("  2. Registros del estado del aula");
            System.out.println("  3. Historial por rango de fechas ..... CU-13 / HU-13");
            System.out.println("  4. Detalle de la comparacion ......... CU-14 / HU-14");
            System.out.println("  0. Salir (los datos ya quedaron guardados)");
            System.out.println();
            int opc = Util.opcion();
            try {
                if (opc == 1) GestorProgramacion.menu();
                else if (opc == 2) GestorRegistro.menu();
                else if (opc == 3) GestorRegistro.historial();
                else if (opc == 4) GestorRegistro.detalle();
                else if (opc == 0) {
                    System.out.println("Los datos quedaron guardados en programacion.txt y registros.txt.");
                    return;
                }
                else Util.aviso("Opcion no valida. Escriba 1, 2, 3, 4 o 0.");
            } catch (RuntimeException e) {
                // RNF-05 - cualquier error inesperado se informa y el programa sigue
                Util.aviso("Ocurrio un error inesperado (" + e.getClass().getSimpleName() + "). El programa sigue en el menu.");
            }
        }
    }
}
