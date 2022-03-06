/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.ProfessorBean;
import connection.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class ProfessorDao {
    
    public void create(ProfessorBean p){ //Função para salvar os dados
        Connection con = conexao.getConnection(); //Variável que irá receber a conexão
        PreparedStatement stmt = null; //Variável que irá guardar o comando SQL
        try {
            if(verificar(p.getEmail())){
                JOptionPane.showMessageDialog(null,"Este Email já existe");
            }else{
            stmt = con.prepareStatement("INSERT INTO professores(nome,email,telefone,escola_per,disciplina,senha) VALUES (?,?,?,?,?,?)"); //Varável stmt irá receber os comando SQL
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getEscola_pertencente());
            stmt.setString(5, p.getDisciplina());
            stmt.setString(6, p.getSenha());

            
            stmt.executeUpdate();//Executar o comando
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso"); // Exibir mensagem caso tudo funcione
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" + ex);
        }finally{
            conexao.closeConnection(con, stmt);
        }
    }
    
    public void atualizar(ProfessorBean p, String email){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        if(verificar(email)){
            
        
        
            try {
                stmt = con.prepareStatement("update professores set nome = ?, email = ?, telefone = ?,escola_per = ?, disciplina = ?, senha = ? where email = '"+email+"'");
                stmt.setString(1, p.getNome());
                stmt.setString(2, p.getEmail());
                stmt.setString(3, p.getTelefone());
                stmt.setString(4, p.getEscola_pertencente());
                stmt.setString(5, p.getDisciplina());
                stmt.setString(6, p.getSenha());

                stmt.executeUpdate();//Executar o comando

                JOptionPane.showMessageDialog(null,"Atualizado com Sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao Atualizar: "+ex);
            }finally{
                conexao.closeConnection(con, stmt);
            }
        
        }else{
            JOptionPane.showMessageDialog(null,"Usuário não existe ou foi deletado","AVISO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean verificar(String email){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM professores WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERRO: "+ex);
        }
        
        return false;
    }
    
    public void deletar(String email){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM professores WHERE email = '"+email+"'");
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Professor deletado com Sucesso!!");
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
