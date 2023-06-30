/*
Realiza un servidor TCP que retorne los datos de un empleado
consultando a una BD a partir del código del empleado pasado desde
un cliente TCP. El cliente lo puedes hacer en modo consola.
 */
package PSP_T2_TCP_IP.ConsultaBD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vespertino
 */
public class Ej2Servidor {

    //SERVIDOR
    public static void main(String[] args) {

        //REALIZAMOS LAS CONEXIONES CON LA BASE DE DATOS 
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\sqlite-tools-win32-x86-3360000\\ejemplo.db", "", "");

            try {
                //INICIAMOS EL SOCKET CON EL PUERTO DEL CLIENTE
                ServerSocket serverSocket = new ServerSocket(2045);
                //ESPEREMOS A QUE RECIBAMOS ALGO CON EL ACCEPT, Y QUE SE HAGA LA CONEXION
                Socket clienteSocket = serverSocket.accept();
                //HACEMOS UN PUERTO DE SALIDA DE DATOS
                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
                //HACEMOS UN PUERTO DE ENTRADA DE DATOS
                BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

                //RECIBIMOS CON EL IN LOS DATOS DEL CLIENTE
                String inputLine = in.readLine();
                //SACAMOS POR PANTALLA EL COFIGO DEL CLIENTE
                System.out.println("El codigo de CLIENTE ES :" + inputLine);

                //LO METEMOS EN LA CONSULTA SELECT
                String sql = "select * from CLIENTES where ID = " + Integer.parseInt(inputLine) + " ;";

                //CREAMOS UN RESULT SET, DONDE VENDRAN LOS DATOS DE LA CONSULTA
                ResultSet rs;
                //METEMOS EN EL RS EL RESULTADO DEL METODO "SACAR CONSULTA"
                rs = sacarConsulta(conexion, sql);//saca la consulta

                //METEMOS LOS DATOS DE SALIDA DEL RS EN UN STRING
                String outputLine = rs.getString(2).toString();
                //MANDAMOS EL STRING AL CLIENTE CON EL "OUT "!!
                out.println(outputLine);

                //CERRAMOS CONEXIONES
                out.close();
                in.close();
                clienteSocket.close();
                serverSocket.close();

            } catch (IOException ex) {
            }

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TE SACA UN RESULT SET CON EL RESULTADO DE LA CONSULTA
    private static ResultSet sacarConsulta(Connection conexion, String sql) throws SQLException {
        System.out.println(sql);
        PreparedStatement sentencia
                = conexion.prepareStatement(sql);

        try {
            ResultSet rs = sentencia.executeQuery(); //es Query es para hacer consultas select
            // Nos recoremos los objetos de la coleccion

            return rs;

        } catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCI�N:");
            System.out.println("Mensaje:    " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("C�d error:  " + e.getErrorCode());
        }

        sentencia.close(); // Cerrar Statement
        conexion.close(); // Cerrar conexi�n
        return null;
    }

}
