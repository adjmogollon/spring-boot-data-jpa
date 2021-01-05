package com.bolsadeideas.springboot.app.models.service;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Usuario;

public interface IUserService extends CrudRepository<Usuario, Long> {

}
