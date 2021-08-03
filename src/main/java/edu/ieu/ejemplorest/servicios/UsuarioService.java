package edu.ieu.ejemplorest.servicios;

import java.util.List;

import edu.ieu.ejemplorest.entities.Usuario;

public interface UsuarioService {
	
	//Consulta select
	Usuario findById(long id);
	Usuario findByNombre(String name);
	List<Usuario> findAll();
	boolean isUserExist(Usuario user);
	//Crear
	void saveUser(Usuario user);
	//Actualizar
	void updateUser(Usuario user);
	//Eliminar
	void deleteUserById(long id);

}
