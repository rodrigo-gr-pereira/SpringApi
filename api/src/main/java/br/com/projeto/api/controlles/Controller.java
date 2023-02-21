package br.com.projeto.api.controlles;

import br.com.projeto.api.models.Pessoa;
import br.com.projeto.api.repositories.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Repositorio acao;
    @PostMapping("/api")
    public Pessoa cadastar(@RequestBody Pessoa obj){
        return acao.save(obj);
    }
    @GetMapping("/api")
    public List<Pessoa> selecionar(){
        return acao.findAll();
    }
    @GetMapping("/api/{codigo}")
    public List<Pessoa> selecionarPeloCodigo(@PathVariable int codigo){
        return acao.findByCodigo(codigo);
    }
    @PutMapping("/api")
    public Pessoa editar(@RequestBody Pessoa obj){
        return acao.save(obj);
    }
    @DeleteMapping("/api/{codigo}")
    public void remover(@PathVariable(value = "codigo") int codigo){
        acao.deleteById(codigo);
    }

    @GetMapping("/")
    public  String mensagem(){
        return  "Hello Word!";
    }

    @GetMapping("/boasVindas/{nome}")
        public String boasVindas(@PathVariable String nome){
        return "Seja bem vindo(a) " + nome;
    }

    @PostMapping ("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }
}
