package mx.uv;

import static spark.Spark.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
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

        get("/", (req, res) -> "<h1>Bienvenido</h1>");
        get("/hola", (req, res) -> "<h1>Hello wold</h1>");
        get("/adios", (req, res) -> "<h1>Adios Mundo</h1>");
        get("/fin", (req, res) -> "<h1>El fin se acerca</h1>");
        get("/alumno", (req, res) -> "{\"alumno\":\"jhon\", \"matricula\":\"s0001\",\"carrera\":\"TECO\"}");
        get("/say/*/to/*", (request, response) -> {
            return "Number of splat parameters: " + request.splat().length;
        });

        post("/alumno",(req, res)->{
                System.out.println(req.contentLength());
                System.out.println(req.contentType());
                System.out.println(req.contextPath());
                return "hola"+req.queryParams("nombre");
        });
        get("/tipo-usuario",(req,res)->{
            System.out.println(req.queryParams("email") + " " + req.queryParams("password"));
            System.out.println(req.body());
            res.status(200);
            res.type("application/json");
            JsonObject oRespuesta = new JsonObject();
            oRespuesta.addProperty("Nombre","David");
            oRespuesta.addProperty("Password","david");
            oRespuesta.addProperty("email",req.queryParams("email"));
            return oRespuesta;
        });

        post("/tipo-usuario",(req,res)->{
            System.out.println(req.queryParams("email") + " " + req.queryParams("password"));
            System.out.println(req.body());
            res.status(200);
            res.type("application/json");
            JsonObject oRespuesta = new JsonObject();
            oRespuesta.addProperty("Nombre","David");
            oRespuesta.addProperty("Password","david");
            oRespuesta.addProperty("email",req.queryParams("email"));
            return oRespuesta;
        });
        // post("/json", (req,res)->{
        //     JsonParser parser = new JsonParser();
        //     JsonElement arbol = parser.parse(req.body());
        //     return arbol.getAsJsonObject().get("nombre").getAsString();
        // });
    }
}
