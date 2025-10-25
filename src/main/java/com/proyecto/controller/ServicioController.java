package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.entity.Servicio;
import com.proyecto.service.ServicioService;

@Controller
public class ServicioController {
	
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping("/servicio-list")
    public String listaServicios(Model model) {
        List<Servicio> servicioList = servicioService.getAll();
        model.addAttribute("servicioList", servicioList);
        return "servicio-list";
    }

    @GetMapping("/new-servicio")
    public String showNuevoServicio(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("type", "N");
        return "servicio";
    }

    @GetMapping("/edit-servicio/{id}")
    public String showEditServicio(@PathVariable Integer id, Model model) {
        try {
            Servicio servicio = servicioService.getServicio(id);
            model.addAttribute("servicio", servicio);
            model.addAttribute("type", "E");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "servicio";
    }
    
    @GetMapping("/view-servicio/{id}")
    public String showViewServicio(@PathVariable Integer id, Model model) {
        try {
            Servicio servicio = servicioService.getServicio(id);
            model.addAttribute("servicio", servicio);
            model.addAttribute("type", "V");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "servicio";
    }

    @GetMapping("/remove-servicio/{id}")
    public String removeServicio(@PathVariable Integer id) {
        servicioService.eliminar(id);
        return "redirect:/servicio-list";
    }

    @PostMapping("/save-new-servicio")
    public String saveNewServicio(@ModelAttribute Servicio servicio) {
        try {
            servicioService.crear(servicio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/servicio-list";
    }
    
    @PostMapping("/save-edit-servicio")
    public String saveEditServicio(@ModelAttribute Servicio servicio) {
        try {
            servicioService.editar(servicio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/servicio-list";
    }
}
