package com.example.demo.Controller;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.Users;
import com.example.demo.repository.UserRepo;

@org.springframework.stereotype.Controller
public class Controller {
	
	
	
@Autowired	
 UserRepo userrepository;
 



@GetMapping("/display")
public String display (Model model) throws SQLException , ClassNotFoundException {
	
	List<Users> listuser=userrepository.findAll();
	model.addAttribute("list",  listuser);
	return "index";
	
}

@PostMapping("/add")	

public String add(@ModelAttribute Users users) throws SQLException{
	userrepository.save(users);
	return "redirect:/display";
	
}
@GetMapping("/delete/{id}")
public String delete(@PathVariable Long id) throws Exception {
	userrepository.deleteById(id);
	return"redirect:/display";
}



}