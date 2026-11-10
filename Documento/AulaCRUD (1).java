public class Persona {

    private int idPersona;
    private String nombre;
    private String correo;
    private String estado;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String correo, String estado) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.correo = correo;
        this.estado = estado;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return idPersona + " | " + nombre + " | " + correo + " | " + estado;
    }
}
