package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente crear(Cliente cliente) throws Exception {
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del cliente");
        }

        if (cliente.getTelefonoCliente() == null || cliente.getTelefonoCliente().isEmpty()) {
            throw new Exception("Debe ingresar el número de teléfono del cliente");
        }

        if (cliente.getEmailCliente() == null || cliente.getEmailCliente().isEmpty()) {
            throw new Exception("Debe ingresar el correo electrónico del cliente");
        }

        return clienteRepository.save(cliente);
    }
	
	public Cliente editar(Cliente cliente) throws Exception {
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del cliente");
        }

        if (cliente.getTelefonoCliente() == null || cliente.getTelefonoCliente().isEmpty()) {
            throw new Exception("Debe ingresar el número de teléfono del cliente");
        }

        if (cliente.getEmailCliente() == null || cliente.getEmailCliente().isEmpty()) {
            throw new Exception("Debe ingresar el correo electrónico del cliente");
        }

        Cliente clienteEdit = getCliente(cliente.getIdCliente());

        clienteEdit.setNombreCliente(cliente.getNombreCliente());
        clienteEdit.setTelefonoCliente(cliente.getTelefonoCliente());
        clienteEdit.setEmailCliente(cliente.getEmailCliente());

        return clienteRepository.save(clienteEdit);
    }
	
	public void eliminar(Integer idCliente) {
        clienteRepository.deleteById(idCliente);
    }
	
	public Cliente getCliente(Integer idCliente) throws Exception {
        Optional<Cliente> optCliente = clienteRepository.findById(idCliente);
        if (optCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente con ID: " + idCliente);
        }
        return optCliente.get();
    }
	
	public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
	
}
