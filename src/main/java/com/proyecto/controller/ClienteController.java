package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.entity.Cliente;
import com.proyecto.service.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/cliente-list")
    public String listaClientes(Model model) {
        List<Cliente> clienteList = clienteService.getAll();
        model.addAttribute("clienteList", clienteList);
        return "cliente-list";
    }

    @GetMapping("/new-cliente")
    public String showNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("type", "N");
        return "cliente";
    }

    @GetMapping("/edit-cliente/{id}")
    public String showEditCliente(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("cliente", clienteService.getCliente(id));
            model.addAttribute("type", "E");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cliente";
    }
    
    @GetMapping("/view-cliente/{id}")
    public String showViewCliente(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("cliente", clienteService.getCliente(id));
            model.addAttribute("type", "V");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cliente";
    }

    @GetMapping("/remove-cliente/{id}")
    public String removeCliente(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return "redirect:/cliente-list";
    }

    @PostMapping("/save-new-cliente")
    public String saveNewCliente(@ModelAttribute Cliente cliente) {
        try {
            clienteService.crear(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cliente-list";
    }
    
    @PostMapping("/save-edit-cliente")
    public String saveEditCliente(@ModelAttribute Cliente cliente) {
        try {
            clienteService.editar(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cliente-list";
    }
    
}
