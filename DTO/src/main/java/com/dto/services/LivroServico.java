package com.dto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.DTO.LivroDTO;
import com.dto.entities.Livro;
import com.dto.repository.LivroRepository;

@Service
public class LivroServico {
	private final LivroRepository livroRepository;
	
	@Autowired
	public LivroServico(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	public LivroDTO salvar(LivroDTO livroDTO) {
		Livro livro = new Livro(null, livroDTO.nome(), livroDTO.genero(), null);
		Livro salvarLivro = livroRepository.save(livro);
		return new LivroDTO(salvarLivro.getId(), salvarLivro.getNome(), salvarLivro.getGenero(), null);
	}
	
	public LivroDTO atualizar(Long id, LivroDTO livroDTO) {
		Livro existeLivro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		existeLivro.setNome(livroDTO.nome());
		existeLivro.setGenero(livroDTO.genero());
		
		Livro updateLivro = livroRepository.save(existeLivro);
		return new LivroDTO(updateLivro.getId(), updateLivro.getNome(), updateLivro.getGenero(), null);
	}
	public boolean deletar(Long id) {
		Optional <Livro> existeLivro = livroRepository.findById(id);
		if(existeLivro.isPresent()) {
			livroRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Livro> buscarTodos(){
		return livroRepository.findAll();
	}
	public Livro buscarPorId(Long id) {
		Optional <Livro> livro = livroRepository.findById(id);
		return livro.orElse(null);
	}
}
