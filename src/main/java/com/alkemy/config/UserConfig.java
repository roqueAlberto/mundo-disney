package com.alkemy.config;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserConfig  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	
}
