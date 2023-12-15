package com.electrovid.co.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electrovid.co.api.model.dto.ClienteDto;
import com.electrovid.co.api.model.entity.Cliente;
import com.electrovid.co.api.model.payload.MensajeResponse;
import com.electrovid.co.api.service.ICliente;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	
	@Autowired
	private ICliente clienteService;
	
	 @GetMapping("clientes")
	    public ResponseEntity<?> showAll() {
	        List<Cliente> getList = clienteService.listAlll();
	        if (getList == null) {
	            return new ResponseEntity<>(
	                    MensajeResponse.builder()
	                            .mnesaje("No hay registros")
	                            .object(null)
	                            .build()
	                    , HttpStatus.OK);
	        }

	        return new ResponseEntity<>(
	                MensajeResponse.builder()
	                        .mnesaje("")
	                        .object(getList)
	                        .build()
	                , HttpStatus.OK);
	    }


	    @PostMapping("cliente")
	    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
	        Cliente clienteSave = null;
	        try {
	            clienteSave = clienteService.save(clienteDto);
	            return new ResponseEntity<>(MensajeResponse.builder()
	                    .mnesaje("Guardado correctamente")
	                    .object(ClienteDto.builder()
	                            .idCliente(clienteSave.getIdCliente())
	                            .nombre(clienteSave.getNombre() )
	                            .apellido(clienteSave.getApellido())
	                            .correo(clienteSave.getCorreo())
	                            .fechaRegistro(clienteSave.getFechaRegistro())
	                            .build())
	                    .build()
	                    , HttpStatus.CREATED);
	        } catch (DataAccessException exDt) {
	            return new ResponseEntity<>(
	                    MensajeResponse.builder()
	                            .mnesaje(exDt.getMessage())
	                            .object(null)
	                            .build()
	                    , HttpStatus.METHOD_NOT_ALLOWED);
	        }
	    }

	    @PutMapping("cliente/{id}")
	    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
	        Cliente clienteUpdate = null;
	        try {
	            if (clienteService.existsById(id)) {
	                clienteDto.setIdCliente(id);
	                clienteUpdate = clienteService.save(clienteDto);
	                return new ResponseEntity<>(MensajeResponse.builder()
	                        .mnesaje("Guardado correctamente")
	                        .object(ClienteDto.builder()
	                                .idCliente(clienteUpdate.getIdCliente())
	                                .nombre(clienteUpdate.getNombre())
	                                .apellido(clienteUpdate.getApellido())
	                                .correo(clienteUpdate.getCorreo())
	                                .fechaRegistro(clienteUpdate.getFechaRegistro())
	                                .build())
	                        .build()
	                        , HttpStatus.CREATED);
	            } else {
	                return new ResponseEntity<>(
	                        MensajeResponse.builder()
	                                .mnesaje("El registro que intenta actualizar no se encuentra en la base de datos.")
	                                .object(null)
	                                .build()
	                        , HttpStatus.NOT_FOUND);
	            }
	        } catch (DataAccessException exDt) {
	            return new ResponseEntity<>(
	                    MensajeResponse.builder()
	                            .mnesaje(exDt.getMessage())
	                            .object(null)
	                            .build()
	                    , HttpStatus.METHOD_NOT_ALLOWED);
	        }
	    }

	    @DeleteMapping("cliente/{id}")
	    public ResponseEntity<?> delete(@PathVariable Integer id) {
	        try {
	            Cliente clienteDelete = clienteService.findById(id);
	            clienteService.delete(clienteDelete);
	            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
	        } catch (DataAccessException exDt) {
	            return new ResponseEntity<>(
	                    MensajeResponse.builder()
	                            .mnesaje(exDt.getMessage())
	                            .object(null)
	                            .build()
	                    , HttpStatus.METHOD_NOT_ALLOWED);
	        }
	    }

	    @GetMapping("cliente/{id}")
	    public ResponseEntity<?> showById(@PathVariable Integer id) {
	        Cliente cliente = clienteService.findById(id);

	        if (cliente == null) {
	            return new ResponseEntity<>(
	                    MensajeResponse.builder()
	                            .mnesaje("El registro que intenta buscar, no existe!!")
	                            .object(null)
	                            .build()
	                    , HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(
	                MensajeResponse.builder()
	                        .mnesaje("")
	                        .object(ClienteDto.builder()
	                                .idCliente(cliente.getIdCliente())
	                                .nombre(cliente.getNombre())
	                                .apellido(cliente.getApellido())
	                                .correo(cliente.getCorreo())
	                                .fechaRegistro(cliente.getFechaRegistro())
	                                .build())
	                        .build()
	                , HttpStatus.OK);
	    }

	}