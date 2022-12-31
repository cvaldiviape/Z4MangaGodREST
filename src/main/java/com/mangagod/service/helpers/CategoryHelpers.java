package com.mangagod.service.helpers;

import com.mangagod.entity.CategoryEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public abstract class CategoryHelpers {

    @Autowired
    private CategoryRepository categoryRepository;

    // ----------------------------------------------------------- utils ----------------------------------------------------------- //
    public CategoryEntity getCategoryById(Integer id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
    }

    public void verifyNameUnique(String name) {
        Boolean existName = this.categoryRepository.existsByName(name);
        if (existName) {
            throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El name " + name + " ya existe.");
        }
    }

    public void verifyNameUnique(String name, String nameCurrent) {
        Boolean existName = this.categoryRepository.existsByName(name);
        Boolean diferentNameCurrent = (!name.equalsIgnoreCase(nameCurrent));
        if (existName && diferentNameCurrent) {
            throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El name " + name + " ya existe.");
        }
    }
}
