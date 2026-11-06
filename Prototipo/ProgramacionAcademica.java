/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 * Entidad        : PROGRAMACION_ACADEMICA (maestra): un bloque del horario del aula 504
 * Programado por : Hilary Perez Zuniga
 * Fecha          : 04/11/2026 (ajustado a los mockups el 05/11/2026)
 * -----------------------------------------------------------
 * Campos con los mismos nombres del diccionario de datos (Tabla 51):
 *   id_programacion, dia_semana, hora_inicio, hora_fin
 * RF-24  Duracion del bloque = hora_fin - hora_inicio, en minutos (dato calculado, Tabla 53)
 * =========================================================== */
public class ProgramacionAcademica {
    int id_programacion;
    String dia_semana;      // lunes ... domingo, sin tilde
    String hora_inicio;     // HH:MM
    String hora_fin;        // HH:MM, posterior a hora_inicio

    ProgramacionAcademica(int id_programacion, String dia_semana, String hora_inicio, String hora_fin) {
        this.id_programacion = id_programacion;
        this.dia_semana = dia_semana;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

    // RF-24 - duracion en minutos (no se guarda)
    int duracion() {
        return Util.minutos(hora_fin) - Util.minutos(hora_inicio);
    }

    // Como se muestra en la consulta y la busqueda (Figura 19): 1 | lunes | 08:00 | 10:00
    public String toString() {
        return id_programacion + " | " + dia_semana + " | " + hora_inicio + " | " + hora_fin;
    }

    // Como se nombra en los mensajes: lunes 08:00-10:00
    String horario() {
        return dia_semana + " " + hora_inicio + "-" + hora_fin;
    }

    // RF-25 - una linea del archivo programacion.txt, en el orden del diccionario
    String aLinea() {
        return id_programacion + ";" + dia_semana + ";" + hora_inicio + ";" + hora_fin;
    }
}
