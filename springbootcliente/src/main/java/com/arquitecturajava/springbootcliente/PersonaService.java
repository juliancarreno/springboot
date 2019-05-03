package com.arquitecturajava.springbootcliente;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonaService {

	
	RestTemplate plantilla = new RestTemplate();
	
	
	public Iterable<Persona> buscarTodos() {
		
		ResponseEntity<List<Persona>> respuesta = plantilla.exchange("http://localhost:8080/webapi/personas",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Persona>>() {
				});
		
		// cuando obtengamos el body de la respuesta que es una estructura JSON la convierta en una lista de objetos
		List<Persona> personas = respuesta.getBody();
		return personas;

	}
	
	public void insertar(Persona persona) {
		//Realiza una peticon HTTP de tipo POST y enviar a la url un objeto Persona
		HttpEntity<Persona> peticion = new HttpEntity<>(persona);
		plantilla.postForObject("http://localhost:8080/webapi/personas", peticion, Persona.class);
	}
	
	public void borrar(Persona persona) {
		
		plantilla.delete("http://localhost:8080/webapi/personas/"+ persona.getNombre());
	}			
}
