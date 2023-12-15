package com.electrovid.co.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electrovid.co.api.model.dao.ClienteDao;
import com.electrovid.co.api.model.dto.ClienteDto;
import com.electrovid.co.api.model.entity.Cliente;
import com.electrovid.co.api.service.ICliente;


@Service
public class ClienteImpl implements ICliente{
	
	@Autowired
	private ClienteDao clienteDao;
	
	 @Override
	    public List<Cliente> listAlll() {
	        return (List) clienteDao.findAll();
	    }


	@Transactional
	@Override
	public Cliente save(ClienteDto clienteDto) {
		// TODO Auto-generated method stub
		Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())  
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .build();
		return clienteDao.save(cliente);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente findById(Integer id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.delete(cliente);
		
		
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return clienteDao.existsById(id);
	}

	

}
