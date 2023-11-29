package uv.mx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO {
    private static Conexion c = new Conexion();

    public static List<Usuario> getAllProducts() {
        ResultSet rs = null;
        List<Usuario> resultado = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection conn = Conexion.getConnection();
        String query = "SELECT * from users";

        try {
            preparedStatement = conn.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Usuario p = new Usuario(rs.getString("id"), rs.getString("nombre"), rs.getString("pass"));
                resultado.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el prepareStatment: " + e.getMessage());
            }
        }
        return resultado;
    }

    public static String createProduct(Usuario p) {
        PreparedStatement preparedStatement = null;
        Connection conn = null;
        String msj = "";

        conn = Conexion.getConnection();
        try {
            String query = "INSERT INTO users (id, nombre, pass) values (?,?,?)";
            preparedStatement = (PreparedStatement) conn.prepareStatement(query);
            preparedStatement.setString(1, p.getId());
            preparedStatement.setString(2, p.getNombre());
            preparedStatement.setString(3, p.getPassword());
            if (preparedStatement.executeUpdate() > 0)
                msj = "product added";
            else
                msj = "product not added";

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el prepareStatment: " + e.getMessage());
            }
        }
        return msj;
    }
}
