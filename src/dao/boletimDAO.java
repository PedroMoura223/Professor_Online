package dao;

import bean.boletimBEAN;
import connection.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class boletimDAO {
    public void salvar(boletimBEAN ab){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO boletim(nome, disciplina, serie, prova_parcial, prova_bimestral, media, resultado) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, ab.getNome());
            stmt.setString(2, ab.getDisciplina());
            stmt.setString(3, ab.getSerie());
            stmt.setDouble(4, ab.getProvaPar());
            stmt.setDouble(5, ab.getProvaBim());
            stmt.setDouble(6, ab.getMedia());
            stmt.setString(7, ab.getResultado());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dados salvos no banco de dados!!");
        } catch (SQLException ex) {
            Logger.getLogger(boletimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexao.closeConnection(con, stmt);
        }
    }
    
    public void deletar(int id){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM boletim WHERE id = "+id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(boletimDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Pesquisar(int id, String nome, boletimBEAN bb){
        
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int n = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção: [1] - Pesquiar por ID\n [2] - Pesquisar por nome"));
        
        switch(n){
            case 1:
        {
            try {
                stmt = con.prepareStatement("SELECT * FROM boletim WHERE id = "+id);
                rs = stmt.executeQuery();
                
                
                    bb.setId(rs.getInt("id"));
                    bb.setNome(rs.getString("nome"));
                    bb.setDisciplina("disciplina");
                    bb.setSerie(rs.getString("serie"));
                    bb.setProvaPar(rs.getDouble("prova_parcial"));
                    bb.setProvaBim(rs.getDouble("prova_bimestral"));
                    bb.setMedia(rs.getDouble("media"));
                    bb.setResultado(rs.getString("resultado"));
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(boletimDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            break;
        }
        
    }
}
