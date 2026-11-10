// Línea 1: Declara una clase pública; aquí se agrupan los atributos y métodos de esta parte del
// programa. | Código original: public class Aula {
public class Aula {

    // Línea 3: Declara un atributo privado de la clase para almacenar información del objeto. | Código
    // original: private int idAula;
    private int idAula;
    // Línea 4: Declara un atributo privado de la clase para almacenar información del objeto. | Código
    // original: private String nombreAula;
    private String nombreAula;
    // Línea 5: Declara un atributo privado de la clase para almacenar información del objeto. | Código
    // original: private String ubicacion;
    private String ubicacion;
    // Línea 6: Declara un atributo privado de la clase para almacenar información del objeto. | Código
    // original: private String estado;
    private String estado;

    // Línea 8: Declara un método o constructor público de la clase. | Código original: public Aula() {
    public Aula() {
    // Línea 9: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 11: Declara un método o constructor público de la clase. | Código original: public
    // Aula(int idAula, String nombreAula,
    public Aula(int idAula, String nombreAula,
            // Línea 12: Forma parte de la estructura del programa y cumple la función indicada por
            // esta instrucción. | Código original: String ubicacion, String estado) {
            String ubicacion, String estado) {

        // Línea 14: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.idAula = idAula;
        this.idAula = idAula;
        // Línea 15: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.nombreAula = nombreAula;
        this.nombreAula = nombreAula;
        // Línea 16: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.ubicacion = ubicacion;
        this.ubicacion = ubicacion;
        // Línea 17: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.estado = estado;
        this.estado = estado;
    // Línea 18: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 20: Declara un método o constructor público de la clase. | Código original: public int
    // getIdAula() {
    public int getIdAula() {
        // Línea 21: Devuelve el valor indicado y finaliza la ejecución del método. | Código original:
        // return idAula;
        return idAula;
    // Línea 22: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 24: Declara un método o constructor público de la clase. | Código original: public void
    // setIdAula(int idAula) {
    public void setIdAula(int idAula) {
        // Línea 25: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.idAula = idAula;
        this.idAula = idAula;
    // Línea 26: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 28: Declara un método o constructor público de la clase. | Código original: public String
    // getNombreAula() {
    public String getNombreAula() {
        // Línea 29: Devuelve el valor indicado y finaliza la ejecución del método. | Código original:
        // return nombreAula;
        return nombreAula;
    // Línea 30: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 32: Declara un método o constructor público de la clase. | Código original: public void
    // setNombreAula(String nombreAula) {
    public void setNombreAula(String nombreAula) {
        // Línea 33: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.nombreAula = nombreAula;
        this.nombreAula = nombreAula;
    // Línea 34: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 36: Declara un método o constructor público de la clase. | Código original: public String
    // getUbicacion() {
    public String getUbicacion() {
        // Línea 37: Devuelve el valor indicado y finaliza la ejecución del método. | Código original:
        // return ubicacion;
        return ubicacion;
    // Línea 38: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 40: Declara un método o constructor público de la clase. | Código original: public void
    // setUbicacion(String ubicacion) {
    public void setUbicacion(String ubicacion) {
        // Línea 41: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.ubicacion = ubicacion;
        this.ubicacion = ubicacion;
    // Línea 42: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 44: Declara un método o constructor público de la clase. | Código original: public String
    // getEstado() {
    public String getEstado() {
        // Línea 45: Devuelve el valor indicado y finaliza la ejecución del método. | Código original:
        // return estado;
        return estado;
    // Línea 46: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 48: Declara un método o constructor público de la clase. | Código original: public void
    // setEstado(String estado) {
    public void setEstado(String estado) {
        // Línea 49: Ejecuta la instrucción indicada; el punto y coma marca el final de la sentencia. |
        // Código original: this.estado = estado;
        this.estado = estado;
    // Línea 50: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }

    // Línea 52: Indica que el método siguiente sobrescribe una implementación heredada. | Código
    // original: @Override
    @Override
    // Línea 53: Declara un método o constructor público de la clase. | Código original: public String
    // toString() {
    public String toString() {
        // Línea 54: Devuelve el valor indicado y finaliza la ejecución del método. | Código original:
        // return idAula + " | " + nombreAula + " | "
        return idAula + " | " + nombreAula + " | "
                // Línea 55: Ejecuta la instrucción indicada; el punto y coma marca el final de la
                // sentencia. | Código original: + ubicacion + " | " + estado;
                + ubicacion + " | " + estado;
    // Línea 56: Cierra el bloque de código que se abrió anteriormente. | Código original: }
    }
// Línea 57: Cierra el bloque de código que se abrió anteriormente. | Código original: }
}
