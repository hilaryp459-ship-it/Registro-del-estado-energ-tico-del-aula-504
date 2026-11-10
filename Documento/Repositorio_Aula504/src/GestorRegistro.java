/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 *                  comparado con la programacion academica
 * Entidad        : REGISTRO_ESTADO (transaccional) y consultas que lo comparan
 *                  con la programacion academica
 * Programado por : Luijeimm Agudelo Godoy
 * Fecha          : 04/11/2026 (ajustado a los mockups el 05/11/2026)
 * -----------------------------------------------------------
 * HU-07 . CU-07  Registrar estado del aula ............. RF-34 a RF-43
 * HU-08 . CU-08  Consultar por id y buscar por persona .. RF-44 a RF-49
 * HU-09 . CU-09  Actualizar registro ................... RF-50 a RF-53
 * HU-10 . CU-10  Eliminar registro ..................... RF-54 a RF-57, RF-80
 * HU-11 . CU-11  Listar registros con totales ........... RF-58 a RF-62
 * HU-12 . CU-12  Deshacer ultima eliminacion ............ RF-63 a RF-66 ("extend" de CU-10)
 * HU-13 . CU-13  Historial por rango de fechas .......... RF-67 a RF-71
 * HU-14 . CU-14  Detalle de la comparacion .............. RF-72 a RF-78
 * CU-16          Comparar con la programacion ........... "include" desde CU-11, CU-13 y CU-14
 * RN-01  Orden de la comparacion: ocupada -> sin advertencia; todo apagado ->
 *        sin advertencia; sin bloques ese dia -> inconclusa; un bloque cubre la
 *        hora -> sin advertencia; si no -> posible inconsistencia
 * RN-02  Un bloque cubre la hora si hora_inicio <= hora_observacion < hora_fin
 * RN-04  La observacion no puede ser posterior al momento actual
 * RNF-02 Se guarda despues de cada cambio exitoso y se carga al iniciar
 * RNF-04 Cada falla avisa la causa en la linea MENSAJE
 * -----------------------------------------------------------
 * Criterios: CA-07.1 a CA-14.7 (Tabla 29)
 * Pantallas: Figuras 21 a 26; mensajes: Tablas 57 y 58
 * Estructuras: Arreglo con contador (200) . Pila propia (200)
 * Punto de contacto: comparar() llama a GestorProgramacion.hayBloquesEl() y
 *                    GestorProgramacion.bloqueQueCubre(), escritos por Hilary Perez Zuniga
 * =========================================================== */
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class GestorRegistro {
    static RegistroEstado[] registros = new RegistroEstado[200];
    static int contador = 0;
    static PilaRegistro pila = new PilaRegistro();
    static final String ARCHIVO = "registros.txt";

    // Figura 21 - menu de los registros del estado del aula
    static void menu() {
        while (true) {
            System.out.println();
            System.out.println("REGISTROS DEL ESTADO DEL AULA 504");
            System.out.println("------------------------------------------------------------");
            System.out.println("  1. Registrar estado del aula ............. CU-07 / HU-07 / RF-34");
            System.out.println("  2. Consultar registro por identificador ... CU-08 / HU-08 / RF-44");
            System.out.println("  3. Buscar registros por persona .......... CU-08 / HU-08 / RF-47");
            System.out.println("  4. Actualizar registro ................... CU-09 / HU-09 / RF-50");
            System.out.println("  5. Eliminar registro ..................... CU-10 / HU-10 / RF-54");
            System.out.println("  6. Listar registros ...................... CU-11 / HU-11 / RF-58");
            System.out.println("  7. Deshacer ultima eliminacion ........... CU-12 / HU-12 / RF-63");
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

    // Recorre solo de 0 a contador - 1. Devuelve la posicion del registro o -1.
    static int buscarPosicion(int id) {
        for (int i = 0; i < contador; i++) {
            if (registros[i].id_registro == id) return i;
        }
        return -1;
    }

    // CU-16 . RN-01 . RN-02 - resultado de comparar un registro con la programacion vigente.
    // No se guarda (Tabla 53): se calcula cada vez. Punto de contacto con GestorProgramacion.
    static final int OCUPADA = 0, APAGADO = 1, INCONCLUSA = 2, CUBIERTO = 3, POSIBLE = 4;

    static int comparar(RegistroEstado r) {
        if (r.ocupacion.equals("ocupada")) return OCUPADA;                     // RN-01 paso 1
        if (!r.algoEncendido()) return APAGADO;                                // RN-01 paso 2
        String dia = Util.diaDe(r.fecha_observacion);
        if (!GestorProgramacion.hayBloquesEl(dia)) return INCONCLUSA;          // RN-01 paso 3
        if (GestorProgramacion.bloqueQueCubre(dia, r.hora_observacion) != null) return CUBIERTO; // RN-01 paso 4 . RN-02
        return POSIBLE;                                                        // RN-01 paso 5
    }

    // Resultado corto para los listados (RF-58, RF-67)
    static String resultadoCorto(RegistroEstado r) {
        int c = comparar(r);
        String dia = Util.diaDe(r.fecha_observacion);
        if (c == OCUPADA) return "sin advertencia (aula ocupada)";
        if (c == APAGADO) return "sin advertencia (todo apagado)";
        if (c == INCONCLUSA) return "comparacion inconclusa (sin bloques el " + dia + ")";
        if (c == CUBIERTO) return "sin advertencia (bloque "
                + GestorProgramacion.bloqueQueCubre(dia, r.hora_observacion).id_programacion + ")";
        return "POSIBLE INCONSISTENCIA";
    }

    // Validaciones de la creacion que tambien usa actualizar (RF-53). Devuelve la causa o null.
    // RF-35 faltan datos . RF-36 valor no admitido . RF-37 fecha . RF-38 futura . RF-39 hora . RF-42 longitud
    static String validarCampos(String ocu, String comp, String lumi, String aire, String fecha, String hora, String persona) {
        String faltan = "";
        if (ocu.isEmpty()) faltan += ", ocupacion";
        if (comp.isEmpty()) faltan += ", computadores_encendidos";
        if (lumi.isEmpty()) faltan += ", luminarias_encendidas";
        if (aire.isEmpty()) faltan += ", aire_encendido";
        if (fecha.isEmpty()) faltan += ", fecha_observacion";
        if (hora.isEmpty()) faltan += ", hora_observacion";
        if (persona.isEmpty()) faltan += ", persona_registra";
        if (!faltan.isEmpty()) return "Faltan datos obligatorios: " + faltan.substring(2) + ".";         // CA-07.2
        if (!ocu.equals("ocupada") && !ocu.equals("vacia"))
            return "Valor no admitido en ocupacion. Use: ocupada o vacia.";                              // CA-07.3
        if (!esSiNo(comp)) return "Valor no admitido en computadores_encendidos. Use: si o no.";
        if (!esSiNo(lumi)) return "Valor no admitido en luminarias_encendidas. Use: si o no.";
        if (!esSiNo(aire)) return "Valor no admitido en aire_encendido. Use: si o no.";
        if (persona.contains(";")) return "Valor no admitido en persona_registra. No use punto y coma.";
        if (Util.fecha(fecha) == null) return "Fecha no valida. Use dd/mm/aaaa con una fecha que exista."; // CA-07.4
        if (!Util.horaValida(hora)) return "Hora no valida. Use HH:MM entre 00:00 y 23:59.";               // CA-07.6
        if (Util.esFutura(fecha, hora)) return "La observacion no puede ser posterior al momento actual."; // CA-07.5 . RN-04
        if (persona.length() > 80) return "persona_registra admite maximo 80 caracteres.";               // CA-07.9
        return null;
    }

    static boolean esSiNo(String t) { return t.equals("si") || t.equals("no"); }

    // HU-07 . CU-07 . Figura 22 - pide los campos en el orden del mockup y valida todo con [G] Guardar.
    // RF-34 guarda . RF-35 a RF-42 rechazan y avisan . RF-43 capacidad . RF-59 guarda en el archivo
    static void registrar() {
        System.out.println();
        System.out.println("REGISTRAR ESTADO DEL AULA - CU-07 / HU-07 / RF-34");
        if (contador == registros.length) {                                                   // RF-43 . CA-07.10
            Util.aviso("Se alcanzo la capacidad maxima de registros (200).");
            return;
        }
        while (true) {
            String tId     = Util.leer("  id_registro ............... (entero positivo): ");
            String ocu     = Util.leer("  ocupacion ................. (ocupada | vacia): ").toLowerCase();
            String comp    = Util.leer("  computadores_encendidos ... (si | no): ").toLowerCase();
            String lumi    = Util.leer("  luminarias_encendidas ..... (si | no): ").toLowerCase();
            String aire    = Util.leer("  aire_encendido ............ (si | no): ").toLowerCase();
            String fecha   = Util.leer("  fecha_observacion ......... (dd/mm/aaaa): ");
            String hora    = Util.leer("  hora_observacion .......... (HH:MM): ");
            String persona = Util.leer("  persona_registra .......... (max. 80, sin ;): ");
            char b = Util.boton("[G] Guardar     [L] Limpiar     [C] Cancelar", "GLC");
            if (b == 'L') continue;                                                           // borra los campos
            if (b == 'C') { Util.aviso("Registro cancelado. No se guardo nada."); return; }

            int id = Util.idValido(tId);
            if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }       // RF-41 . CA-07.8
            if (buscarPosicion(id) != -1) { Util.aviso("El identificador " + id + " ya esta registrado."); return; } // RF-40 . CA-07.7
            String causa = validarCampos(ocu, comp, lumi, aire, fecha, hora, persona);
            if (causa != null) { Util.aviso(causa); return; }
            registros[contador] = new RegistroEstado(id, ocu, comp.equals("si"), lumi.equals("si"), aire.equals("si"),
                    fecha, hora, persona);                                                     // se escribe en [contador]
            contador++;
            guardar();                                                                         // RF-59
            Util.aviso("Registro " + id + " guardado.");                                       // RF-34 . CA-07.1
            return;
        }
    }

    // HU-08 . CU-08 . Figura 23 - consulta exacta por id. RF-44 muestra . RF-45 no existe . RF-46 id invalido
    static void consultar() {
        System.out.println();
        System.out.println("CONSULTAR REGISTRO - CU-08 / HU-08 / RF-44");
        String t = Util.leer("  id_registro: ");
        if (Util.boton("[B] Buscar     [C] Cancelar", "BC") == 'C') return;
        int id = Util.idValido(t);
        if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }          // CA-08.3
        int p = buscarPosicion(id);
        if (p == -1) Util.aviso("No existe un registro con el identificador " + id + ".");                    // CA-08.2
        else System.out.println("  " + registros[p]);                                                          // CA-08.1
    }

    // HU-08 . CU-08 . Figura 23 - busqueda por texto parcial en persona_registra, sin distinguir mayusculas.
    // RF-47 muestra . RF-48 sin coincidencias . RF-49 texto vacio
    static void buscar() {
        System.out.println();
        System.out.println("BUSCAR REGISTROS POR PERSONA - CU-08 / HU-08 / RF-47");
        String t = Util.leer("  texto: ").toLowerCase();
        if (Util.boton("[B] Buscar     [C] Cancelar", "BC") == 'C') return;
        if (t.isEmpty()) { Util.aviso("Escriba un texto para buscar."); return; }                              // CA-08.6
        int n = 0;
        for (int i = 0; i < contador; i++) {
            if (registros[i].persona_registra.toLowerCase().contains(t)) {                                    // CA-08.4
                System.out.println("  " + registros[i].corto());
                n++;
            }
        }
        if (n == 0) Util.aviso("No hay coincidencias para \"" + t + "\".");                                    // CA-08.5
    }

    // HU-09 . CU-09 . Figura 22 - corrige los datos en la misma casilla; el id no se ofrece para cambiar.
    // RF-50 actualiza . RF-51 no existe . RF-52 id invalido . RF-53 datos invalidos. Enter deja el valor actual.
    static void actualizar() {
        System.out.println();
        System.out.println("ACTUALIZAR REGISTRO - CU-09 / HU-09 / RF-50");
        int id = Util.idValido(Util.leer("  id_registro a actualizar: "));
        if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }          // CA-09.3
        int p = buscarPosicion(id);
        if (p == -1) { Util.aviso("No existe un registro con el identificador " + id + ". No se modifico nada."); return; } // CA-09.2
        RegistroEstado r = registros[p];
        System.out.println("  id_registro: " + id + " (no se modifica)   -   Enter deja el valor actual");
        while (true) {
            String ocu     = Util.leer("  ocupacion ................. [" + r.ocupacion + "]: ").toLowerCase();
            String comp    = Util.leer("  computadores_encendidos ... [" + RegistroEstado.sn(r.computadores_encendidos) + "]: ").toLowerCase();
            String lumi    = Util.leer("  luminarias_encendidas ..... [" + RegistroEstado.sn(r.luminarias_encendidas) + "]: ").toLowerCase();
            String aire    = Util.leer("  aire_encendido ............ [" + RegistroEstado.sn(r.aire_encendido) + "]: ").toLowerCase();
            String fecha   = Util.leer("  fecha_observacion ......... [" + r.fecha_observacion + "]: ");
            String hora    = Util.leer("  hora_observacion .......... [" + r.hora_observacion + "]: ");
            String persona = Util.leer("  persona_registra .......... [" + r.persona_registra + "]: ");
            char b = Util.boton("[G] Guardar     [L] Limpiar     [C] Cancelar", "GLC");
            if (b == 'L') continue;
            if (b == 'C') { Util.aviso("Actualizacion cancelada. No se modifico nada."); return; }
            if (ocu.isEmpty()) ocu = r.ocupacion;
            if (comp.isEmpty()) comp = RegistroEstado.sn(r.computadores_encendidos);
            if (lumi.isEmpty()) lumi = RegistroEstado.sn(r.luminarias_encendidas);
            if (aire.isEmpty()) aire = RegistroEstado.sn(r.aire_encendido);
            if (fecha.isEmpty()) fecha = r.fecha_observacion;
            if (hora.isEmpty()) hora = r.hora_observacion;
            if (persona.isEmpty()) persona = r.persona_registra;
            String causa = validarCampos(ocu, comp, lumi, aire, fecha, hora, persona);
            if (causa != null) { Util.aviso(causa + " Se conservan los datos anteriores del registro."); return; } // CA-09.4
            r.ocupacion = ocu;
            r.computadores_encendidos = comp.equals("si");
            r.luminarias_encendidas = lumi.equals("si");
            r.aire_encendido = aire.equals("si");
            r.fecha_observacion = fecha;
            r.hora_observacion = hora;
            r.persona_registra = persona;
            guardar();                                                                                         // RF-59
            Util.aviso("Registro " + id + " actualizado.");                                                    // CA-09.1
            return;
        }
    }

    // HU-10 . CU-10 . Figura 24 - pide confirmacion, apila una copia, corre a la izquierda todo lo que
    // esta a la derecha y baja el contador (no queda hueco).
    // RF-54 elimina . RF-55 cancela . RF-56 no existe . RF-57 id invalido . RF-80 pila llena
    static void eliminar() {
        System.out.println();
        System.out.println("ELIMINAR REGISTRO - CU-10 / HU-10 / RF-54");
        int id = Util.idValido(Util.leer("  id_registro: "));
        if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }          // CA-10.4
        int p = buscarPosicion(id);
        if (p == -1) { Util.aviso("No existe un registro con el identificador " + id + ". No se elimino nada."); return; } // CA-10.3
        if (pila.estaLlena()) {                                                                                // CA-10.5
            Util.aviso("La pila de deshacer esta llena. Deshaga una eliminacion o reinicie el programa para poder eliminar.");
            return;
        }
        String c = Util.leer("  Confirma eliminar el registro " + id + "? (S/N): ");
        if (!c.equalsIgnoreCase("S")) { Util.aviso("Eliminacion cancelada. No se modifico nada."); return; }    // CA-10.2
        pila.apilar(registros[p]);
        for (int i = p; i < contador - 1; i++) registros[i] = registros[i + 1];   // desplaza a la izquierda
        registros[contador - 1] = null;
        contador--;
        guardar();                                                                                             // RF-59
        Util.aviso("Registro " + id + " eliminado. Puede deshacer esta eliminacion.");                         // CA-10.1
    }

    // Una fila de los listados de registros (Figuras 24 y 25)
    static String fila(RegistroEstado r) {
        return "  " + Util.col("" + r.id_registro, 2) + " | " + r.fecha_observacion + " | " + r.hora_observacion + " | "
                + Util.col(r.ocupacion, 9) + " | " + Util.col(RegistroEstado.sn(r.computadores_encendidos), 5) + " | "
                + Util.col(RegistroEstado.sn(r.luminarias_encendidas), 4) + " | " + Util.col(RegistroEstado.sn(r.aire_encendido), 4)
                + " | " + Util.col(r.persona_registra, 12) + " | " + resultadoCorto(r);
    }

    static final String ENCABEZADO = "  id | fecha      | hora  | ocupacion | comp. | lum. | aire | persona      | resultado";

    // HU-11 . CU-11 . Figura 24 - lista de 0 a contador - 1, en el orden del arreglo, con el resultado de
    // la comparacion (CU-16), el total de registros y el de posibles inconsistencias.
    // RF-58 lista con los totales . RF-61 lista vacia
    static void listar() {
        System.out.println();
        System.out.println("LISTAR REGISTROS - CU-11 / HU-11 / RF-58");
        if (contador == 0) { Util.aviso("No hay registros del estado del aula."); return; }                    // CA-11.3
        System.out.println(ENCABEZADO);
        int posibles = 0;
        for (int i = 0; i < contador; i++) {
            System.out.println(fila(registros[i]));
            if (comparar(registros[i]) == POSIBLE) posibles++;
        }
        System.out.println("  Total de registros: " + contador + "   Posibles inconsistencias: " + posibles + "  (CU-16 / RN-01)");
    }

    // HU-12 . CU-12 - mira la cima; si se puede recuperar, la desapila y la escribe en [contador].
    // Si no, avisa y el registro sigue en la pila.
    // RF-63 recupera . RF-64 pila vacia . RF-65 id ocupado . RF-66 arreglo lleno
    static void deshacer() {
        System.out.println();
        System.out.println("DESHACER - CU-12 / HU-12 / RF-63");
        RegistroEstado r = pila.cima();
        if (r == null) { Util.aviso("No hay nada que deshacer."); return; }                                    // CA-12.3
        if (contador == registros.length) {                                                                    // CA-12.5
            Util.aviso("No se puede recuperar el registro: hay 200 registros. Sigue en la pila.");
            return;
        }
        if (buscarPosicion(r.id_registro) != -1) {                                                             // CA-12.4
            Util.aviso("No se puede recuperar el registro " + r.id_registro + ": su identificador ya esta ocupado. Sigue en la pila.");
            return;
        }
        pila.desapilar();
        registros[contador] = r;                                       // vuelve de ultimo
        contador++;
        guardar();                                                                                             // RF-59
        Util.aviso("Registro " + r.id_registro + " recuperado.");                                              // CA-12.1 / CA-12.2
    }

    // HU-13 . CU-13 . Figura 25 - registros entre dos fechas (ambas incluidas), en orden cronologico, con el
    // resultado de la comparacion. RF-67 muestra . RF-68 rango invertido . RF-69 sin registros
    // . RF-70 fecha invalida . RF-71 fecha faltante
    static void historial() {
        System.out.println();
        System.out.println("HISTORIAL POR RANGO DE FECHAS - CU-13 / HU-13 / RF-67");
        String desde, hasta;
        while (true) {
            desde = Util.leer("  fecha inicial (dd/mm/aaaa): ");
            hasta = Util.leer("  fecha final (dd/mm/aaaa): ");
            char b = Util.boton("[B] Consultar     [L] Limpiar     [C] Volver", "BLC");
            if (b == 'L') continue;
            if (b == 'C') return;
            break;
        }
        if (desde.isEmpty() && hasta.isEmpty()) { Util.aviso("Faltan la fecha inicial y la fecha final."); return; } // CA-13.5
        if (desde.isEmpty()) { Util.aviso("Falta la fecha inicial."); return; }
        if (hasta.isEmpty()) { Util.aviso("Falta la fecha final."); return; }
        LocalDate ini = Util.fecha(desde), fin = Util.fecha(hasta);
        if (ini == null) { Util.aviso("Fecha inicial no valida. Use dd/mm/aaaa con una fecha que exista."); return; } // CA-13.4
        if (fin == null) { Util.aviso("Fecha final no valida. Use dd/mm/aaaa con una fecha que exista."); return; }
        if (fin.isBefore(ini)) { Util.aviso("La fecha final no puede ser anterior a la fecha inicial."); return; }   // CA-13.2

        // posiciones del rango, ordenadas por fecha y hora (insercion); el arreglo no se modifica
        int[] pos = new int[contador];
        int n = 0;
        for (int i = 0; i < contador; i++) {
            LocalDate f = Util.fecha(registros[i].fecha_observacion);
            if (!f.isBefore(ini) && !f.isAfter(fin)) pos[n++] = i;
        }
        if (n == 0) { Util.aviso("No hay registros entre " + desde + " y " + hasta + "."); return; }          // CA-13.3
        for (int i = 1; i < n; i++) {
            int actual = pos[i], j = i - 1;
            while (j >= 0 && clave(registros[pos[j]]).compareTo(clave(registros[actual])) > 0) { pos[j + 1] = pos[j]; j--; }
            pos[j + 1] = actual;
        }
        System.out.println(ENCABEZADO);
        int posibles = 0;
        for (int k = 0; k < n; k++) {                                                                          // CA-13.1
            System.out.println(fila(registros[pos[k]]));
            if (comparar(registros[pos[k]]) == POSIBLE) posibles++;
        }
        System.out.println("  Registros en el rango: " + n + "   Posibles inconsistencias: " + posibles);
    }

    // aaaa/mm/dd HH:MM, para ordenar por fecha y hora
    static String clave(RegistroEstado r) {
        String f = r.fecha_observacion;
        return f.substring(6) + "/" + f.substring(3, 5) + "/" + f.substring(0, 2) + " " + r.hora_observacion;
    }

    // HU-14 . CU-14 . Figura 26 - muestra el registro, los bloques de ese dia y el resultado explicado.
    // RF-72 posible inconsistencia . RF-73 ocupada . RF-74 hora cubierta . RF-75 todo apagado
    // . RF-76 inconclusa . RF-77 no existe . RF-78 id invalido
    static void detalle() {
        System.out.println();
        System.out.println("DETALLE DE LA COMPARACION - CU-14 / HU-14 / RF-72");
        String t = Util.leer("  id_registro: ");
        if (Util.boton("[B] Consultar     [C] Volver", "BC") == 'C') return;
        int id = Util.idValido(t);
        if (id == -1) { Util.aviso("El identificador debe ser un numero entero positivo."); return; }          // CA-14.7
        int p = buscarPosicion(id);
        if (p == -1) { Util.aviso("No existe un registro con el identificador " + id + "."); return; }        // CA-14.6
        RegistroEstado r = registros[p];
        String dia = Util.diaDe(r.fecha_observacion);
        System.out.println("  Fecha: " + r.fecha_observacion + " (" + dia + ")   Hora: " + r.hora_observacion
                + "   Ocupacion: " + r.ocupacion);
        System.out.println("  Encendidos: " + r.encendidos());
        String lista = "";
        ProgramacionAcademica terminaAhi = null;
        for (int i = 0; i < GestorProgramacion.contador; i++) {
            ProgramacionAcademica b = GestorProgramacion.bloques[i];
            if (b.dia_semana.equals(dia)) {
                lista += (lista.isEmpty() ? "" : "  -  ") + b.id_programacion + " | " + b.hora_inicio + "-" + b.hora_fin;
                if (b.hora_fin.equals(r.hora_observacion)) terminaAhi = b;
            }
        }
        System.out.println("  Bloques del " + dia + ": " + (lista.isEmpty() ? "ninguno" : lista));
        int c = comparar(r);
        if (c == OCUPADA) Util.resultado("SIN ADVERTENCIA. El aula estaba registrada como ocupada.");          // CA-14.2
        else if (c == APAGADO) Util.resultado("SIN ADVERTENCIA. No habia elementos encendidos.");             // CA-14.4
        else if (c == INCONCLUSA)                                                                              // CA-14.5
            Util.resultado("COMPARACION INCONCLUSA. No hay programacion cargada para el " + dia + ".");
        else if (c == CUBIERTO) {                                                                              // CA-14.3
            ProgramacionAcademica b = GestorProgramacion.bloqueQueCubre(dia, r.hora_observacion);
            Util.resultado("SIN ADVERTENCIA. Las " + r.hora_observacion + " estan dentro del bloque "
                    + b.hora_inicio + "-" + b.hora_fin + ".");
        } else {                                                                                               // CA-14.1
            String enc = r.encendidos();
            String concuerda = enc.equals("luminarias") ? " encendidas" : enc.equals("aire acondicionado") ? " encendido" : " encendidos";
            String texto = "POSIBLE INCONSISTENCIA. Aula vacia, " + enc + concuerda + " y ningun bloque del "
                    + dia + " cubre las " + r.hora_observacion;
            if (terminaAhi != null) texto += " (el bloque " + terminaAhi.hora_inicio + "-" + terminaAhi.hora_fin
                    + " termina a las " + terminaAhi.hora_fin + ")";
            Util.resultado(texto + ".");
            System.out.println("  Una posible inconsistencia no confirma desperdicio de energia.");
        }
    }

    // RF-59 . RNF-02 - escribe todos los registros en el archivo (se llama despues de cada cambio exitoso)
    static void guardar() {
        try (PrintWriter w = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (int i = 0; i < contador; i++) w.println(registros[i].aLinea());
        } catch (Exception e) {
            Util.aviso("No se pudo guardar " + ARCHIVO + ": " + e.getMessage());
        }
    }

    // RF-60 carga al iniciar . RF-62 sin archivo: inicia sin registros, avisa y sigue (CA-11.4)
    // Lee el orden del diccionario (id;ocupacion;comp;lum;aire;fecha;hora;persona) y tambien el de la
    // version anterior (id;fecha;hora;persona;ocupacion;comp;lum;aire). Las lineas danadas se saltan (RNF-05).
    static void cargar() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            Util.aviso("No se encontraron registros guardados. Se inicia sin registros.");
            return;
        }
        try (Scanner lector = new Scanner(f)) {
            while (lector.hasNextLine() && contador < registros.length) {
                String[] c = lector.nextLine().trim().split(";");
                if (c.length != 8) continue;
                int id = Util.idValido(c[0]);
                if (id == -1 || buscarPosicion(id) != -1) continue;
                String ocu, fecha, hora, persona, comp, lumi, aire;
                if (Util.fecha(c[1]) != null) {          // orden de la version anterior
                    fecha = c[1]; hora = c[2]; persona = c[3]; ocu = c[4]; comp = c[5]; lumi = c[6]; aire = c[7];
                } else {                                 // orden del diccionario
                    ocu = c[1]; comp = c[2]; lumi = c[3]; aire = c[4]; fecha = c[5]; hora = c[6]; persona = c[7];
                }
                if (Util.fecha(fecha) == null || !Util.horaValida(hora)) continue;
                if (!ocu.equals("ocupada") && !ocu.equals("vacia")) continue;
                registros[contador] = new RegistroEstado(id, ocu, verdadero(comp), verdadero(lumi), verdadero(aire),
                        fecha, hora, persona);
                contador++;
            }
        } catch (Exception e) {
            Util.aviso("No se pudo leer " + ARCHIVO + ".");
        }
    }

    static boolean verdadero(String t) { return t.equals("true") || t.equals("si"); }
}
