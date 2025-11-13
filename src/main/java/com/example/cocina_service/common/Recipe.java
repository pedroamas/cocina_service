package com.example.cocina_service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Recipe {
    String name;
    List<Portion> portionList;

}
