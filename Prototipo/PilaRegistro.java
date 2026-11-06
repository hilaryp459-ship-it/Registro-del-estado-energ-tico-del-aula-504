/* ===========================================================
 * Proyecto       : Registro del estado energetico del aula 504
 * Entidad        : REGISTRO_ESTADO (pila propia para deshacer)
 * Programado por : Luijeimm Agudelo Godoy
 * Fecha          : 04/11/2026 (ajustado el 05/11/2026)
 * -----------------------------------------------------------
 * HU-10 eliminar (apila)  .  HU-12 deshacer (desapila)
 * RF-54  Al eliminar se apila una copia del registro
 * RF-63  Deshacer saca el ultimo que entro (el ultimo eliminado)
 * RF-64  Con la pila vacia, cima y desapilar devuelven null (no hay error)
 * RF-80  Con la pila llena (200) no se puede eliminar
 * Estructuras: Pila sobre un arreglo con un entero que marca la cima.
 *              Sin java.util.Stack. Solo vive en memoria: no se guarda.
 * =========================================================== */
public class PilaRegistro {
    private RegistroEstado[] datos = new RegistroEstado[200];
    private int cima = 0;                          // cuantos elementos hay

    // RF-54 - pone el eliminado encima; false si esta llena
    boolean apilar(RegistroEstado r) {
        if (cima == datos.length) return false;
        datos[cima] = r;
        cima++;
        return true;
    }

    // RF-63 - saca el ultimo que entro; RF-64 - null si esta vacia
    RegistroEstado desapilar() {
        if (cima == 0) return null;
        cima--;
        RegistroEstado r = datos[cima];
        datos[cima] = null;
        return r;
    }

    // Mira el de arriba sin sacarlo; null si esta vacia
    RegistroEstado cima() { return cima == 0 ? null : datos[cima - 1]; }

    boolean estaVacia() { return cima == 0; }

    // RF-80 . CA-10.5
    boolean estaLlena() { return cima == datos.length; }

    int tamano() { return cima; }
}
