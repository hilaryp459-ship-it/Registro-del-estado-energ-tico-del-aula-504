import java.io.*;

public class ArchivoAula {

    private static final String NOMBRE_ARCHIVO = "aulas.txt";

    public static void guardar() {

        try {

            PrintWriter pw = new PrintWriter(
                    new FileWriter(
                    NOMBRE_ARCHIVO));

            for (int i = 0; i < AulaCRUD.cantidadAulas; i++) {

                Aula a = AulaCRUD.aulas[i];

                pw.println(
                        a.getIdAula()
                        + ";"
                        + a.getNombreAula()
                        + ";"
                        + a.getUbicacion()
                        + ";"
                        + a.getEstado());
            }

            pw.close();

            System.out.println(
                    "\nAulas guardadas correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "\nError al guardar aulas.");
        }
    }

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

                String ubicacion = datos[2];

                String estado = datos[3];

                AulaCRUD.aulas[AulaCRUD.cantidadAulas] = new Aula(
                        id,
                        nombre,
                        ubicacion,
                        estado);

                AulaCRUD.cantidadAulas++;
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "\nError al cargar aulas.");
        }
    }
}
