// Línea 1: Declara la clase de la pila propia que guarda las personas eliminadas para poder deshacer.
// | Código original: public class PilaPersona {
public class PilaPersona {

    // Línea 3: Arreglo privado de 100 casillas donde la pila guarda las personas; es privado para que
    // nadie lo lea por posición. | Código original: private Persona[] datos = new Persona[100];
    private Persona[] datos = new Persona[100];
    // Línea 4: Cuenta cuántos elementos hay en la pila; también indica la casilla libre de arriba. |
    // Código original: private int tope = 0;
    private int tope = 0;

    // Línea 6: Apilar: pone una persona encima de la pila; devuelve false si la pila está llena. |
    // Código original: public boolean apilar(Persona p) {
    public boolean apilar(Persona p) {
        // Línea 7: Evalúa si la pila está llena (las 100 casillas ocupadas). | Código original: if
        // (tope == datos.length) {
        if (tope == datos.length) {
            // Línea 8: Devuelve false y finaliza el método. | Código original: return false;
            return false;
        // Línea 9: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
        // Línea 10: Guarda la persona en la casilla libre de arriba. | Código original: datos[tope] =
        // p;
        datos[tope] = p;
        // Línea 11: Sube el tope: la pila tiene un elemento más. | Código original: tope++;
        tope++;
        // Línea 12: Devuelve true para indicar que se apiló correctamente. | Código original: return
        // true;
        return true;
    // Línea 13: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 15: Desapilar: saca y devuelve la persona de arriba, la última que entró. | Código
    // original: public Persona desapilar() {
    public Persona desapilar() {
        // Línea 16: Evalúa si la pila está vacía. | Código original: if (tope == 0) {
        if (tope == 0) {
            // Línea 17: Devuelve null (no hay elemento) en lugar de producir un error. | Código
            // original: return null;
            return null;
        // Línea 18: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
        // Línea 19: Baja el tope: la casilla de arriba queda libre. | Código original: tope--;
        tope--;
        // Línea 20: Toma la persona que estaba arriba. | Código original: Persona p = datos[tope];
        Persona p = datos[tope];
        // Línea 21: Limpia la casilla que quedó libre. | Código original: datos[tope] = null;
        datos[tope] = null;
        // Línea 22: Devuelve la persona sacada de la pila. | Código original: return p;
        return p;
    // Línea 23: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 25: Cima: devuelve la persona de arriba sin sacarla de la pila. | Código original: public
    // Persona cima() {
    public Persona cima() {
        // Línea 26: Evalúa si la pila está vacía. | Código original: if (tope == 0) {
        if (tope == 0) {
            // Línea 27: Devuelve null (no hay elemento) en lugar de producir un error. | Código
            // original: return null;
            return null;
        // Línea 28: Cierra el bloque de código que se abrió anteriormente. | Código original: }
        }
        // Línea 29: Devuelve el elemento de arriba (la casilla tope - 1) sin quitarlo. | Código
        // original: return datos[tope - 1];
        return datos[tope - 1];
    // Línea 30: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 32: EstaVacia: indica si la pila no tiene elementos. | Código original: public boolean
    // estaVacia() {
    public boolean estaVacia() {
        // Línea 33: Devuelve true si el tope es 0, es decir, si la pila está vacía. | Código original:
        // return tope == 0;
        return tope == 0;
    // Línea 34: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 36: EstaLlena: indica si la pila ya no admite más elementos. | Código original: public
    // boolean estaLlena() {
    public boolean estaLlena() {
        // Línea 37: Devuelve true si las 100 casillas están ocupadas. | Código original: return tope
        // == datos.length;
        return tope == datos.length;
    // Línea 38: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 40: Tamano: devuelve cuántos elementos tiene la pila. | Código original: public int
    // tamano() {
    public int tamano() {
        // Línea 41: Devuelve el número de elementos de la pila. | Código original: return tope;
        return tope;
    // Línea 42: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }
// Línea 43: Cierra el bloque de código que se abrió anteriormente. | Código original: }
}
