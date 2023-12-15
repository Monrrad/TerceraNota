package com.electrovid.co.api.model.dao;


import org.springframework.data.repository.CrudRepository;

import com.electrovid.co.api.model.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}