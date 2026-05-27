package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        /* Essa linha cria a URI completa do recurso recém-criado (ex: /users/5) para ser
        retornada no header Location da resposta HTTP 201 (Created), seguindo as boas práticas REST.
        Funções explicadas:
        1 - ServletUriComponentsBuilder.fromCurrentRequest(): Captura a URI da requisição atual;
        Exemplo: Se a requisição foi para http://localhost:8080/users, essa parte retorna exatamente isso.
        2 - .path("/{id}"): Adiciona um caminho extra à URI, Neste caso, adiciona /{id} ao final;
        Resultado parcial: http://localhost:8080/users/{id}.
        3 - .buildAndExpand(obj.getId()): Substitui o placeholder {id} pelo valor real, se obj.getId()
        retorna 5, por exemplo;
        Resultado: http://localhost:8080/users/5
        4 - .toUri(): Converte o componente construído para um objeto do tipo URI.

         */
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        /* Retorna uma resposta HTTP 204 (no content), indicando sucesso sem corpo de resposta
        ideal para operações Delete ou atualizações que não precisam retornar dados.
        de forma mais completa, segue o Breakdown:
        1 - RespondeEntity: Classe do Spring para construir respostas HTTP completas;
        2 - .noContent(): Define o status HTTP como 204 (No content);
        3 - .build(): Constrói o objeto ResponseEntity (sem corpo).
         */
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
