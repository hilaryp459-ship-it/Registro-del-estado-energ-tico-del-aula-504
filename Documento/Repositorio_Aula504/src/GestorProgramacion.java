/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 *                  comparado con la programacion academica
 * Entidad        : PROGRAMACION_ACADEMICA (maestra)
 * Programado por : Hilary Perez Zuniga
 * Fecha          : 04/11/2026 (ajustado a los mockups el 05/11/2026)
 * -----------------------------------------------------------
 * HU-01 . CU-01  Registrar bloque ..................... RF-01 a RF-08
 * HU-02 . CU-02  Consultar por id y buscar por dia ..... RF-09 a RF-14
 * HU-03 . CU-03  Actualizar bloque .................... RF-15 a RF-19
 * HU-04 . CU-04  Eliminar bloque ...................... RF-20 a RF-23, RF-79
 * HU-05 . CU-05  Listar bloques con duracion ........... RF-24 a RF-28
 * HU-06 . CU-06  Deshacer ultima eliminacion ........... RF-29 a RF-33 ("extend" de CU-04)
 * CU-15          Validar superposicion ................. "include" desde CU-01, CU-03 y CU-06
 * RN-02  Un bloque cubre una hora si hora_inicio <= hora < hora_fin
 * RN-03  Dos bloques del mismo dia no se superponen; los contiguos si se permiten
 * RNF-02 Se guarda despues de cada cambio exitoso y se carga al iniciar
 * RNF-04 Cada falla avisa la causa en la linea MENSAJE
 * -----------------------------------------------------------
 * Criterios: CA-01.1 a CA-06.6 (Tabla 29)
 * Pantallas: Figuras 17 a 20; mensajes: Tabla 56
 * Estructuras: Arreglo con contador (100) . Pila propia (100)
 * Punto de contacto: GestorRegistro.comparar() llama a hayBloquesEl() y bloqueQueCubre()
 * =========================================================== */
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestorProgramacion {
    static ProgramacionAcademica[] bloques = new ProgramacionAcademica[100];
    static int contador = 0;
    static PilaProgramacion pila = new PilaProgramacion();
    static final String ARCHIVO = "programacion.txt";

    // Figura 17 - menu de la programacion academica
    static void menu() {
        while (true) {
            System.out.println();
            System.out.println("PROGRAMACION ACADEMICA - AULA 504");
            System.out.println("------------------------------------------------------------");
            System.out.println("  1. Registrar bloque ....................... CU-01 / HU-01 / RF-01");
            System.out.println("  2. Consultar bloque por identificador ..... CU-02 / HU-02 / RF-09");
            System.out.println("  3. Buscar bloques por dia ................. CU-02 / HU-02 / RF-12");
            System.out.println("  4. Actualizar bloque ...................... CU-03 / HU-03 / RF-15");
            System.out.println("  5. Eliminar bloque ........................ CU-04 / HU-04 / RF-20");
            System.out.println("  6. Listar bloques ......................... CU-05 / HU-05 / RF-24");
            System.out.println("  7. Deshacer ultima eliminacion ............ CU-06 / HU-06 / RF-29");
            System.out.println("  0. Volver a la pantalla principal");
            System.out.println();
            int opc = Util.opcion();
            if (opc == 1) registrar();
            else if (opc == 2) consultar();
            else if (opc == 3) buscar();
            else if (opc == 4) actualizar();
            else if (opc == 5) eliminar();
            else if (opc == 6) listar();
            else if (opc == 7) deshacer();
            else if (opc == 0) return;
            else Util.aviso("Opcion no valida. Escriba un numero de 0 a 7.");
        }
    }

    // Recorre solo de 0 a contador - 1. Devuelve la posicion del bloque o -1.
    static int buscarPosicion(int id) {
        for (int i = 0; i < contador; i++) {
            if (bloques[i].id_programacion == id) return i;
        }
        return -1;
    }

    // CU-15 . RN-03 . RF-03, RF-18, RF-32 - primer bloque del mismo dia que se cruza con [ini, fin).
    // idPropio se excluye para que un bloque no se compare consigo mismo al actualizar (CA-03.4).
    // Hay cruce si el inicio de uno es anterior al fin del otro y su fin posterior al inicio del otro;
    // por eso 08:00-10:00 y 10:00-12:00 (contiguos) no se cruzan (CA-01.8).
    static ProgramacionAcademica seSuperpone(String dia, String ini, String fin, int idPropio) {
        for (int i = 0; i < contador; i++) {
            ProgramacionAcademica b = bloques[i];
            if (b.id_programacion != idPropio && b.dia_semana.equals(dia)
                    && ini.compareTo(b.hora_fin) < 0 && b.hora_inicio.compareTo(fin) < 0) {
                return b;
            }
        }
        return null;
    }

    // Punto de contacto (RN-01, paso 3) - true si hay al menos un bloque ese dia
    static boolean hayBloquesEl(String dia) {
        for (int i = 0; i < contador; i++) if (bloques[i].dia_semana.equals(dia)) return true;
        return false;
    }

    // Punto de contacto . RN-02 - bloque con hora_inicio <= hora < hora_fin, o null
    static ProgramacionAcademica bloqueQueCubre(String dia, String hora) {
        for (int i = 0; i < contador; i++) {
            ProgramacionAcademica b = bloques[i];
            if (b.dia_semana.equals(dia) && b.hora_inicio.compareTo(hora) <= 0 && hora.compareTo(b.hora_fin) < 0) {
                return b;
            }
        }
        return null;
    }

    // Validaciones de dia y horas que comparten registrar y actualizar. Devuelve la causa o null.
    // RF-04 . CA-01.4 | RF-05 . CA-01.5 | RF-02 . CA-01.2
    static String validarHorario(String dia, String ini, String fin) {
        if (!Util.diaValido(dia))
            return "Dia no valido. Use: lunes, martes, miercoles, jueves, viernes, sabado o domingo.";
        if (!Util.horaValida(ini)) return "Hora de inicio no valida. Use HH:MM entre 00:00 y 23:59.";
        if (!Util.horaValida(fin)) return "Hora de fin no valida. Use HH:MM entre 00:00 y 23:59.";
        if (fin.compareTo(ini) <= 0) return "La hora de fin debe ser posterior a la hora de inicio.";
        return null;
    }

    // HU-01 . CU-01 . Figura 18 - pide los campos y valida todo al oprimir [G] Guardar.
    // RF-01 guarda . RF-02 a RF-07 rechazan y avisan . RF-08 capacidad . RF-25 guarda en el archivo
    static void registrar() {
        System.out.println();
        System.out.println("REGISTRAR BLOQUE - CU-01 / HU-01 / RF-01");
        if (contador == bloques.length) {                                  // RF-08 . CA-01.9
            Util.aviso("Se alcanzo la capacidad maxima de la programacion (100 bloques).");
            return;
        }
        while (true) {
            String tId  = Util.leer("  id_programacion .... (entero positivo): ");
            String dia  = Util.leer("  dia_semana ......... (lunes ... domingo, sin tilde): ").toLowerCase();
            String ini  = Util.leer("  hora_inicio ........ (HH:MM): ");
            String fin  = Util.leer("  hora_fin ........... (HH:MM): ");
            char b = Util.boton("[G] Guardar     [L] Limpiar     [C] Cancelar", "GLC");
            if (b == 'L') continue;                                        // borra los campos
            if (b == 'C') { Util.aviso("Registro del bloque cancelado. No se guardo nada."); return; }

            int id = Util.idValido(tId);
            if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }   // RF-06 . CA-01.6
            if (buscarPosicion(id) != -1) { Util.aviso("El identificador " + id + " ya esta registrado."); return; } // RF-07 . CA-01.7
            String causa = validarHorario(dia, ini, fin);                  // RF-02, RF-04, RF-05
            if (causa != null) {                                           // CA-01.2 / CA-01.4 / CA-01.5
                Util.aviso(causa.startsWith("La hora de fin") ? causa + " El bloque no se guardo." : causa);
                return;
            }
            ProgramacionAcademica choque = seSuperpone(dia, ini, fin, -1); // RF-03 . CU-15 . CA-01.3
            if (choque != null) {
                Util.aviso("El horario se superpone con el bloque " + choque.id_programacion + " ("
                        + choque.horario() + "). El bloque no se guardo.");
                return;
            }
            bloques[contador] = new ProgramacionAcademica(id, dia, ini, fin);  // se escribe en [contador]
            contador++;
            guardar();                                                     // RF-25
            Util.aviso("Bloque " + id + " guardado.");                      // RF-01 . CA-01.1 / CA-01.8
            return;
        }
    }

    // HU-02 . CU-02 . Figura 19 - consulta exacta por id. RF-09 muestra . RF-10 no existe . RF-11 id invalido
    static void consultar() {
        System.out.println();
        System.out.println("CONSULTAR BLOQUE - CU-02 / HU-02 / RF-09");
        String t = Util.leer("  id_programacion: ");
        if (Util.boton("[B] Buscar     [C] Cancelar", "BC") == 'C') return;
        int id = Util.idValido(t);
        if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }      // CA-02.3
        int p = buscarPosicion(id);
        if (p == -1) Util.aviso("No existe un bloque con el identificador " + id + ".");                   // CA-02.2
        else System.out.println("  " + bloques[p]);                                                         // CA-02.1
    }

    // HU-02 . CU-02 . Figura 19 - busqueda por texto parcial en dia_semana, sin distinguir mayusculas.
    // RF-12 muestra . RF-13 sin coincidencias . RF-14 texto vacio
    static void buscar() {
        System.out.println();
        System.out.println("BUSCAR BLOQUES POR DIA - CU-02 / HU-02 / RF-12");
        String t = Util.leer("  texto: ").toLowerCase();
        if (Util.boton("[B] Buscar     [C] Cancelar", "BC") == 'C') return;
        if (t.isEmpty()) { Util.aviso("Escriba un texto para buscar."); return; }                           // CA-02.6
        int n = 0;
        for (int i = 0; i < contador; i++) {
            if (bloques[i].dia_semana.contains(t)) { System.out.println("  " + bloques[i]); n++; }        // CA-02.4
        }
        if (n == 0) Util.aviso("No hay coincidencias para \"" + t + "\".");                                 // CA-02.5
    }

    // HU-03 . CU-03 . Figura 18 - cambia dia y horas en la misma casilla; el id no se ofrece para cambiar.
    // RF-15 actualiza . RF-16 no existe . RF-17 datos invalidos . RF-18 superposicion (sin compararse
    // consigo mismo) . RF-19 id invalido. Enter deja el valor actual.
    static void actualizar() {
        System.out.println();
        System.out.println("ACTUALIZAR BLOQUE - CU-03 / HU-03 / RF-15");
        int id = Util.idValido(Util.leer("  id_programacion a actualizar: "));
        if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }      // CA-03.5
        int p = buscarPosicion(id);
        if (p == -1) { Util.aviso("No existe un bloque con el identificador " + id + ". No se modifico nada."); return; } // CA-03.2
        ProgramacionAcademica b = bloques[p];
        System.out.println("  id_programacion: " + id + " (no se modifica)   -   Enter deja el valor actual");
        while (true) {
            String dia = Util.leer("  dia_semana ......... [" + b.dia_semana + "]: ").toLowerCase();
            String ini = Util.leer("  hora_inicio ........ [" + b.hora_inicio + "]: ");
            String fin = Util.leer("  hora_fin ........... [" + b.hora_fin + "]: ");
            char op = Util.boton("[G] Guardar     [L] Limpiar     [C] Cancelar", "GLC");
            if (op == 'L') continue;
            if (op == 'C') { Util.aviso("Actualizacion cancelada. No se modifico nada."); return; }
            if (dia.isEmpty()) dia = b.dia_semana;
            if (ini.isEmpty()) ini = b.hora_inicio;
            if (fin.isEmpty()) fin = b.hora_fin;
            String causa = validarHorario(dia, ini, fin);
            if (causa != null) { Util.aviso(causa + " Se conservan los datos anteriores del bloque."); return; } // CA-03.3
            ProgramacionAcademica choque = seSuperpone(dia, ini, fin, id);
            if (choque != null) {                                                                           // CA-03.4
                Util.aviso("El nuevo horario se superpone con el bloque " + choque.id_programacion
                        + ". Se conservan los datos anteriores.");
                return;
            }
            b.dia_semana = dia;
            b.hora_inicio = ini;
            b.hora_fin = fin;
            guardar();                                                                                      // RF-25
            Util.aviso("Bloque " + id + " actualizado.");                                                   // CA-03.1
            return;
        }
    }

    // HU-04 . CU-04 . Figura 20 - pide confirmacion, apila una copia, corre a la izquierda todo lo que
    // esta a la derecha y baja el contador (no queda hueco).
    // RF-20 elimina . RF-21 cancela . RF-22 no existe . RF-23 id invalido . RF-79 pila llena
    static void eliminar() {
        System.out.println();
        System.out.println("ELIMINAR BLOQUE - CU-04 / HU-04 / RF-20");
        int id = Util.idValido(Util.leer("  id_programacion: "));
        if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }      // CA-04.4
        int p = buscarPosicion(id);
        if (p == -1) { Util.aviso("No existe un bloque con el identificador " + id + ". No se elimino nada."); return; } // CA-04.3
        if (pila.estaLlena()) {                                                                             // CA-04.5
            Util.aviso("La pila de deshacer esta llena. Deshaga una eliminacion o reinicie el programa para poder eliminar.");
            return;
        }
        String r = Util.leer("  Confirma eliminar " + bloques[p].horario() + "? (S/N): ");
        if (!r.equalsIgnoreCase("S")) { Util.aviso("Eliminacion cancelada. No se modifico nada."); return; } // CA-04.2
        pila.apilar(bloques[p]);
        for (int i = p; i < contador - 1; i++) bloques[i] = bloques[i + 1];   // desplaza a la izquierda
        bloques[contador - 1] = null;
        contador--;
        guardar();                                                                                          // RF-25
        Util.aviso("Bloque " + id + " eliminado. Puede deshacer esta eliminacion.");                        // CA-04.1
    }

    // HU-05 . CU-05 . Figura 20 - lista de 0 a contador - 1, en el orden del arreglo, con la duracion de
    // cada bloque y el total de horas por dia. RF-24 lista con el calculo . RF-27 lista vacia
    static void listar() {
        System.out.println();
        System.out.println("LISTAR BLOQUES - CU-05 / HU-05 / RF-24");
        if (contador == 0) { Util.aviso("No hay bloques de programacion registrados."); return; }          // CA-05.3
        System.out.println("  id | dia_semana | hora_inicio | hora_fin | duracion (min)");
        int[] minutosDia = new int[7];
        for (int i = 0; i < contador; i++) {
            ProgramacionAcademica b = bloques[i];
            System.out.println("  " + Util.col("" + b.id_programacion, 2) + " | " + Util.col(b.dia_semana, 10) + " | "
                    + Util.col(b.hora_inicio, 11) + " | " + Util.col(b.hora_fin, 8) + " | " + b.duracion());
            for (int d = 0; d < 7; d++) if (Util.DIAS[d].equals(b.dia_semana)) minutosDia[d] += b.duracion();
        }
        String totales = "";
        for (int d = 0; d < 7; d++) {
            if (minutosDia[d] > 0) totales += (totales.isEmpty() ? "" : "  -  ") + Util.DIAS[d] + " " + Util.horasYMinutos(minutosDia[d]);
        }
        System.out.println("  Total por dia:  " + totales);
        System.out.println("  Total de bloques: " + contador);
    }

    // HU-06 . CU-06 - mira la cima; si se puede recuperar, la desapila y la escribe en [contador].
    // Si no, avisa y el bloque sigue en la pila.
    // RF-29 recupera . RF-30 pila vacia . RF-31 id ocupado . RF-32 se superpone (CU-15) . RF-33 arreglo lleno
    static void deshacer() {
        System.out.println();
        System.out.println("DESHACER - CU-06 / HU-06 / RF-29");
        ProgramacionAcademica b = pila.cima();
        if (b == null) { Util.aviso("No hay nada que deshacer."); return; }                                 // CA-06.3
        if (contador == bloques.length) {                                                                   // CA-06.6
            Util.aviso("No se puede recuperar el bloque: la programacion esta llena (100). Sigue en la pila.");
            return;
        }
        if (buscarPosicion(b.id_programacion) != -1) {                                                      // CA-06.4
            Util.aviso("No se puede recuperar el bloque " + b.id_programacion
                    + ": su identificador ya esta ocupado. Sigue en la pila.");
            return;
        }
        ProgramacionAcademica choque = seSuperpone(b.dia_semana, b.hora_inicio, b.hora_fin, b.id_programacion);
        if (choque != null) {                                                                               // CA-06.5
            Util.aviso("No se puede recuperar el bloque " + b.id_programacion + ": se superpone con el bloque "
                    + choque.id_programacion + ". Sigue en la pila.");
            return;
        }
        pila.desapilar();
        bloques[contador] = b;                                         // vuelve de ultimo
        contador++;
        guardar();                                                                                          // RF-25
        Util.aviso("Bloque " + b.id_programacion + " recuperado.");                                         // CA-06.1 / CA-06.2
    }

    // RF-25 . RNF-02 - escribe todos los bloques en el archivo (se llama despues de cada cambio exitoso)
    static void guardar() {
        try (PrintWriter w = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (int i = 0; i < contador; i++) w.println(bloques[i].aLinea());
        } catch (Exception e) {
            Util.aviso("No se pudo guardar " + ARCHIVO + ": " + e.getMessage());
        }
    }

    // RF-26 carga al iniciar . RF-28 sin archivo: inicia vacia, avisa y sigue (CA-05.4)
    // Las lineas danadas se saltan (RNF-05). La pila empieza vacia en cada ejecucion.
    static void cargar() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            Util.aviso("No se encontraron datos guardados de la programacion. Se inicia vacia.");
            return;
        }
        try (Scanner lector = new Scanner(f)) {
            while (lector.hasNextLine() && contador < bloques.length) {
                String[] c = lector.nextLine().trim().split(";");
                if (c.length != 4) continue;
                int id = Util.idValido(c[0]);
                if (id == -1 || buscarPosicion(id) != -1 || validarHorario(c[1], c[2], c[3]) != null) continue;
                bloques[contador] = new ProgramacionAcademica(id, c[1], c[2], c[3]);
                contador++;
            }
        } catch (Exception e) {
            Util.aviso("No se pudo leer " + ARCHIVO + ".");
        }
    }
}
