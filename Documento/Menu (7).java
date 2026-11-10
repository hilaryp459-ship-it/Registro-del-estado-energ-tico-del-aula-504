// Línea 1: Importa la clase o las clases del paquete indicadas para poder utilizarlas. | Código
// original: import java.io.*;
import java.io.*;

// Línea 3: Declara una clase pública; aquí se agrupan los atributos y métodos de esta parte del
// programa. | Código original: public class ArchivoAula {
public class ArchivoAula {

    // Línea 5: Declara una constante de texto que almacena el nombre del archivo utilizado. | Código
    // original: private static final String NOMBRE_ARCHIVO = "aulas.txt";
    private static final String NOMBRE_ARCHIVO = "aulas.txt";

    // Línea 7: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void guardar() {
    public static void guardar() {

        // Línea 9: Inicia un bloque de ejecución controlada para manejar posibles excepciones. |
        // Código original: try {
        try {

            // Línea 11: Crea un escritor que permite guardar texto en el archivo. | Código original:
            // PrintWriter pw = new PrintWriter(
            PrintWriter pw = new PrintWriter(
                    // Línea 12: Abre el archivo para escritura; si corresponde, permite guardar
                    // información en él. | Código original: new FileWriter(
                    new FileWriter(
                    // Línea 13: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: NOMBRE_ARCHIVO));
                    NOMBRE_ARCHIVO));

            // Línea 15: Inicia un ciclo for para repetir instrucciones según la condición establecida.
            // | Código original: for (int i = 0; i < AulaCRUD.cantidadAulas; i++) {
            for (int i = 0; i < AulaCRUD.cantidadAulas; i++) {

                // Línea 17: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                // sentencia. | Código original: Aula a = AulaCRUD.aulas[i];
                Aula a = AulaCRUD.aulas[i];

                // Línea 19: Escribe en el archivo una línea con los datos del registro separados por
                // punto y coma. | Código original: pw.println(
                pw.println(
                        // Línea 20: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: a.getIdAula()
                        a.getIdAula()
                        // Línea 21: Forma parte de la estructura del programa y cumple la función
                        // indicada por esta instrucción. | Código original: + ";"
                        + ";"
                        // Línea 22: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: + a.getNombreAula()
                        + a.getNombreAula()
                        // Línea 23: Forma parte de la estructura del programa y cumple la función
                        // indicada por esta instrucción. | Código original: + ";"
                        + ";"
                        // Línea 24: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: + a.getUbicacion()
                        + a.getUbicacion()
                        // Línea 25: Forma parte de la estructura del programa y cumple la función
                        // indicada por esta instrucción. | Código original: + ";"
                        + ";"
                        // Línea 26: Llama al método getter para consultar el valor del atributo
                        // indicado. | Código original: + a.getEstado());
                        + a.getEstado());
            // Línea 27: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }

            // Línea 29: Cierra el recurso utilizado para liberar sus recursos y finalizar su uso. |
            // Código original: pw.close();
            pw.close();

            // Línea 31: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println(
            System.out.println(
                    // Línea 32: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: "\nAulas guardadas correctamente.");
                    "\nAulas guardadas correctamente.");

        // Línea 34: Forma parte de la estructura del programa y cumple la función indicada por esta
        // instrucción. | Código original: } catch (IOException e) {
        } catch (IOException e) {

            // Línea 36: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println(
            System.out.println(
                    // Línea 37: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: "\nError al guardar aulas.");
                    "\nError al guardar aulas.");
        // Línea 38: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
    // Línea 39: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 41: Declara un método público y estático que puede invocarse desde otras clases. | Código
    // original: public static void cargar() {
    public static void cargar() {

        // Línea 43: Crea un objeto File que representa el archivo indicado. | Código original: File
        // archivo = new File(
        File archivo = new File(
                // Línea 44: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                // sentencia. | Código original: NOMBRE_ARCHIVO);
                NOMBRE_ARCHIVO);

        // Línea 46: Evalúa una condición y ejecuta el bloque asociado si se cumple. | Código original:
        // if (!archivo.exists()) {
        if (!archivo.exists()) {

            // Línea 48: Finaliza la ejecución del método sin devolver un valor. | Código original:
            // return;
            return;
        // Línea 49: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }

        // Línea 51: Inicia un bloque de ejecución controlada para manejar posibles excepciones. |
        // Código original: try {
        try {

            // Línea 53: Crea un lector con búfer para leer el archivo de forma eficiente. | Código
            // original: BufferedReader br = new BufferedReader(
            BufferedReader br = new BufferedReader(
                    // Línea 54: Abre el archivo para leer su contenido. | Código original: new
                    // FileReader(
                    new FileReader(
                    // Línea 55: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: archivo));
                    archivo));

            // Línea 57: Ejecuta la instrucción indicada; el punto y coma marca el final de la
            // sentencia. | Código original: String linea;
            String linea;

            // Línea 59: Inicia un ciclo while que se repite mientras la condición sea verdadera. |
            // Código original: while ((linea = br.readLine()) != null) {
            while ((linea = br.readLine()) != null) {

                // Línea 61: Divide la línea de texto usando el separador indicado y almacena las
                // partes en un arreglo. | Código original: String[] datos = linea.split(";");
                String[] datos = linea.split(";");

                // Línea 63: Convierte el texto recibido en un número entero. | Código original: int id
                // = Integer.parseInt(
                int id = Integer.parseInt(
                        // Línea 64: Ejecuta la instrucción indicada; el punto y coma marca el final de
                        // la sentencia. | Código original: datos[0]);
                        datos[0]);

                // Línea 66: Obtiene el dato indicado y lo asigna a la variable correspondiente. |
                // Código original: String nombre = datos[1];
                String nombre = datos[1];

                // Línea 68: Obtiene el dato indicado y lo asigna a la variable correspondiente. |
                // Código original: String ubicacion = datos[2];
                String ubicacion = datos[2];

                // Línea 70: Obtiene el dato indicado y lo asigna a la variable correspondiente. |
                // Código original: String estado = datos[3];
                String estado = datos[3];

                // Línea 72: Crea un objeto Aula con los datos recibidos y lo almacena en el arreglo
                // correspondiente. | Código original: AulaCRUD.aulas[AulaCRUD.cantidadAulas] = new
                // Aula(
                AulaCRUD.aulas[AulaCRUD.cantidadAulas] = new Aula(
                        // Línea 73: Continúa la instrucción iniciada en las líneas cercanas, formando
                        // parte de la expresión o llamada correspondiente. | Código original: id,
                        id,
                        // Línea 74: Continúa la instrucción iniciada en las líneas cercanas, formando
                        // parte de la expresión o llamada correspondiente. | Código original: nombre,
                        nombre,
                        // Línea 75: Continúa la instrucción iniciada en las líneas cercanas, formando
                        // parte de la expresión o llamada correspondiente. | Código original:
                        // ubicacion,
                        ubicacion,
                        // Línea 76: Ejecuta la instrucción indicada; el punto y coma marca el final de
                        // la sentencia. | Código original: estado);
                        estado);

                // Línea 78: Aumenta en uno el contador de registros almacenados. | Código original:
                // AulaCRUD.cantidadAulas++;
                AulaCRUD.cantidadAulas++;
            // Línea 79: Cierra el bloque de código que se abrió anteriormente. | Código original: }
            }

            // Línea 81: Cierra el recurso utilizado para liberar sus recursos y finalizar su uso. |
            // Código original: br.close();
            br.close();

        // Línea 83: Forma parte de la estructura del programa y cumple la función indicada por esta
        // instrucción. | Código original: } catch (IOException e) {
        } catch (IOException e) {

            // Línea 85: Muestra un mensaje en consola sin realizar un salto de línea al finalizar. |
            // Código original: System.out.println(
            System.out.println(
                    // Línea 86: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                    // sentencia. | Código original: "\nError al cargar aulas.");
                    "\nError al cargar aulas.");
        // Línea 87: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
    // Línea 88: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }
// Línea 89: Cierra el bloque de código que se abrió anteriormente. | Código original: }
}
