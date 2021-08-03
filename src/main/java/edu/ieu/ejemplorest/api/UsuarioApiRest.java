package edu.ieu.ejemplorest.api;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ieu.ejemplorest.entities.Usuario;
import edu.ieu.ejemplorest.servicios.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioApiRest {
	
	@Autowired
	private UsuarioService service;
	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> listaUsuarios = service.findAll();
		if(listaUsuarios.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching user with id " + id);
		Usuario user = service.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Usuario> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario> (user, HttpStatus.OK);
	}
	
	@PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createUser(@RequestBody Usuario user){
		System.out.println("Creating User " + user.getNombre());
		
		if (service.isUserExist(user)) {
			System.out.println("A User with name " + user.getNombre() + " already exis");
			return new ResponseEntity<Void> (HttpStatus.CONFLICT);
		}
		service.saveUser(user);
		return new ResponseEntity<Void> (HttpStatus.CREATED);
	}
	

}












