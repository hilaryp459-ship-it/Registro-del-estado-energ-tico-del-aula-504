/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 * Entidad        : REGISTRO_ESTADO (transaccional): una observacion manual del aula 504
 * Programado por : Luijeimm Agudelo Godoy
 * Fecha          : 04/11/2026 (ajustado a los mockups el 05/11/2026)
 * -----------------------------------------------------------
 * Campos con los mismos nombres del diccionario de datos (Tabla 52):
 *   id_registro, ocupacion, computadores_encendidos, luminarias_encendidas,
 *   aire_encendido, fecha_observacion, hora_observacion, persona_registra
 * Los tres estados se escriben si / no y se guardan como true / false.
 * =========================================================== */
public class RegistroEstado {
    int id_registro;
    String ocupacion;                  // ocupada / vacia
    boolean computadores_encendidos;   // si / no
    boolean luminarias_encendidas;     // si / no
    boolean aire_encendido;            // si / no
    String fecha_observacion;          // dd/mm/aaaa
    String hora_observacion;           // HH:MM
    String persona_registra;           // maximo 80 caracteres, sin ;

    RegistroEstado(int id_registro, String ocupacion, boolean computadores_encendidos,
                   boolean luminarias_encendidas, boolean aire_encendido, String fecha_observacion,
                   String hora_observacion, String persona_registra) {
        this.id_registro = id_registro;
        this.ocupacion = ocupacion;
        this.computadores_encendidos = computadores_encendidos;
        this.luminarias_encendidas = luminarias_encendidas;
        this.aire_encendido = aire_encendido;
        this.fecha_observacion = fecha_observacion;
        this.hora_observacion = hora_observacion;
        this.persona_registra = persona_registra;
    }

    static String sn(boolean b) { return b ? "si" : "no"; }

    boolean algoEncendido() { return computadores_encendidos || luminarias_encendidas || aire_encendido; }

    // Elementos encendidos en palabras: "computadores y aire acondicionado" o "ninguno"
    String encendidos() {
        String[] e = new String[3];
        int n = 0;
        if (computadores_encendidos) e[n++] = "computadores";
        if (luminarias_encendidas) e[n++] = "luminarias";
        if (aire_encendido) e[n++] = "aire acondicionado";
        if (n == 0) return "ninguno";
        if (n == 1) return e[0];
        if (n == 2) return e[0] + " y " + e[1];
        return e[0] + ", " + e[1] + " y " + e[2];
    }

    // Como se muestra en la consulta (Figura 23): 1 | vacia | comp. si | lum. no | aire no | 02/11/2026 14:45 | L. Agudelo
    public String toString() {
        return id_registro + " | " + ocupacion + " | comp. " + sn(computadores_encendidos) + " | lum. "
                + sn(luminarias_encendidas) + " | aire " + sn(aire_encendido) + " | " + fecha_observacion + " "
                + hora_observacion + " | " + persona_registra;
    }

    // Como se muestra en la busqueda por persona (Figura 23): 1 | 02/11/2026 14:45 | L. Agudelo
    String corto() {
        return id_registro + " | " + fecha_observacion + " " + hora_observacion + " | " + persona_registra;
    }

    // RF-59 - una linea del archivo registros.txt, en el orden del diccionario
    String aLinea() {
        return id_registro + ";" + ocupacion + ";" + computadores_encendidos + ";" + luminarias_encendidas + ";"
                + aire_encendido + ";" + fecha_observacion + ";" + hora_observacion + ";" + persona_registra;
    }
}
