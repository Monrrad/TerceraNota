package com.electrovid.co.api.service;

import java.util.List;

import com.electrovid.co.api.model.dto.ClienteDto;
import com.electrovid.co.api.model.entity.Cliente;

public interface ICliente {
	
	List<Cliente> listAlll();

	Cliente save(ClienteDto cliente);
	
	Cliente findById(Integer id);
	
	void delete (Cliente cliente);
	
	boolean existsById(Integer id);

	
}
