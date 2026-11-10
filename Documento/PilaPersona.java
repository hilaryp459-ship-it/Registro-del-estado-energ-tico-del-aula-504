import java.util.Scanner;

public class AulaCRUD {

    static Aula[] aulas = new Aula[100];
    static int cantidadAulas = 0;

    static PilaAula pila = new PilaAula();

    static Scanner sc = new Scanner(System.in);

    public static boolean existeAula(int id) {

        for (int i = 0; i < cantidadAulas; i++) {

            if (aulas[i].getIdAula() == id) {
                return true;
            }
        }

        return false;
    }

    public static void crearAula() {

        System.out.println("\n=== CREAR AULA ===");

        System.out.print("ID Aula: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (existeAula(id)) {
            System.out.println("El aula ya existe.");
            return;
        }

        System.out.print("Nombre Aula: ");
        String nombre = sc.nextLine();

        System.out.print("Ubicacion: ");
        String ubicacion = sc.nextLine();

        aulas[cantidadAulas] =
                new Aula(id, nombre, ubicacion, "Activo");

        cantidadAulas++;

        System.out.println("Aula registrada.");
    }

    public static void buscarAula() {

        System.out.print("Digite ID Aula: ");
        int id = sc.nextInt();

        for (int i = 0; i < cantidadAulas; i++) {

            if (aulas[i].getIdAula() == id) {

                System.out.println(aulas[i]);
                return;
            }
        }

        System.out.println("Aula no encontrada.");
    }

    public static void modificarAula() {

        System.out.print("Digite ID Aula: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidadAulas; i++) {

            if (aulas[i].getIdAula() == id) {

                System.out.print("Nuevo Nombre Aula: ");
                aulas[i].setNombreAula(sc.nextLine());

                System.out.print("Nueva Ubicacion: ");
                aulas[i].setUbicacion(sc.nextLine());

                System.out.println("Aula actualizada.");
                return;
            }
        }

        System.out.println("Aula no encontrada.");
    }

    public static void eliminarAula() {

        System.out.print("Digite ID Aula: ");
        int id = sc.nextInt();

        for (int i = 0; i < cantidadAulas; i++) {

            if (aulas[i].getIdAula() == id) {

                if (pila.estaLlena()) {
                    System.out.println("No se puede eliminar: la pila de deshacer esta llena.");
                    return;
                }

                pila.apilar(aulas[i]);

                for (int j = i; j < cantidadAulas - 1; j++) {

                    aulas[j] = aulas[j + 1];
                }

                aulas[cantidadAulas - 1] = null;
                cantidadAulas--;

                System.out.println("Aula eliminada.");
                return;
            }
        }

        System.out.println("Aula no encontrada.");
    }

    public static void deshacerAula() {

        if (pila.estaVacia()) {
            System.out.println("No hay nada que deshacer.");
            return;
        }

        Aula a = pila.cima();

        if (existeAula(a.getIdAula())) {
            System.out.println("No se puede deshacer: ya existe un aula con el ID " + a.getIdAula() + ".");
            return;
        }

        if (cantidadAulas == aulas.length) {
            System.out.println("No se puede deshacer: el arreglo de aulas esta lleno.");
            return;
        }

        pila.desapilar();
        aulas[cantidadAulas] = a;
        cantidadAulas++;

        System.out.println("Aula recuperada: " + a);
    }

    public static void listarAulas() {

        System.out.println("\n=== LISTADO AULAS ===");

        for (int i = 0; i < cantidadAulas; i++) {

            System.out.println(aulas[i]);
        }
    }
}
