import java.util.Scanner;

public class PersonaCRUD {

    static Persona[] personas = new Persona[100];
    static int cantidadPersonas = 0;

    static PilaPersona pila = new PilaPersona();

    static Scanner sc = new Scanner(System.in);

    public static boolean existePersona(int id) {

        for (int i = 0; i < cantidadPersonas; i++) {

            if (personas[i].getIdPersona() == id) {
                return true;
            }
        }

        return false;
    }

    public static void crearPersona() {

        System.out.println("\n=== CREAR PERSONA ===");

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (existePersona(id)) {
            System.out.println("La persona ya existe.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        personas[cantidadPersonas] =
                new Persona(id, nombre, correo, "Activo");

        cantidadPersonas++;

        System.out.println("Persona registrada.");
    }

    public static void buscarPersona() {

        System.out.print("Digite ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < cantidadPersonas; i++) {

            if (personas[i].getIdPersona() == id) {

                System.out.println(personas[i]);
                return;
            }
        }

        System.out.println("Persona no encontrada.");
    }

    public static void modificarPersona() {

        System.out.print("Digite ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidadPersonas; i++) {

            if (personas[i].getIdPersona() == id) {

                System.out.print("Nuevo Nombre: ");
                personas[i].setNombre(sc.nextLine());

                System.out.print("Nuevo Correo: ");
                personas[i].setCorreo(sc.nextLine());

                System.out.println("Persona actualizada.");
                return;
            }
        }

        System.out.println("Persona no encontrada.");
    }

    public static void eliminarPersona() {

        System.out.print("Digite ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < cantidadPersonas; i++) {

            if (personas[i].getIdPersona() == id) {

                if (pila.estaLlena()) {
                    System.out.println("No se puede eliminar: la pila de deshacer esta llena.");
                    return;
                }

                pila.apilar(personas[i]);

                for (int j = i; j < cantidadPersonas - 1; j++) {

                    personas[j] = personas[j + 1];
                }

                personas[cantidadPersonas - 1] = null;
                cantidadPersonas--;

                System.out.println("Persona eliminada.");

                return;
            }
        }

        System.out.println("Persona no encontrada.");
    }

    public static void deshacerPersona() {

        if (pila.estaVacia()) {
            System.out.println("No hay nada que deshacer.");
            return;
        }

        Persona p = pila.cima();

        if (existePersona(p.getIdPersona())) {
            System.out.println("No se puede deshacer: ya existe una persona con el ID " + p.getIdPersona() + ".");
            return;
        }

        if (cantidadPersonas == personas.length) {
            System.out.println("No se puede deshacer: el arreglo de personas esta lleno.");
            return;
        }

        pila.desapilar();
        personas[cantidadPersonas] = p;
        cantidadPersonas++;

        System.out.println("Persona recuperada: " + p);
    }

    public static void listarPersonas() {

        System.out.println("\n=== LISTADO PERSONAS ===");

        for (int i = 0; i < cantidadPersonas; i++) {

            System.out.println(personas[i]);
        }
    }
}
