package com.fiche.inaction.data.entity;

import com.fiche.inaction.data.enums.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String id;
    private String name;
    private IngredientType type;

}
