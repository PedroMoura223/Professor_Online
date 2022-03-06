package dao;

import bean.alunoBEAN;
import connection.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class alunoDAO {
    public void salvar(alunoBEAN ab){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO alunos(matricula,nome,email,telefone,serie,escola) VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, ab.getMatricula());
            stmt.setString(2, ab.getNome());
            stmt.setString(3, ab.getEmail());
            stmt.setString(4, ab.getTelefone());
            stmt.setString(5, ab.getSerie());
            stmt.setString(6, ab.getEscola());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dados Salvos com Sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Verifique se você duplicou a matrícula, ou reveja os dados para cadastrar","Um erro aconteceu durante o cadastro",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void atualizar(alunoBEAN ab,int matricula){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        if(verif(matricula)){
            try {
                stmt = con.prepareStatement("UPDATE alunos SET matricula = ?,nome = ?,email = ?,telefone = ?,serie = ?,escola = ? WHERE matricula = "+matricula);
                stmt.setInt(1, ab.getMatricula());
                stmt.setString(2, ab.getNome());
                stmt.setString(3, ab.getEmail());
                stmt.setString(4, ab.getTelefone());
                stmt.setString(5, ab.getSerie());
                stmt.setString(6, ab.getEscola());

                stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Dados Atualizado com Sucesso!!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Esse registro não foi encontrados");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Matrícula não existe ou foi excluído");
        }
        
        
    }
    
    public void deletar(int matricula){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        
        if(verif(matricula)){
            
            try {
                stmt = con.prepareStatement("DELETE FROM alunos WHERE matricula = "+matricula);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Registro excluido com Sucesso!!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Esse registro não foi encontrados");
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null,"Matrícula não existe ou foi excluído");
            
        }
    }
    
    public boolean verif(int matricula){
        try {
            Connection con = conexao.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = con.prepareStatement("SELECT * FROM alunos WHERE matricula like "+matricula+"");
            rs = stmt.executeQuery();
            
            if(rs.next()){
                 return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"É preciso da matrícula para confirmação!!","Aviso",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
