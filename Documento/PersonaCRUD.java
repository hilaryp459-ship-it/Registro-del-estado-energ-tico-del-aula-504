import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        // Cargar datos guardados
        ArchivoPersona.cargar();
        ArchivoAula.cargar();

        Scanner sc = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("\n=================================");
            System.out.println("        MENU PRINCIPAL");
            System.out.println("=================================");

            System.out.println("\n------ PERSONAS ------");
            System.out.println("1. Crear Persona");
            System.out.println("2. Buscar Persona");
            System.out.println("3. Modificar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Listar Personas");
            System.out.println("11. Deshacer ultima eliminacion de Persona");

            System.out.println("\n-------- AULAS --------");
            System.out.println("6. Crear Aula");
            System.out.println("7. Buscar Aula");
            System.out.println("8. Modificar Aula");
            System.out.println("9. Eliminar Aula");
            System.out.println("10. Listar Aulas");
            System.out.println("12. Deshacer ultima eliminacion de Aula");

            System.out.println("\n0. Salir");

            System.out.print("\nSeleccione una opción: ");

            while (!sc.hasNextInt()) {

                System.out.println("Error: Debe ingresar un número.");
                sc.next();

                System.out.print("Seleccione una opción: ");
            }

            opcion = sc.nextInt();

            switch (opcion) {

                // PERSONAS

                case 1:
                    PersonaCRUD.crearPersona();
                    break;

                case 2:
                    PersonaCRUD.buscarPersona();
                    break;

                case 3:
                    PersonaCRUD.modificarPersona();
                    break;

                case 4:
                    PersonaCRUD.eliminarPersona();
                    break;

                case 5:
                    PersonaCRUD.listarPersonas();
                    break;

                    // AULAS

                case 6:
                    AulaCRUD.crearAula();
                    break;

                case 7:
                    AulaCRUD.buscarAula();
                    break;

                case 8:
                    AulaCRUD.modificarAula();
                    break;

                case 9:
                    AulaCRUD.eliminarAula();
                    break;

                case 10:
                    AulaCRUD.listarAulas();
                    break;

                case 11:
                    PersonaCRUD.deshacerPersona();
                    break;

                case 12:
                    AulaCRUD.deshacerAula();
                    break;

                    // SALIR

                case 0:

                    ArchivoPersona.guardar();
                    ArchivoAula.guardar();

                    System.out.println("\nDatos guardados correctamente.");
                    System.out.println("Programa finalizado.");

                    break;

                default:

                    System.out.println("\nOpción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
