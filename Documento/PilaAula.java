import java.io.*;

public class ArchivoPersona {

    private static final String NOMBRE_ARCHIVO = "personas.txt";

    // ===============================================
    // Guarda personas al salir
    // ===============================================
    public static void guardar() {

        try {

            PrintWriter pw = new PrintWriter(
                    new FileWriter(
                    NOMBRE_ARCHIVO));

            for (int i = 0; i < PersonaCRUD.cantidadPersonas; i++) {

                Persona p = PersonaCRUD.personas[i];

                pw.println(
                        p.getIdPersona()
                        + ";"
                        + p.getNombre()
                        + ";"
                        + p.getCorreo()
                        + ";"
                        + p.getEstado());
            }

            pw.close();

            System.out.println(
                    "\nPersonas guardadas correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "\nError al guardar personas.");
        }
    }

    // ===============================================
    // Carga personas al iniciar
    // ===============================================
    public static void cargar() {

        File archivo = new File(
                NOMBRE_ARCHIVO);

        if (!archivo.exists()) {

            return;
        }

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(
                    archivo));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                int id = Integer.parseInt(
                        datos[0]);

                String nombre = datos[1];

                String correo = datos[2];

                String estado = datos[3];

                PersonaCRUD.personas[PersonaCRUD.cantidadPersonas] = new Persona(
                        id,
                        nombre,
                        correo,
                        estado);

                PersonaCRUD.cantidadPersonas++;
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "\nError al cargar personas.");
        }
    }
}
