public class PilaPersona {

    private Persona[] datos = new Persona[100];
    private int tope = 0;

    public boolean apilar(Persona p) {
        if (tope == datos.length) {
            return false;
        }
        datos[tope] = p;
        tope++;
        return true;
    }

    public Persona desapilar() {
        if (tope == 0) {
            return null;
        }
        tope--;
        Persona p = datos[tope];
        datos[tope] = null;
        return p;
    }

    public Persona cima() {
        if (tope == 0) {
            return null;
        }
        return datos[tope - 1];
    }

    public boolean estaVacia() {
        return tope == 0;
    }

    public boolean estaLlena() {
        return tope == datos.length;
    }

    public int tamano() {
        return tope;
    }
}
