package br.com.mentorama.cadastroaluno;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cadastroregistro")

public class AlunocadController {

    private List<Cadastro> cadastros;

    private AlunocadController() {

        this.cadastros = new ArrayList<>();

        Cadastro aluno1 = new Cadastro(01, "André", 19);
        Cadastro aluno2 = new Cadastro(02, "Bruna", 18);
        Cadastro aluno3 = new Cadastro(03, "Carlos", 21);
        Cadastro aluno4 = new Cadastro(04, "Bruna", 21);
        Cadastro aluno5 = new Cadastro(05, "Diana", 21);

        this.cadastros.add(aluno1);
        this.cadastros.add(aluno2);
        this.cadastros.add(aluno3);
        this.cadastros.add(aluno4);
        this.cadastros.add(aluno5);
    }

    // 1.1) Listar todos:
    @GetMapping
    public List<Cadastro> findAll() {
        return cadastros;

    }

    // 1.2) Filtar por nome:
    @GetMapping("/{nome}")
    public List<Cadastro> listByName(@PathVariable("nome") String nome) {
        return cadastros.stream()
                .filter(cad -> cad.getNome().contains(nome))
                .collect(Collectors.toList());
    }

    // 1.3) Filtar por idade:
    @GetMapping("/{nome} /{idade}")
    public List<Cadastro> listByIdade(@PathVariable("idade") Integer idade) {
        return cadastros.stream()
                .filter(cad -> cad.getIdade().equals(idade))
                .collect(Collectors.toList());
    }

    // 2) Buscar Aluno por Id:
    @GetMapping("/{nome} /{idade}/{id}")
    public Cadastro findById(@PathVariable("id") Integer id) {
        return cadastros.stream()
                .filter(cad -> cad.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 3) Cadastrar um Novo Aluno:
    @PostMapping
    public ResponseEntity <Integer> add(@RequestBody final Cadastro cadastro) {
        if (cadastro.getId() == null) {
            cadastro.setId(cadastros.size() + 1);
            }

        cadastros.add(cadastro);
        return new ResponseEntity<>(cadastro.getId() , HttpStatus.CREATED);
    }

    // 4) Alterar a Idade no Cadastro de um Aluno:
    @PatchMapping("/{id} /{nome}")
    public ResponseEntity update(@RequestBody final Cadastro cadastro , @PathVariable("id") Integer id){
        cadastros.stream()
                .filter((cad-> cad.getId().equals(id)))
                .forEach(cad-> cad.setIdade(cadastro.getIdade()));
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    // 5) Remover o Cadastro de um Aluno:
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
         cadastros.removeIf(cad-> cad.getId().equals(id));
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}










