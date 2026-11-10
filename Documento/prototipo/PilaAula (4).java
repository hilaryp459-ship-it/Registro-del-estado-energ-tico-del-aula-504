// Línea 1: Importa la clase o las clases del paquete indicadas para poder utilizarlas. | Código
// original: import java.io.*;
import java.io.*;

// Línea 3: Declara una clase pública; aquí se agrupan los atributos y métodos de esta parte del
// programa. | Código original: public class ArchivoPersona {
public class ArchivoPersona {

    // Línea 5: Declara una constante de texto que almacena el nombre del archivo utilizado. | Código
    // original: private static final String NOMBRE_ARCHIVO = "personas.txt";
    private static final String NOMBRE_ARCHIVO = "personas.txt";

    // Línea 7: Comentario existente que describe o separa una sección del código. | Código original:
    // // ===============================================
    // ===============================================
    // Línea 8: Comentario existente que describe o separa una sección del código. | Código original:
    // // Guarda personas al salir
    // Guarda personas al salir
    // Línea 9: Comentario existente que describe o separa una sección del código. | Código original:
    // // ===============================================
    // ===============================================
    // Línea 10: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void guardar() {
    public static void guardar() {

        // Línea 12: Inicia un bloque de ejecución controlada para manejar posibles excepciones. |
        // Código original: try {
        try {

            // Línea 14: Crea un escritor que permite guardar texto en el archivo. | Código original:
            // PrintWriter pw = new PrintWriter(
            PrintWriter pw = new PrintWriter(
                    // Línea 15: Abre el archivo para escritura; si corresponde, permite guardar
                    // información en él. | Código original: new FileWriter(
                    new FileWriter(
                    // Línea 16: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: NOMBRE_ARCHIVO));
                    NOMBRE_ARCHIVO));

            // Línea 18: Inicia un ciclo for para repetir instrucciones según la condición establecida.
            // | Código original: for (int i = 0; i < PersonaCRUD.cantidadPersonas; i++) {
            for (int i = 0; i < PersonaCRUD.cantidadPersonas; i++) {

                // Línea 20: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                // sentencia. | Código original: Persona p = PersonaCRUD.personas[i];
                Persona p = PersonaCRUD.personas[i];

                // Línea 22: Escribe en el archivo una línea con los datos del registro separados por
                // punto y coma. | Código original: pw.println(
                pw.println(
                        // Línea 23: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: p.getIdPersona()
                        p.getIdPersona()
                        // Línea 24: Forma parte de la estructura del programa y cumple la función
                        // indicada por esta instrucción. | Código original: + ";"
                        + ";"
                        // Línea 25: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: + p.getNombre()
                        + p.getNombre()
                        // Línea 26: Forma parte de la estructura del programa y cumple la función
                        // indicada por esta instrucción. | Código original: + ";"
                        + ";"
                        // Línea 27: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: + p.getCorreo()
                        + p.getCorreo()
                        // Línea 28: Forma parte de la estructura del programa y cumple la función
                        // indicada por esta instrucción. | Código original: + ";"
                        + ";"
                        // Línea 29: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: + p.getEstado());
                        + p.getEstado());
            // Línea 30: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }

            // Línea 32: Cierra el recurso utilizado para liberar sus recursos y finalizar su uso. |
            // Código original: pw.close();
            pw.close();

            // Línea 34: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println(
            System.out.println(
                    // Línea 35: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: "\nPersonas guardadas correctamente.");
                    "\nPersonas guardadas correctamente.");

        // Línea 37: Forma parte de la estructura del programa y cumple la función indicada por esta
        // instrucción. | Código original: } catch (IOException e) {
        } catch (IOException e) {

            // Línea 39: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println(
            System.out.println(
                    // Línea 40: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: "\nError al guardar personas.");
                    "\nError al guardar personas.");
        // Línea 41: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
    // Línea 42: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 44: Comentario existente que describe o separa una sección del código. | Código original:
    // // ===============================================
    // ===============================================
    // Línea 45: Comentario existente que describe o separa una sección del código. | Código original:
    // // Carga personas al iniciar
    // Carga personas al iniciar
    // Línea 46: Comentario existente que describe o separa una sección del código. | Código original:
    // // ===============================================
    // ===============================================
    // Línea 47: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void cargar() {
    public static void cargar() {

        // Línea 49: Crea un objeto File que representa el archivo indicado. | Código original: File
        // archivo = new File(
        File archivo = new File(
                // Línea 50: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                // sentencia. | Código original: NOMBRE_ARCHIVO);
                NOMBRE_ARCHIVO);

        // Línea 52: Evalúa una condición y ejecuta el bloque asociado si se cumple. | Código original:
        // if (!archivo.exists()) {
        if (!archivo.exists()) {

            // Línea 54: Finaliza la ejecución del método sin devolver un valor. | Código original:
            // return;
            return;
        // Línea 55: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 57: Inicia un bloque de ejecución controlada para manejar posibles excepciones. |
        // Código original: try {
        try {

            // Línea 59: Crea un lector con búfer para leer el archivo de forma eficiente. | Código
            // original: BufferedReader br = new BufferedReader(
            BufferedReader br = new BufferedReader(
                    // Línea 60: Abre el archivo para leer su contenido. | Código original: new
                    // FileReader(
                    new FileReader(
                    // Línea 61: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: archivo));
                    archivo));

            // Línea 63: Ejecuta la instrucción indicada; el punto y coma marca el final de la
            // sentencia. | Código original: String linea;
            String linea;

            // Línea 65: Inicia un ciclo while que se repite mientras la condición sea verdadera. |
            // Código original: while ((linea = br.readLine()) != null) {
            while ((linea = br.readLine()) != null) {

                // Línea 67: Divide la línea de texto usando el separador indicado y almacena las
                // partes en un arreglo. | Código original: String[] datos = linea.split(";");
                String[] datos = linea.split(";");

                // Línea 69: Convierte el texto recibido en un número entero. | Código original: int id
                // = Integer.parseInt(
                int id = Integer.parseInt(
                        // Línea 70: Ejecuta la instrucción indicada; el punto y coma marca el final de
                        // la sentencia. | Código original: datos[0]);
                        datos[0]);

                // Línea 72: Obtiene el dato indicado y lo asigna a la variable correspondiente. |
                // Código original: String nombre = datos[1];
                String nombre = datos[1];

                // Línea 74: Obtiene el dato indicado y lo asigna a la variable correspondiente. |
                // Código original: String correo = datos[2];
                String correo = datos[2];

                // Línea 76: Obtiene el dato indicado y lo asigna a la variable correspondiente. |
                // Código original: String estado = datos[3];
                String estado = datos[3];

                // Línea 78: Crea un objeto Persona con los datos recibidos y lo almacena en el arreglo
                // correspondiente. | Código original:
                // PersonaCRUD.personas[PersonaCRUD.cantidadPersonas] = new Persona(
                PersonaCRUD.personas[PersonaCRUD.cantidadPersonas] = new Persona(
                        // Línea 79: Continúa la instrucción iniciada en las líneas cercanas, formando
                        // parte de la expresión o llamada correspondiente. | Código original: id,
                        id,
                        // Línea 80: Continúa la instrucción iniciada en las líneas cercanas, formando
                        // parte de la expresión o llamada correspondiente. | Código original: nombre,
                        nombre,
                        // Línea 81: Continúa la instrucción iniciada en las líneas cercanas, formando
                        // parte de la expresión o llamada correspondiente. | Código original: correo,
                        correo,
                        // Línea 82: Ejecuta la instrucción indicada; el punto y coma marca el final de
                        // la sentencia. | Código original: estado);
                        estado);

                // Línea 84: Aumenta en uno el contador de registros almacenados. | Código original:
                // PersonaCRUD.cantidadPersonas++;
                PersonaCRUD.cantidadPersonas++;
            // Línea 85: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }

            // Línea 87: Cierra el recurso utilizado para liberar sus recursos y finalizar su uso. |
            // Código original: br.close();
            br.close();

        // Línea 89: Forma parte de la estructura del programa y cumple la función indicada por esta
        // instrucción. | Código original: } catch (IOException e) {
        } catch (IOException e) {

            // Línea 91: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println(
            System.out.println(
                    // Línea 92: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: "\nError al cargar personas.");
                    "\nError al cargar personas.");
        // Línea 93: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
    // Línea 94: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }
// Línea 95: Cierra el bloque de código que se abrió anteriormente. | Código original: }
}
