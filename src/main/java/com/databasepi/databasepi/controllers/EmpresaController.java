package com.databasepi.databasepi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.databasepi.databasepi.models.Empresa;
import com.databasepi.databasepi.repository.EmpresaRepository;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaRepository er;
	
	// GET que chama o form para cadastrar produtos
	@RequestMapping("/cadastrarProduto")
	public String form() {
		return "empresa/form-empresa";
	}

	// POST que cadastra produtos
	@RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
	public String form(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes) {

		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarProduto";
		}

		er.save(empresa);
		attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
		return "redirect:/produtos";
	}

	// GET que lista produtos
	@RequestMapping("/produtos")
	public ModelAndView listaEmpresas() {
		ModelAndView mv = new ModelAndView("empresa/lista-empresa");
		Iterable<Empresa> empresas = er.findAll();
		mv.addObject("empresas", empresas);
		return mv;
	}
	
	//GET que deleta produto
	@RequestMapping("/deletarEmpresa")
	public String deletarEmpresa(long id) {
		Empresa empresa = er.findById(id);
		er.delete(empresa);
		return "redirect:/produtos";
		
	}
	
	// Métodos que atualizam produto
	// GET que chama o FORM de edição do produto
	@RequestMapping("/editar-empresa")
	public ModelAndView editarEmpresa(long id) {
		Empresa empresa = er.findById(id);
		ModelAndView mv = new ModelAndView("empresa/update-empresa");
		mv.addObject("empresa", empresa);
		return mv;
	}
	
	// POST que atualiza o produto
	@RequestMapping(value = "/editar-empresa", method = RequestMethod.POST)
	public String updateEmpresa(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes){
		
		er.save(empresa);
		attributes.addFlashAttribute("success", "Produto alterado com sucesso!");
		return "redirect:/produtos";
		
	}
	
}