/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Aluno
 */
public class ProfessorBean {
    
    private String nome;
    private String disciplina;
    private String escola_pertencente;
    private String email;
    private String senha;
    private String telefone;
    private String confirmar_senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getEscola_pertencente() {
        return escola_pertencente;
    }

    public void setEscola_pertencente(String escola_pertencente) {
        this.escola_pertencente = escola_pertencente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
       
       
    
}
