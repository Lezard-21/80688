package uv.mx;

import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.UUID;
/**
 * Hello world!
 *
 */
public class App 
{
    static HashMap<String,Usuario> usuarios = new HashMap<>();
    static Gson gson = new Gson();
    public static void main( String[] args )
    {
        port(80);
        get("/usuario", (req,res)->{
            res.type("application/json");
            // return gson.toJson(usuarios.values());
            return gson.toJson(DAO.getAllProducts());
        });
        post("/usuario", (req,res)->{
            System.out.println(req.body());
            Usuario user = gson.fromJson(req.body(), Usuario.class);
            System.out.println("n:"+user.getNombre());
            System.out.println("p:"+user.getPassword());
            String id = UUID.randomUUID().toString();
            user.setId(id);
            // usuarios.put(id, user);
            DAO.createProduct(user);
            System.out.println("i:"+user.getId());
            // res.type("application/json");
            res.type("application/json");
            res.status(200);
            return gson.toJson(id);

         });

    }
}
