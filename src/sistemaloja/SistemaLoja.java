/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaloja;
import DAO.*;
import Views.Login;
import java.sql.SQLException;
import java.sql.Connection;
/**
 *
 * @author Jefferson César
 */
public class SistemaLoja {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        DAO dao = new DAO();
        dao.conecta();
        
        Login l = new Login();
                
        l.show();
        l.setLocationRelativeTo(null);
        
        Connection conecta = new DAO().conecta();
        System.out.println("CONECTADO");
        conecta.close();
    }
    
}
