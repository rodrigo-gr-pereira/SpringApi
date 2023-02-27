package br.com.projeto.api.services;

import br.com.projeto.api.models.Mensagem;
import br.com.projeto.api.models.Pessoa;
import br.com.projeto.api.repositories.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Servico {
    @Autowired
    private Mensagem mensagem;
    @Autowired
    private Repositorio acao;

    public ResponseEntity<?> cadastrar(Pessoa obj) {
        if (obj.getNome().equals("")) {
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }
    //Metodo para selecionar pesssoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }


    // Metodo para selecionar pessoas através do código
    public ResponseEntity<?> selecionarByCodigo(int codigo){
        mensagem.setMensagem("Não foi encontrada nenhuma pessoa.");
        if (acao.countByCodigo(codigo)== 0){
            mensagem.setMensagem("Não foi encontrada nenhuma pessoa.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }

    }


    //Metodo para editar dados
    public ResponseEntity<?> editar (Pessoa obj) {
        if (acao.countByCodigo(obj.getCodigo()) == 0) {
            mensagem.setMensagem("O códio informado não existe.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if (obj.getNome().equals("")) {
            mensagem.setMensagem("E necessário  informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade válida");
            return  new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
            mensagem.setMensagem("OK");
            return  new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

    //mMétodp para remover registros
    public ResponseEntity<?> remover (int codigo) {
        if (acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);

            mensagem.setMensagem("Pessoa removida com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

}