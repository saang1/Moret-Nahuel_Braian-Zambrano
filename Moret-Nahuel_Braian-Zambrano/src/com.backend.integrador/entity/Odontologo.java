package src.com.backend.integrador.entity;

public class Odontologo {
    private int id;
    private String nombre;
    private String apellido;
    private int matricula;

    
    public Odontologo(String nombre, String apellido, int matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Odontologo(int id, String nombre, String apellido, int matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }



    @Override
    public String toString() {
        return "Odontologo{id=" + this.id + ", nombre='" + this.nombre + "', apellido='" + this.apellido + "', matricula=" + this.matricula + "}";
    }
}

   