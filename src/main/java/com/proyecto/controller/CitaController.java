package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.entity.Cita;
import com.proyecto.entity.Cliente;
import com.proyecto.entity.EstadoCita;
import com.proyecto.service.BarberoService;
import com.proyecto.service.CitaService;
import com.proyecto.service.ClienteService;
import com.proyecto.service.ServicioService;

@Controller
public class CitaController {
	
	@Autowired
	private CitaService citaService;
	
	@Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private BarberoService barberoService;

    @GetMapping("/cita-list")
    public String listarCitas(Model model) {
        List<Cita> citaList = citaService.getAll();
        model.addAttribute("citaList", citaList);
        return "cita-list";
    }

    @GetMapping("/new-cita")
    public String showNuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("clienteList", clienteService.getAll());
        model.addAttribute("servicioList", servicioService.getAll());
        model.addAttribute("barberoList", barberoService.getAll());
        model.addAttribute("estadoList", EstadoCita.values());
        model.addAttribute("type", "N");
        return "cita";
    }

    @GetMapping("/edit-cita/{id}")
    public String showEditCita(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("cita", citaService.getCita(id));
            model.addAttribute("clienteList", clienteService.getAll());
            model.addAttribute("servicioList", servicioService.getAll());
            model.addAttribute("barberoList", barberoService.getAll());
            model.addAttribute("estadoList", EstadoCita.values());
            model.addAttribute("type", "E");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cita";
    }
    
    @GetMapping("/view-cita/{id}")
    public String showViewCita(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("cita", citaService.getCita(id));
            model.addAttribute("clienteList", clienteService.getAll());
            model.addAttribute("servicioList", servicioService.getAll());
            model.addAttribute("barberoList", barberoService.getAll());
            model.addAttribute("estadoList", EstadoCita.values());
            model.addAttribute("type", "V");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cita";
    }

    @GetMapping("/remove-cita/{id}")
    public String removeCita(@PathVariable Integer id) {
        citaService.eliminar(id);
        return "redirect:/cita-list";
    }

    @PostMapping("/save-new-cita")
    public String saveNewCita(@ModelAttribute Cita cita) {
        try {
            citaService.crear(cita);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cita-list";
    }
    
    @PostMapping("/save-edit-cita")
    public String saveEditCita(@ModelAttribute Cita cita) {
        try {
            citaService.editar(cita);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cita-list";
    }
    
    
    @GetMapping("/consulta-historial-citas")
    public String showConsultaHistorialCitas(Model model) {
        model.addAttribute("cliente", new Cliente()); // para el formulario
        model.addAttribute("clienteList", clienteService.getAll());
        model.addAttribute("citas", null);
        return "consulta-historial-citas";
    }

    @PostMapping("/consulta-historial-citas")
    public String procesarConsultaHistorialCitas(@ModelAttribute Cliente cliente, Model model) {
        model.addAttribute("cliente", cliente);
        model.addAttribute("clienteList", clienteService.getAll());
        model.addAttribute("citas", citaService.listarPorCliente(cliente.getIdCliente()));
        return "consulta-historial-citas";
    }
    
}	
