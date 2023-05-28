package com.fiche.inaction.converter;

import com.fiche.inaction.data.entity.Ingredient;
import com.fiche.inaction.data.enums.IngredientType;
import com.fiche.inaction.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;
    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }

    private Map<String, Ingredient> ingredientMap = new HashMap<>();
    public IngredientByIdConverter() {
        ingredientMap.put("FLTO",
        new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));
        ingredientMap.put("COTO",
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP));
        ingredientMap.put("GRBF",
                new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
        ingredientMap.put("CARN",
                new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN));
        ingredientMap.put("TMTO",
                new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES));
        ingredientMap.put("LETC",
                new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES));
        ingredientMap.put("CHED",
                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
        ingredientMap.put("JACK",
                new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE));
        ingredientMap.put("SLSA",
                new Ingredient("SLSA", "Salsa", IngredientType.SAUCE));
        ingredientMap.put("SRCR",
                new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));
    }
//    @Override
//    public Ingredient convert(String id) {
//        return ingredientMap.get(id);
//    }

}
