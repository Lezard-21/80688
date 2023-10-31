package mx.uv;

import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class main
{
    public static void main( String[] args )
    {
        get("/", (req, res) -> "<h1>Bienvenido</h1>");
        get("/hola", (req, res) -> "<h1>Hello wold</h1>");
        get("/adios", (req, res) -> "<h1>Adios Mundo</h1>");
        get("/fin", (req, res) -> "<h1>El fin se acerca</h1>");
    }
}
