/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 * Entidad        : PROGRAMACION_ACADEMICA (pila propia para deshacer)
 * Programado por : Hilary Perez Zuniga
 * Fecha          : 04/11/2026 (ajustado el 05/11/2026)
 * -----------------------------------------------------------
 * HU-04 eliminar (apila)  .  HU-06 deshacer (desapila)
 * RF-20  Al eliminar se apila una copia del bloque
 * RF-29  Deshacer saca el ultimo que entro (el ultimo eliminado)
 * RF-30  Con la pila vacia, cima y desapilar devuelven null (no hay error)
 * RF-79  Con la pila llena (100) no se puede eliminar
 * Estructuras: Pila sobre un arreglo con un entero que marca la cima.
 *              Sin java.util.Stack. Solo vive en memoria: no se guarda.
 * =========================================================== */
public class PilaProgramacion {
    private ProgramacionAcademica[] datos = new ProgramacionAcademica[100];
    private int cima = 0;                          // cuantos elementos hay

    // RF-20 - pone el eliminado encima; false si esta llena
    boolean apilar(ProgramacionAcademica b) {
        if (cima == datos.length) return false;
        datos[cima] = b;
        cima++;
        return true;
    }

    // RF-29 - saca el ultimo que entro; RF-30 - null si esta vacia
    ProgramacionAcademica desapilar() {
        if (cima == 0) return null;
        cima--;
        ProgramacionAcademica b = datos[cima];
        datos[cima] = null;
        return b;
    }

    // Mira el de arriba sin sacarlo; null si esta vacia
    ProgramacionAcademica cima() { return cima == 0 ? null : datos[cima - 1]; }

    boolean estaVacia() { return cima == 0; }

    // RF-79 . CA-04.5
    boolean estaLlena() { return cima == datos.length; }

    int tamano() { return cima; }
}
