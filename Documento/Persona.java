public class Aula {

    private int idAula;
    private String nombreAula;
    private String ubicacion;
    private String estado;

    public Aula() {
    }

    public Aula(int idAula, String nombreAula,
            String ubicacion, String estado) {

        this.idAula = idAula;
        this.nombreAula = nombreAula;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return idAula + " | " + nombreAula + " | "
                + ubicacion + " | " + estado;
    }
}
