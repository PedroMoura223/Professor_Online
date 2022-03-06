/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JInternalFrame;

import bean.boletimBEAN;
import connection.conexao;
import dao.boletimDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.TelaProfessor;

/**
 *
 * @author Aluno
 */
public class GerBoletim extends javax.swing.JInternalFrame {

    /**
     * Creates new form GerBoletim
     */
    public GerBoletim() {
        initComponents();
        
        puxarBoletim();
    }
    
    public void puxarBoletim(){
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM boletim");
            rs = stmt.executeQuery();
            
            DefaultTableModel table = (DefaultTableModel) boletim.getModel();
            table.setNumRows(0);
            while(rs.next()){
                Object[] l = {rs.getInt("id"),rs.getString("nome"), rs.getString("disciplina"), rs.getString("serie"), rs.getDouble("prova_parcial"), rs.getDouble("prova_bimestral"), rs.getDouble("media"), rs.getString("resultado")};
                table.addRow(l);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        boletim = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        mostrar = new javax.swing.JButton();
        deletar = new javax.swing.JButton();
        pesquisar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boletim.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        boletim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Disciplina", "Serie", "Prova Parcial", "Prova Bimestral", "Média", "Resultado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        boletim.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(boletim);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 790, 337));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/GerBoletim.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mostrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mostrar.setText("MOSTRAR TUDO");
        mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarActionPerformed(evt);
            }
        });
        getContentPane().add(mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 610, 220, 60));

        deletar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deletar.setText("DELETAR DADOS");
        deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarActionPerformed(evt);
            }
        });
        getContentPane().add(deletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 610, 200, 60));

        pesquisar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pesquisar.setText("PESQUISAR DADOS");
        pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, -1, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarActionPerformed
        boletimBEAN ab = new boletimBEAN();
        boletimDAO ad = new boletimDAO();
        
        if(boletim.getSelectedRow() != -1){
            DefaultTableModel table = (DefaultTableModel) boletim.getModel();
            
            int id = Integer.parseInt(String.valueOf(boletim.getValueAt(boletim.getSelectedRow(), 0)));
            ad.deletar(id);
            table.removeRow(boletim.getSelectedRow());
            
        }else{
            JOptionPane.showMessageDialog(null,"Selecione uma linha da tabela para exclusão","Aviso",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deletarActionPerformed

    private void pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarActionPerformed
        boletimDAO bt = new boletimDAO();
        
        Connection con = conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int n = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção:\n [1] - Pesquiar por ID\n [2] - Pesquisar por nome"));
        
        switch(n){
            case 1:
        
            try {
                
                int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID: "));
                
                stmt = con.prepareStatement("SELECT * FROM boletim WHERE id like '%"+id+"%'");
                rs = stmt.executeQuery();
                
                DefaultTableModel table = (DefaultTableModel) boletim.getModel();
                table.setNumRows(0);
                
                while(rs.next()){
                    Object[] l = {rs.getInt("id"), rs.getString("nome"), rs.getString("disciplina"), rs.getString("serie"), rs.getDouble("prova_parcial"), rs.getDouble("prova_bimestral"), rs.getDouble("media"), rs.getString("resultado")};
                    table.addRow(l);
                }
                
            } catch (SQLException ex) {
                System.out.println("ERRO!  "+ex);
            }
        
            break;
            
            case 2:
                
            try {
                
                String nome1 = JOptionPane.showInputDialog("Digite o nome do aluno: ");
                
                stmt = con.prepareStatement("SELECT * FROM boletim WHERE nome like '%"+nome1+"%'");
                rs = stmt.executeQuery();
                
                DefaultTableModel table = (DefaultTableModel) boletim.getModel();
                table.setNumRows(0);
                
                while(rs.next()){
                    Object[] l = {rs.getInt("id"), rs.getString("nome"), rs.getString("disciplina"), rs.getString("serie"), rs.getDouble("prova_parcial"), rs.getDouble("prova_bimestral"), rs.getDouble("media"), rs.getString("resultado")};
                    table.addRow(l);
                }
                
            } catch (SQLException ex) {
                System.out.println("ERRO!  "+ex);
            }
            
            break;
        }
    }//GEN-LAST:event_pesquisarActionPerformed

    private void mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarActionPerformed
       DefaultTableModel table = (DefaultTableModel) boletim.getModel();
       table.setNumRows(0);
       puxarBoletim();
    }//GEN-LAST:event_mostrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable boletim;
    private javax.swing.JButton deletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton mostrar;
    private javax.swing.JButton pesquisar;
    // End of variables declaration//GEN-END:variables
}
