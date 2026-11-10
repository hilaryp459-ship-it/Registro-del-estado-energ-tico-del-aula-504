// Línea 1: Importa la clase o las clases del paquete indicadas para poder utilizarlas. | Código
// original: import java.util.Scanner;
import java.util.Scanner;

// Línea 3: Declara una clase pública; aquí se agrupan los atributos y métodos de esta parte del
// programa. | Código original: public class AulaCRUD {
public class AulaCRUD {

    // Línea 5: Declara e inicializa un atributo compartido por toda la clase. | Código original:
    // static Aula[] aulas = new Aula[100];
    static Aula[] aulas = new Aula[100];
    // Línea 6: Declara e inicializa un atributo compartido por toda la clase. | Código original:
    // static int cantidadAulas = 0;
    static int cantidadAulas = 0;

    // Línea 8: Crea la pila propia de deshacer de las aulas; se comparte en toda la clase. | Código
    // original: static PilaAula pila = new PilaAula();
    static PilaAula pila = new PilaAula();

    // Línea 10: Declara e inicializa un atributo compartido por toda la clase. | Código original:
    // static Scanner sc = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);

    // Línea 12: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static boolean existeAula(int id) {
    public static boolean existeAula(int id) {

        // Línea 14: Inicia un ciclo for para repetir instrucciones según la condición establecida. |
        // Código original: for (int i = 0; i < cantidadAulas; i++) {
        for (int i = 0; i < cantidadAulas; i++) {

            // Línea 16: Evalúa una condición y ejecuta el bloque asociado si se cumple. | Código
            // original: if (aulas[i].getIdAula() == id) {
            if (aulas[i].getIdAula() == id) {
                // Línea 17: Devuelve true para indicar que se apiló correctamente. | Código original:
                // return true;
                return true;
            // Línea 18: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }
        // Línea 19: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 21: Devuelve false y finaliza el método. | Código original: return false;
        return false;
    // Línea 22: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 24: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void crearAula() {
    public static void crearAula() {

        // Línea 26: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.println("\n=== CREAR AULA ===");
        System.out.println("\n=== CREAR AULA ===");

        // Línea 28: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.print("ID Aula: ");
        System.out.print("ID Aula: ");
        // Línea 29: Lee un número entero ingresado por el usuario. | Código original: int id =
        // sc.nextInt();
        int id = sc.nextInt();
        // Línea 30: Lee una línea completa de texto ingresada por el usuario. | Código original:
        // sc.nextLine();
        sc.nextLine();

        // Línea 32: Evalúa una condición y ejecuta el bloque asociado si se cumple. | Código original:
        // if (existeAula(id)) {
        if (existeAula(id)) {
            // Línea 33: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println("El aula ya existe.");
            System.out.println("El aula ya existe.");
            // Línea 34: Finaliza la ejecución del método sin devolver un valor. | Código original:
            // return;
            return;
        // Línea 35: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 37: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.print("Nombre Aula: ");
        System.out.print("Nombre Aula: ");
        // Línea 38: Lee una línea completa de texto ingresada por el usuario. | Código original:
        // String nombre = sc.nextLine();
        String nombre = sc.nextLine();

        // Línea 40: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.print("Ubicacion: ");
        System.out.print("Ubicacion: ");
        // Línea 41: Lee una línea completa de texto ingresada por el usuario. | Código original:
        // String ubicacion = sc.nextLine();
        String ubicacion = sc.nextLine();

        // Línea 43: Forma parte de la estructura del programa y cumple la función indicada por esta
        // instrucción. | Código original: aulas[cantidadAulas] =
        aulas[cantidadAulas] =
                // Línea 44: Crea un objeto Aula con los datos recibidos y lo almacena en el arreglo
                // correspondiente. | Código original: new Aula(id, nombre, ubicacion, "Activo");
                new Aula(id, nombre, ubicacion, "Activo");

        // Línea 46: Aumenta en uno el contador de registros almacenados. | Código original:
        // cantidadAulas++;
        cantidadAulas++;

        // Línea 48: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.println("Aula registrada.");
        System.out.println("Aula registrada.");
    // Línea 49: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 51: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void buscarAula() {
    public static void buscarAula() {

        // Línea 53: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.print("Digite ID Aula: ");
        System.out.print("Digite ID Aula: ");
        // Línea 54: Lee un número entero ingresado por el usuario. | Código original: int id =
        // sc.nextInt();
        int id = sc.nextInt();

        // Línea 56: Inicia un ciclo for para repetir instrucciones según la condición establecida. |
        // Código original: for (int i = 0; i < cantidadAulas; i++) {
        for (int i = 0; i < cantidadAulas; i++) {

            // Línea 58: Evalúa una condición y ejecuta el bloque asociado si se cumple. | Código
            // original: if (aulas[i].getIdAula() == id) {
            if (aulas[i].getIdAula() == id) {

                // Línea 60: Muestra un mensaje en consola sin realizar un salto de línea al finalizar.
                // | Código original: System.out.println(aulas[i]);
                System.out.println(aulas[i]);
                // Línea 61: Finaliza la ejecución del método sin devolver un valor. | Código original:
                // return;
                return;
            // Línea 62: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }
        // Línea 63: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 65: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.println("Aula no encontrada.");
        System.out.println("Aula no encontrada.");
    // Línea 66: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 68: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void modificarAula() {
    public static void modificarAula() {

        // Línea 70: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.print("Digite ID Aula: ");
        System.out.print("Digite ID Aula: ");
        // Línea 71: Lee un número entero ingresado por el usuario. | Código original: int id =
        // sc.nextInt();
        int id = sc.nextInt();
        // Línea 72: Lee una línea completa de texto ingresada por el usuario. | Código original:
        // sc.nextLine();
        sc.nextLine();

        // Línea 74: Inicia un ciclo for para repetir instrucciones según la condición establecida. |
        // Código original: for (int i = 0; i < cantidadAulas; i++) {
        for (int i = 0; i < cantidadAulas; i++) {

            // Línea 76: Evalúa una condición y ejecuta el bloque asociado si se cumple. | Código
            // original: if (aulas[i].getIdAula() == id) {
            if (aulas[i].getIdAula() == id) {

                // Línea 78: Muestra un mensaje en consola sin realizar un salto de línea al finalizar.
                // | Código original: System.out.print("Nuevo Nombre Aula: ");
                System.out.print("Nuevo Nombre Aula: ");
                // Línea 79: Lee una línea completa de texto ingresada por el usuario. | Código
                // original: aulas[i].setNombreAula(sc.nextLine());
                aulas[i].setNombreAula(sc.nextLine());

                // Línea 81: Muestra un mensaje en consola sin realizar un salto de línea al finalizar.
                // | Código original: System.out.print("Nueva Ubicacion: ");
                System.out.print("Nueva Ubicacion: ");
                // Línea 82: Lee una línea completa de texto ingresada por el usuario. | Código
                // original: aulas[i].setUbicacion(sc.nextLine());
                aulas[i].setUbicacion(sc.nextLine());

                // Línea 84: Muestra un mensaje en consola sin realizar un salto de línea al finalizar.
                // | Código original: System.out.println("Aula actualizada.");
                System.out.println("Aula actualizada.");
                // Línea 85: Finaliza la ejecución del método sin devolver un valor. | Código original:
                // return;
                return;
            // Línea 86: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }
        // Línea 87: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 89: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.println("Aula no encontrada.");
        System.out.println("Aula no encontrada.");
    // Línea 90: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 92: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void eliminarAula() {
    public static void eliminarAula() {

        // Línea 94: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.print("Digite ID Aula: ");
        System.out.print("Digite ID Aula: ");
        // Línea 95: Lee un número entero ingresado por el usuario. | Código original: int id =
        // sc.nextInt();
        int id = sc.nextInt();

        // Línea 97: Inicia un ciclo for para repetir instrucciones según la condición establecida. |
        // Código original: for (int i = 0; i < cantidadAulas; i++) {
        for (int i = 0; i < cantidadAulas; i++) {

            // Línea 99: Evalúa una condición y ejecuta el bloque asociado si se cumple. | Código
            // original: if (aulas[i].getIdAula() == id) {
            if (aulas[i].getIdAula() == id) {

                // Línea 101: Evalúa si la pila de deshacer está llena; si lo está, no se elimina para
                // no perder la copia. | Código original: if (pila.estaLlena()) {
                if (pila.estaLlena()) {
                    // Línea 102: Muestra el aviso de que no se puede eliminar porque la pila está
                    // llena. | Código original: System.out.println("No se puede eliminar: la pila de
                    // deshacer esta llena.");
                    System.out.println("No se puede eliminar: la pila de deshacer esta llena.");
                    // Línea 103: Finaliza la ejecución del método sin devolver un valor. | Código
                    // original: return;
                    return;
                // Línea 104: Cierra el bloque de código que se abrió anteriormente. | Código original:
                // }
                }

                // Línea 106: Guarda en la pila el aula que se va a eliminar, para poder recuperarla
                // con deshacer. | Código original: pila.apilar(aulas[i]);
                pila.apilar(aulas[i]);

                // Línea 108: Inicia un ciclo for para repetir instrucciones según la condición
                // establecida. | Código original: for (int j = i; j < cantidadAulas - 1; j++) {
                for (int j = i; j < cantidadAulas - 1; j++) {

                    // Línea 110: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: aulas[j] = aulas[j + 1];
                    aulas[j] = aulas[j + 1];
                // Línea 111: Cierra el bloque de código que se abrió anteriormente. | Código original:
                // }
                }

                // Línea 113: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                // sentencia. | Código original: aulas[cantidadAulas - 1] = null;
                aulas[cantidadAulas - 1] = null;
                // Línea 114: Disminuye en uno el contador de registros almacenados. | Código original:
                // cantidadAulas--;
                cantidadAulas--;

                // Línea 116: Muestra un mensaje en consola sin realizar un salto de línea al
                // finalizar. | Código original: System.out.println("Aula eliminada.");
                System.out.println("Aula eliminada.");
                // Línea 117: Finaliza la ejecución del método sin devolver un valor. | Código
                // original: return;
                return;
            // Línea 118: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }
        // Línea 119: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 121: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.println("Aula no encontrada.");
        System.out.println("Aula no encontrada.");
    // Línea 122: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 124: Declara el método que deshace la última eliminación de un aula usando la pila. |
    // Código original: public static void deshacerAula() {
    public static void deshacerAula() {

        // Línea 126: Evalúa si la pila está vacía, es decir, si no se ha eliminado nada. | Código
        // original: if (pila.estaVacia()) {
        if (pila.estaVacia()) {
            // Línea 127: Muestra un mensaje (no un error) cuando no hay eliminaciones para deshacer. |
            // Código original: System.out.println("No hay nada que deshacer.");
            System.out.println("No hay nada que deshacer.");
            // Línea 128: Finaliza la ejecución del método sin devolver un valor. | Código original:
            // return;
            return;
        // Línea 129: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 131: Mira el aula de arriba de la pila sin sacarla todavía. | Código original: Aula a
        // = pila.cima();
        Aula a = pila.cima();

        // Línea 133: Evalúa si ya existe otra aula con ese ID (se creó después de eliminar). | Código
        // original: if (existeAula(a.getIdAula())) {
        if (existeAula(a.getIdAula())) {
            // Línea 134: Avisa que no se recupera para no repetir el ID; el aula sigue en la pila. |
            // Código original: System.out.println("No se puede deshacer: ya existe un aula con el ID "
            // + a.getIdAula() + ".");
            System.out.println("No se puede deshacer: ya existe un aula con el ID " + a.getIdAula() + ".");
            // Línea 135: Finaliza la ejecución del método sin devolver un valor. | Código original:
            // return;
            return;
        // Línea 136: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 138: Evalúa si el arreglo de aulas está lleno (100 aulas). | Código original: if
        // (cantidadAulas == aulas.length) {
        if (cantidadAulas == aulas.length) {
            // Línea 139: Avisa que no hay espacio para recuperar el aula; sigue en la pila. | Código
            // original: System.out.println("No se puede deshacer: el arreglo de aulas esta lleno.");
            System.out.println("No se puede deshacer: el arreglo de aulas esta lleno.");
            // Línea 140: Finaliza la ejecución del método sin devolver un valor. | Código original:
            // return;
            return;
        // Línea 141: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 143: Ahora sí saca el elemento de la pila, porque ya se comprobó que se puede
        // recuperar. | Código original: pila.desapilar();
        pila.desapilar();
        // Línea 144: Devuelve el aula al arreglo, en la primera casilla libre (queda de última). |
        // Código original: aulas[cantidadAulas] = a;
        aulas[cantidadAulas] = a;
        // Línea 145: Aumenta en uno el contador de registros almacenados. | Código original:
        // cantidadAulas++;
        cantidadAulas++;

        // Línea 147: Muestra el aula recuperada. | Código original: System.out.println("Aula
        // recuperada: " + a);
        System.out.println("Aula recuperada: " + a);
    // Línea 148: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 150: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void listarAulas() {
    public static void listarAulas() {

        // Línea 152: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
        // Código original: System.out.println("\n=== LISTADO AULAS ===");
        System.out.println("\n=== LISTADO AULAS ===");

        // Línea 154: Inicia un ciclo for para repetir instrucciones según la condición establecida. |
        // Código original: for (int i = 0; i < cantidadAulas; i++) {
        for (int i = 0; i < cantidadAulas; i++) {

            // Línea 156: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println(aulas[i]);
            System.out.println(aulas[i]);
        // Línea 157: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
    // Línea 158: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }
// Línea 159: Cierra el bloque de código que se abrió anteriormente. | Código original: }
}
