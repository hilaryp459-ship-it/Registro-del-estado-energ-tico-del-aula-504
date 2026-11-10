public class PilaAula {

    private Aula[] datos = new Aula[100];
    private int tope = 0;

    public boolean apilar(Aula a) {
        if (tope == datos.length) {
            return false;
        }
        datos[tope] = a;
        tope++;
        return true;
    }

    public Aula desapilar() {
        if (tope == 0) {
            return null;
        }
        tope--;
        Aula a = datos[tope];
        datos[tope] = null;
        return a;
    }

    public Aula cima() {
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
