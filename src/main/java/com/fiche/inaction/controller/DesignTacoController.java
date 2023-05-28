package com.fiche.inaction.controller;

import com.fiche.inaction.data.entity.Ingredient;
import com.fiche.inaction.data.entity.Taco;
import com.fiche.inaction.data.entity.TacoOrder;
import com.fiche.inaction.data.enums.IngredientType;
import com.fiche.inaction.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

//    @ModelAttribute
//    public void addIngredientsToModel(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
//                new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
//                new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
//                new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
//                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
//                new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE)
//        );
//        IngredientType[] types = IngredientType.values();
//        for (IngredientType type : types) {
//            model.addAttribute(type.toString().toLowerCase(),
//                    filterByType(ingredients, type));
//        }
//    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, IngredientType type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}

