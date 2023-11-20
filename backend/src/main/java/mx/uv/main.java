package mx.uv;

import static spark.Spark.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mx.uv.Usuario;
/**
 * Hello world!
 *
 */
public class main
{
    public static void main( String[] args )
    {
        //fuente:https://gist.github.com/saeidzebardast/e375b7d17be3e0f4dddf
        options("/*",(request,response)->{
        String accessControlRequestHeaders=request.headers("Access-Control-Request-Headers");
        if(accessControlRequestHeaders!=null){
        response.header("Access-Control-Allow-Headers",accessControlRequestHeaders);
        }
        String accessControlRequestMethod=request.headers("Access-Control-Request-Method");
        if(accessControlRequestMethod!=null){
        response.header("Access-Control-Allow-Methods",accessControlRequestMethod);
        }
        return "OK";
        });
        before((request,response)->response.header("Access-Control-Allow-Origin","*"));

        List<Usuario> listaUsuarios = new ArrayList<>();

        get("/", (req, res) -> "mensaje de prueba");

        get("/alumno",(req, res)->{
                System.out.println(req.contentLength());
                System.out.println(req.contentType());
                System.out.println(req.contextPath());
                return "hola"+req.queryParams("nombre");
        });

        post("/usuario",(req,res)-> {
            Map<String, String[]> usuarios = new HashMap<>();
            usuarios = req.queryMap().toMap();

            String nombre = usuarios.get("nombre")[0];
            String password = usuarios.get("password")[0];
            String email = usuarios.get("email")[0];
            Usuario newUser = new Usuario(nombre, password, email);
            listaUsuarios.add(newUser);
            JsonObject oRespuesta = new JsonObject();
            for(Usuario u : listaUsuarios){
                oRespuesta.addProperty("Nombre",u.getNombre());
                oRespuesta.addProperty("Password",u.getPassword());
                oRespuesta.addProperty("email",u.getEmail());
            } 
            res.type("application/json");
            res.status(200);
            return oRespuesta;
         });

        get("/usuario",(req,res)->{
            System.out.println(req.body());
            res.status(200);
            res.type("application/json");
            JsonObject oRespuesta = new JsonObject();
            int cont = 1;
            for(Usuario u: listaUsuarios){
                JsonObject temp = new JsonObject();
                temp.addProperty("Nombre",u.getNombre());
                temp.addProperty("Password",u.getPassword());
                temp.addProperty("email",u.getEmail());
                oRespuesta.add("user"+cont, temp);
                cont++;
            }
            return oRespuesta;
        });

        delete("/usuario/:email", (req, res) -> {
            String email = req.params(":email");
            for(Usuario u: listaUsuarios){
                if(u.getEmail().equals(email)){
                    listaUsuarios.remove(u);
                    JsonObject oRespuesta = new JsonObject();
                    oRespuesta.addProperty("nombre", u.getNombre());
                    oRespuesta.addProperty("password", u.getPassword());
                    oRespuesta.addProperty("email", u.getEmail());
                    res.type("application/json");
                    res.status(200);
                    return oRespuesta;
                }
            }
            res.status(404);
            return "Usuario no encontrado";
         });
         
        

        put("/usuario/:email", (req,res)->{
            String reqEmail = req.params(":email");
            Usuario removeUsuario = null;
            Usuario newUser = null;
            for(Usuario u: listaUsuarios){
                if(u.getEmail().equals(reqEmail)){
                    Map<String, String[]> usuarios = new HashMap<>();
                    usuarios = req.queryMap().toMap();

                    String nombre = usuarios.get("nombre")[0];
                    String password = usuarios.get("password")[0];
                    String email = usuarios.get("email")[0];
                    newUser = new Usuario(nombre, password, email);

                    removeUsuario = u;
                }
            }
            if (removeUsuario != null) {
                listaUsuarios.remove(removeUsuario);
                listaUsuarios.add(newUser);
                JsonObject oRespuesta = new JsonObject();
                oRespuesta.addProperty("nombre", newUser.getNombre());
                oRespuesta.addProperty("password", newUser.getPassword());
                oRespuesta.addProperty("email", newUser.getEmail());
                res.type("application/json");
                res.status(200);
                return oRespuesta;
            }
            res.status(404);
            return "Error el usuario no existe";
        });
    }
}
