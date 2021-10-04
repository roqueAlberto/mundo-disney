package com.alkemy.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.config.JwtUtil;
import com.alkemy.config.UserConfig;
import com.alkemy.service.impl.UsuarioServiceImpl;


@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(value="/login")
	public ResponseEntity<?> loguearse(@RequestBody UserConfig userConfig) throws Exception{
		
		usuarioService.autenticacion(userConfig.getUsername(), userConfig.getPassword());
		
		 UserDetails userDetails = usuarioService.loadUserByUsername(userConfig.getUsername());
		
		 String token = jwtUtil.generateToken(userDetails);
		
		 return ResponseEntity.ok(token);
	}
	
	
	@PostMapping(value ="/register")
	public ResponseEntity<?> registrar(@RequestBody UserConfig user) throws Exception{
		return ResponseEntity.ok(usuarioService.save(user));
	}
	
}
