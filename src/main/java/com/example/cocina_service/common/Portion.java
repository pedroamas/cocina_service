package com.example.cocina_service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Portion {
    int count;
    Ingredient ingredient;
}
