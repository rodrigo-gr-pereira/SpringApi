package br.com.projeto.api.controlles;

import br.com.projeto.api.models.Pessoa;
import br.com.projeto.api.repositories.Repositorio;
import br.com.projeto.api.services.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico; // Classe objeto

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
    @PostMapping("/api")
    public ResponseEntity<?> cadastar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }
    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }
    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){
        return servico.selecionarByCodigo(codigo);
    }
    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){
        return servico.editar(obj);
    }
    @DeleteMapping("/api/{codigo}")
    //(value = "codigo")
    public ResponseEntity<?>remover(@PathVariable int codigo){
        return servico.remover(codigo);
        // acao.deleteById(codigo);
    }
    //Contador
    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }
    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes(){
        return acao.findByOrderByNome();
    }
    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2(){
        return acao.findByNomeOrderByIdadeDesc("Rodrigo");
    }
    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem(){
        return acao.findByNomeContaining("a");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("a");
    }
    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom(){
        return acao.findByNomeEndsWith("o");
    }

    @GetMapping("/api/somaIdades")
    public  int somaIdades(){
        return acao.somaIdades();

    }
    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(37);
    }
    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
  //  public String status(){
    //    return  "Configurando status";
    }

}
