package com.dto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.DTO.LivroDTO;
import com.dto.entities.Livro;
import com.dto.services.LivroServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {
	
	private final LivroServico livroServico;
	
	@Autowired
	public LivroController(LivroServico livroServico) {
		this.livroServico = livroServico;
	}
	
	@PostMapping
	public ResponseEntity<LivroDTO> criar(@RequestBody @Valid LivroDTO livroDTO){
		LivroDTO salvarLivro = livroServico.salvar(livroDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarLivro);
	}
	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> alterar(@PathVariable Long id, @RequestBody @Valid LivroDTO livroDTO){
		LivroDTO alteraLivroDTO = livroServico.atualizar(id, livroDTO);
		if(alteraLivroDTO != null) {
			return ResponseEntity.ok(alteraLivroDTO);
		}
		else {
			return ResponseEntity.notFound().build();		
			}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> apagar(@PathVariable Long id){
		boolean apagar = livroServico.deletar(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Long id){
		Livro livro = livroServico.buscarPorId(id);
		if(livro != null) {
			return ResponseEntity.ok(livro);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping 
	public ResponseEntity<List<Livro>> buscaTodos(){
		List<Livro> livro = livroServico.buscarTodos();
		return ResponseEntity.ok(livro);
	}
}
