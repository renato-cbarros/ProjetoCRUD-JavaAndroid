package com.example.renat.wttconce.model;

public class Cliente {

    private Long idPessoa;
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private String situacao;

    @Override
    public String toString() {
        return "ID Cliente = " + idPessoa + " nome = " + nome + " cpf = " + cpf + " login = " + login + " senha = " + senha + " situação = " + situacao;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
