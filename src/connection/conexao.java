/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class conexao {
    
    
    private static final String DRIVER = "com.mysql.jdbc.Driver"; //Localização do Driver de banco de dados Java
    private static final String URL = "jdbc:mysql://localhost:3306/profonline"; //Url do banco de dados
    private static final String USER = "root"; //Usuário do banco de dados
    private static final String PASS = ""; //Senha do Usuário do banco
    
    public static Connection getConnection(){ //função para criação de conexão do banco com o Java
        try {
            Class.forName(DRIVER); //Classe para ser usado
            return DriverManager.getConnection(URL, USER, PASS); //Acesso ao banco
        } catch ( SQLException ex) { //Caso a conexão falhe
            JOptionPane.showMessageDialog(null,"ERRO NA CONEXÃO!","AVISO!!",JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro na Classe",ex);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"ERRO NA CONEXÃO! ");
            throw new RuntimeException("ERRO NA CONEXÃO",e);
        }
    }
    
    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void closeConnection(Connection con,PreparedStatement stmt){
        closeConnection(con);
        
        if(stmt != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void closeConnection(Connection con,PreparedStatement stmt, ResultSet rs){
        
        closeConnection(con, stmt);
        if(rs != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
