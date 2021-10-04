package com.alkemy.service;

import com.alkemy.config.UserConfig;
import com.alkemy.models.Usuario;

public interface IUsuarioService {

	public Usuario save(UserConfig user);
	public void autenticacion(String username, String password) throws Exception;
}
