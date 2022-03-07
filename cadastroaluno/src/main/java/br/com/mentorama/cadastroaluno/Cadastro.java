package br.com.mentorama.cadastroaluno;

public class Cadastro {

    private Integer Id;
    private String nome;
    private Integer idade;

    public Cadastro(Integer id, String nome, Integer idade) {
        this.Id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
