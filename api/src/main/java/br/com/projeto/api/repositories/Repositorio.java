package br.com.projeto.api.repositories;

import br.com.projeto.api.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repositorio extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findAll();
    List<Pessoa> findByCodigo(int codigo);
}
