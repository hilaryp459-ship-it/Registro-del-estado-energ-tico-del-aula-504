// Línea 1: Importa la clase o las clases del paquete indicadas para poder utilizarlas. | Código
// original: import java.util.Scanner;
import java.util.Scanner;

// Línea 3: Declara una clase pública; aquí se agrupan los atributos y métodos de esta parte del
// programa. | Código original: public class Menu {
public class Menu {

    // Línea 5: Define el método principal, punto de entrada desde el que comienza la ejecución del
    // programa. | Código original: public static void main(String[] args) {
    public static void main(String[] args) {

        // Línea 7: Comentario existente que describe o separa una sección del código. | Código
        // original: // Cargar datos guardados
        // Cargar datos guardados
        // Línea 8: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: ArchivoPersona.cargar();
        ArchivoPersona.cargar();
        // Línea 9: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: ArchivoAula.cargar();
        ArchivoAula.cargar();

        // Línea 11: Crea un objeto Scanner para leer los datos que ingresa el usuario por consola. |
        // Código original: Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        // Línea 13: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: int opcion;
        int opcion;

        // Línea 15: Inicia un ciclo do-while, cuyo bloque se ejecuta al menos una vez. | Código
        // original: do {
        do {

            // Línea 17: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("\n=================================");
            System.out.println("\n=================================");
            // Línea 18: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("        MENU PRINCIPAL");
            System.out.println("        MENU PRINCIPAL");
            // Línea 19: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("=================================");
            System.out.println("=================================");

            // Línea 21: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("\n------ PERSONAS ------");
            System.out.println("\n------ PERSONAS ------");
            // Línea 22: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("1. Crear Persona");
            System.out.println("1. Crear Persona");
            // Línea 23: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("2. Buscar Persona");
            System.out.println("2. Buscar Persona");
            // Línea 24: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("3. Modificar Persona");
            System.out.println("3. Modificar Persona");
            // Línea 25: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("4. Eliminar Persona");
            System.out.println("4. Eliminar Persona");
            // Línea 26: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("5. Listar Personas");
            System.out.println("5. Listar Personas");
            // Línea 27: Muestra la opción 11 del menú: deshacer la última eliminación de persona. |
            // Código original: System.out.println("11. Deshacer ultima eliminacion de Persona");
            System.out.println("11. Deshacer ultima eliminacion de Persona");

            // Línea 29: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("\n-------- AULAS --------");
            System.out.println("\n-------- AULAS --------");
            // Línea 30: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("6. Crear Aula");
            System.out.println("6. Crear Aula");
            // Línea 31: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("7. Buscar Aula");
            System.out.println("7. Buscar Aula");
            // Línea 32: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("8. Modificar Aula");
            System.out.println("8. Modificar Aula");
            // Línea 33: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("9. Eliminar Aula");
            System.out.println("9. Eliminar Aula");
            // Línea 34: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("10. Listar Aulas");
            System.out.println("10. Listar Aulas");
            // Línea 35: Muestra la opción 12 del menú: deshacer la última eliminación de aula. |
            // Código original: System.out.println("12. Deshacer ultima eliminacion de Aula");
            System.out.println("12. Deshacer ultima eliminacion de Aula");

            // Línea 37: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("\n0. Salir");
            System.out.println("\n0. Salir");

            // Línea 39: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.print("\nSeleccione una opción: ");
            System.out.print("\nSeleccione una opción: ");

            // Línea 41: Inicia un ciclo while que se repite mientras la condición sea verdadera. |
            // Código original: while (!sc.hasNextInt()) {
            while (!sc.hasNextInt()) {

                // Línea 43: Muestra un mensaje en consola sin realizar un salto de línea al finalizar.
                // | Código original: System.out.println("Error: Debe ingresar un número.");
                System.out.println("Error: Debe ingresar un número.");
                // Línea 44: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                // sentencia. | Código original: sc.next();
                sc.next();

                // Línea 46: Muestra un mensaje en consola sin realizar un salto de línea al finalizar.
                // | Código original: System.out.print("Seleccione una opción: ");
                System.out.print("Seleccione una opción: ");
            // Línea 47: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }

            // Línea 49: Lee un número entero ingresado por el usuario. | Código original: opcion =
            // sc.nextInt();
            opcion = sc.nextInt();

            // Línea 51: Evalúa una expresión y permite seleccionar una opción mediante casos. | Código
            // original: switch (opcion) {
            switch (opcion) {

                // Línea 53: Comentario existente que describe o separa una sección del código. |
                // Código original: // PERSONAS
                // PERSONAS

                // Línea 55: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 1:
                case 1:
                    // Línea 56: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: PersonaCRUD.crearPersona();
                    PersonaCRUD.crearPersona();
                    // Línea 57: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 59: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 2:
                case 2:
                    // Línea 60: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: PersonaCRUD.buscarPersona();
                    PersonaCRUD.buscarPersona();
                    // Línea 61: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 63: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 3:
                case 3:
                    // Línea 64: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: PersonaCRUD.modificarPersona();
                    PersonaCRUD.modificarPersona();
                    // Línea 65: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 67: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 4:
                case 4:
                    // Línea 68: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: PersonaCRUD.eliminarPersona();
                    PersonaCRUD.eliminarPersona();
                    // Línea 69: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 71: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 5:
                case 5:
                    // Línea 72: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: PersonaCRUD.listarPersonas();
                    PersonaCRUD.listarPersonas();
                    // Línea 73: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                    // Línea 75: Comentario existente que describe o separa una sección del código. |
                    // Código original: // AULAS
                    // AULAS

                // Línea 77: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 6:
                case 6:
                    // Línea 78: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: AulaCRUD.crearAula();
                    AulaCRUD.crearAula();
                    // Línea 79: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 81: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 7:
                case 7:
                    // Línea 82: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: AulaCRUD.buscarAula();
                    AulaCRUD.buscarAula();
                    // Línea 83: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 85: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 8:
                case 8:
                    // Línea 86: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: AulaCRUD.modificarAula();
                    AulaCRUD.modificarAula();
                    // Línea 87: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 89: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 9:
                case 9:
                    // Línea 90: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: AulaCRUD.eliminarAula();
                    AulaCRUD.eliminarAula();
                    // Línea 91: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 93: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 10:
                case 10:
                    // Línea 94: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: AulaCRUD.listarAulas();
                    AulaCRUD.listarAulas();
                    // Línea 95: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 97: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 11:
                case 11:
                    // Línea 98: Llama al método que deshace la última eliminación de persona. | Código
                    // original: PersonaCRUD.deshacerPersona();
                    PersonaCRUD.deshacerPersona();
                    // Línea 99: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 101: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 12:
                case 12:
                    // Línea 102: Llama al método que deshace la última eliminación de aula. | Código
                    // original: AulaCRUD.deshacerAula();
                    AulaCRUD.deshacerAula();
                    // Línea 103: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                    // Línea 105: Comentario existente que describe o separa una sección del código. |
                    // Código original: // SALIR
                    // SALIR

                // Línea 107: Indica el caso del menú que se ejecuta cuando coincide el valor de la
                // opción. | Código original: case 0:
                case 0:

                    // Línea 109: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: ArchivoPersona.guardar();
                    ArchivoPersona.guardar();
                    // Línea 110: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: ArchivoAula.guardar();
                    ArchivoAula.guardar();

                    // Línea 112: Muestra un mensaje en consola sin realizar un salto de línea al
                    // finalizar. | Código original: System.out.println("\nDatos guardados
                    // correctamente.");
                    System.out.println("\nDatos guardados correctamente.");
                    // Línea 113: Muestra un mensaje en consola sin realizar un salto de línea al
                    // finalizar. | Código original: System.out.println("Programa finalizado.");
                    System.out.println("Programa finalizado.");

                    // Línea 115: Interrumpe el ciclo o la estructura switch en la que se encuentra. |
                    // Código original: break;
                    break;

                // Línea 117: Define la acción que se ejecuta cuando ningún caso coincide. | Código
                // original: default:
                default:

                    // Línea 119: Muestra un mensaje en consola sin realizar un salto de línea al
                    // finalizar. | Código original: System.out.println("\nOpción inválida.");
                    System.out.println("\nOpción inválida.");
            // Línea 120: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }

        // Línea 122: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia.
        // | Código original: } while (opcion != 0);
        } while (opcion != 0);

        // Línea 124: Cierra el recurso utilizado para liberar sus recursos y finalizar su uso. |
        // Código original: sc.close();
        sc.close();
    // Línea 125: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }
// Línea 126: Cierra el bloque de código que se abrió anteriormente. | Código original: }
}
