package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.bootstrap.DataGenerator;
import com.cydeo.pizzacloud.enums.Cheese;
import com.cydeo.pizzacloud.enums.Sauce;
import com.cydeo.pizzacloud.enums.Topping;
import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/design")
public class DesignPizzaController {

    private PizzaRepository pizzaRepository;

    public DesignPizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
// @PostMapping("design")
   // public String showDesignForm(Model model) {

     @GetMapping
        public String showDesignForm(Model model) {
       // Pizza pizza  = new Pizza();

        model.addAttribute("pizza",new Pizza());
        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);

        return "/design";

    }

   //public String showDesignForm(@ModelAttribute("cheese") Cheese cheese, @ModelAttribute("sauce") Sauce sauce, @ModelAttribute("topping") Topping topping, Model model) {




    @PostMapping("/createPizza")
    public String processPizza(Pizza pizza) {

        pizza.setId(UUID.randomUUID());
        pizzaRepository.createPizza(pizza);

        return "redirect:/orders/current?pizzaId=" + pizza.getId();

    }

}