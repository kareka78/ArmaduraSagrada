package clases;

public class Usuario {
    private String nombre;
    private String password;
    private Personaje personaje;

    public Usuario(String nombre, String password,Personaje personaje) {
        this.nombre = nombre;
        this.password = password;
        this.personaje=personaje;
    }

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
