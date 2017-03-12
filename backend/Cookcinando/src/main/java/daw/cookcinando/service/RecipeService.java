package daw.cookcinando.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import daw.cookcinando.model.Recipe;

public interface RecipeService {
	Page<Recipe> listAllByPage(Pageable pageable);
}
