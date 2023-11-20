package mx.uv;

public class Usuario {
    private String nombre;
    private String password;
    private String email;

    // constructors, getters and setters
    public Usuario(String nombre, String password, String email){
        this.nombre = nombre;
        this.password = password;
        this.email = email;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
}