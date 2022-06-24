package main.controller;

import main.entity.EditField;
import main.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NonUniqueResultException;

@RestController
public class EditController {

    @Autowired
    EditService editService;

    @PostMapping("/edit/{name}")
    public String edit(@PathVariable String name, @RequestBody EditField editField){
        return editService.edit(name, editField);
    }
}
