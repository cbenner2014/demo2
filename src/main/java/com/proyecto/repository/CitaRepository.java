package com.proyecto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Cita;
import com.proyecto.entity.EstadoCita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
	
	List<Cita> findByClienteIdClienteOrderByFechaDesc(Integer idCliente);

    List<Cita> findByBarberoIdBarberoAndFechaOrderByHoraAsc(Integer idBarbero, LocalDate  fecha);
    
    List<Cita> findByEstado(EstadoCita estado);
    
    List<Cita> findByClienteIdCliente(Integer idCliente);
}
