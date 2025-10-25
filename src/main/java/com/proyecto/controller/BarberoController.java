package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.entity.Barbero;
import com.proyecto.service.BarberoService;

@Controller
public class BarberoController {
	
	@Autowired
	private BarberoService barberoService;
	
	@GetMapping("/barbero-list")
    public String listaBarberos(Model model) {
        List<Barbero> barberoList = barberoService.getAll();
        model.addAttribute("barberoList", barberoList);
        return "barbero-list"; 
    }
	
	@GetMapping("/new-barbero")
    public String showNuevoBarbero(Model model) {
        model.addAttribute("barbero", new Barbero());
        model.addAttribute("type", "N");
        return "barbero"; 
    }
	
	@GetMapping("/edit-barbero/{id}")
    public String showEditBarbero(@PathVariable Integer id, Model model) {
        try {
            Barbero barbero = barberoService.getBarbero(id);
            model.addAttribute("barbero", barbero);
            model.addAttribute("type", "E");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "barbero";
    }
	
	@GetMapping("/view-barbero/{id}")
    public String showViewBarbero(@PathVariable Integer id, Model model) {
        try {
            Barbero barbero = barberoService.getBarbero(id);
            model.addAttribute("barbero", barbero);
            model.addAttribute("type", "V");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "barbero";
    }
	
	@GetMapping("/remove-barbero/{id}")
    public String removeBarbero(@PathVariable Integer id) {
        barberoService.eliminar(id);
        
        return "redirect:/barbero-list";
    }
	
	@PostMapping("/save-new-barbero")
    public String saveNewBarbero(@ModelAttribute Barbero barbero) {
        try {
            barberoService.crear(barbero);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/barbero-list";
    }

	@PostMapping("/save-edit-barbero")
    public String saveEditBarbero(@ModelAttribute Barbero barbero) {
        try {
            barberoService.editar(barbero);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/barbero-list";
    }
}
