package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Servicio;
import com.proyecto.repository.ServicioRepository;

@Service
public class ServicioService {
	
	@Autowired
	private ServicioRepository servicioRepository;
	
	public Servicio crear(Servicio servicio) throws Exception {
        if (servicio.getNombreServicio() == null || servicio.getNombreServicio().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del servicio");
        }

        if (servicio.getPrecioServicio() == null || servicio.getPrecioServicio().doubleValue() <= 0) {
            throw new Exception("Debe ingresar un precio válido");
        }

        if (servicio.getDuracionServicio() == null || servicio.getDuracionServicio() <= 0) {
            throw new Exception("Debe ingresar una duración válida en minutos");
        }

        return servicioRepository.save(servicio);
    }
	
	public Servicio editar(Servicio servicio) throws Exception {
        if (servicio.getNombreServicio() == null || servicio.getNombreServicio().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del servicio");
        }

        if (servicio.getPrecioServicio() == null || servicio.getPrecioServicio().doubleValue() <= 0) {
            throw new Exception("Debe ingresar un precio válido");
        }

        if (servicio.getDuracionServicio() == null || servicio.getDuracionServicio() <= 0) {
            throw new Exception("Debe ingresar una duración válida en minutos");
        }

        Servicio servicioEdit = getServicio(servicio.getIdServicio());

        servicioEdit.setNombreServicio(servicio.getNombreServicio());
        servicioEdit.setPrecioServicio(servicio.getPrecioServicio());
        servicioEdit.setDuracionServicio(servicio.getDuracionServicio());

        return servicioRepository.save(servicioEdit);
    }
	
	public void eliminar(Integer idServicio) {
        servicioRepository.deleteById(idServicio);
    }
	
	public Servicio getServicio(Integer idServicio) throws Exception {
        Optional<Servicio> optServicio = servicioRepository.findById(idServicio);
        if (optServicio.isEmpty()) {
            throw new Exception("No se encontró el servicio con ID: " + idServicio);
        }
        return optServicio.get();
    }
	
	public List<Servicio> getAll() {
        return servicioRepository.findAll();
    }
	
	public List<Servicio> buscarPorNombre(String nombre) {
        return servicioRepository.findByNombreServicioContainingIgnoreCase(nombre);
    }
	
}
