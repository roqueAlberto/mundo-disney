package com.alkemy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.dto.PersonajeDTO;
import com.alkemy.models.Personaje;
import com.alkemy.service.IPersonajeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

	@Autowired
	private IPersonajeService personajeService;
	
	
	@GetMapping("/")
	@ApiOperation(value = "Listar personajes con imagen y nombre", notes = "Servicio para mostrar los personajes de Disney")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Error en la solicitud"),
			                @ApiResponse(code = 200, message = "Personajes traidos correctamente") })
	public  ResponseEntity<List<PersonajeDTO>> listPersonajes() {

			List<Personaje> lista = personajeService.listarPersonajes();
			List<PersonajeDTO> listaDto = new ArrayList<>();

			for (Personaje p : lista)
				listaDto.add(new PersonajeDTO(p));
						
		return ResponseEntity.ok(listaDto);
	}
	

	@GetMapping
	@ApiOperation(value = "Listar personajes segun el parametro elegido", notes = "Servicio para mostrar los personajes de Disney")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Error en la solicitud"),
			               @ApiResponse(code = 200, message = "Personajes traidos correctamente") })
	public List<Personaje> listar(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "age", required = false) String age, 
			@RequestParam(value = "movies", required = false) String movies) {

		List<Personaje> listaPersonajes = new ArrayList<>();
				
		if (name != null) 
			return listaPersonajes = personajeService.findPersonajeByName(name);
		 else if(age != null)
			return listaPersonajes =  personajeService.findPersonajeByAge(Integer.parseInt(age));		
		else if(movies != null) 		
			return listaPersonajes = personajeService.findPersonajeByMovie(Integer.parseInt(movies));		
		else 
			return listaPersonajes;
					
	}
	
	
	
	@PostMapping("/agregar")
	@ApiOperation(value = "Agregar personaje", notes = "Servicio para crear un nuevo personaje")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 201, message = "Personaje creado correctamente"),
			                @ApiResponse(code = 401, message = "No esta autorizado para realizar la peticion"),
			                @ApiResponse(code = 403, message = "El acceso está prohibido"),
			                @ApiResponse(code = 404, message = "No se puede encontrar la pagina solicitada")})
	public ResponseEntity<Personaje> add(@RequestBody() Personaje personaje) {
	    Personaje p = personajeService.add(personaje);
	    return ResponseEntity.ok(p);
	}

	
	@PutMapping("/actualizar")
	@ApiOperation(value = "Actualizar personaje", notes = "Servicio para actualizar el personaje")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 201, message = "Peticion completa"),
			                @ApiResponse(code = 401, message = "No esta autorizado para realizar la peticion"),
			                @ApiResponse(code = 403, message = "El acceso está prohibido"),
			                @ApiResponse(code = 404, message = "No se puede encontrar la pagina solicitada") })
	public ResponseEntity<Personaje> update(@RequestBody Personaje personaje) {
		Personaje p = personajeService.update(personaje);
		return ResponseEntity.ok(p);
	}

	
	
	@ApiOperation(value = "Eliminar personaje", notes = "Servicio para eliminar personaje")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 401, message = "Se requiere de un usuario y contraseña"),
			                @ApiResponse(code = 403, message = "No esta autorizado para realizar la peticion") })
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Personaje> delete(@PathVariable("id") int id) {

		if (personajeService.delete(id)) 
			return new ResponseEntity<>(HttpStatus.OK);
		  else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}

	
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Buscar personaje a traves de su id", notes = "Servicio para buscar personaje por id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La peticion fue recibida correctamente"),
			                @ApiResponse(code = 401, message = "No esta autorizado para realizar la peticion"),
			                @ApiResponse(code = 403, message = "El acceso está prohibido"),
			                @ApiResponse(code = 404, message = "No se puede encontrar la pagina solicitada") })	
	public ResponseEntity<Personaje> getPersonaje(@PathVariable(name = "id") int id) {
		
		Optional<Personaje> personaje = personajeService.findPersonajeById(id);
		
		return ResponseEntity.ok(personaje.get());
	}

	
	
	
}
