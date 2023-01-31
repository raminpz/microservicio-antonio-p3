package com.softrami.cursos.app.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softrami.cursos.app.models.Curso;

@RestController
public class CursoController {
	
	private List<Curso> cursos;
	@PostConstruct
	public void init() {
		cursos = new ArrayList<>();
		cursos.add(new Curso("Spring",24,"tarde"));
		cursos.add(new Curso("Spring boot",4,"mañana"));
		cursos.add(new Curso("Java 8",20,"sabados"));
		cursos.add(new Curso("Phyton",24,"tarde"));
		cursos.add(new Curso("Angular",40,"domingos"));
	}
	
	@GetMapping(value = "cursos",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> getCursos(){
		return cursos;
	}
	
	
	@GetMapping(value = "curso",produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso getCurso() {
		return new Curso("Java",100,"mañana");
	}
	
	@GetMapping(value = "cursos/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCurso(@PathVariable("name") String nombre){
		List<Curso> aux = new ArrayList<>();
		for (Curso c : cursos) {
			if (c.getNombre().contains(nombre)) {
				aux.add(c);
			}
		}
		return aux;
	}
	
	@DeleteMapping(value = "curso/{name}")
	public void eliminarCurso(@PathVariable("name") String nombre) {
		cursos.removeIf(c ->c.getNombre().equals(nombre));
	}
	
	@PostMapping(value = "curso", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> altaCurso(@RequestBody Curso curso){
		cursos.add(curso);
		return cursos;
	}
	
	@PutMapping(value = "curso", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> actualizarCurso(@RequestBody Curso curso){
		for (int i = 0; i < cursos.size(); i++) {
			if (cursos.get(i).getNombre().equals(curso.getNombre())) {
				cursos.set(i, curso);
			}
		}
		return cursos;
	}

}
