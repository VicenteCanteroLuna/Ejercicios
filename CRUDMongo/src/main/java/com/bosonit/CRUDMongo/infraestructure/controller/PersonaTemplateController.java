package com.bosonit.CRUDMongo.infraestructure.controller;


import com.bosonit.CRUDMongo.application.PersonaService;
import com.bosonit.CRUDMongo.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personaTemplate/api")
@CrossOrigin("*")
public class PersonaTemplateController {

    @Autowired
    PersonaService personaService;

    @GetMapping(value = "/all")
    public List<Persona> getAll(){
        return personaService.damePersonas();
    }

    @GetMapping(value= "/find/{id}")
    public Persona find (@PathVariable int id){
        return personaService.damePersonaByID(id);
    }

    @PostMapping(value= "/save")
    public ResponseEntity<Persona> save (@RequestBody Persona persona){
        Persona obj= personaService.añadir(persona);
        return new ResponseEntity<Persona>(obj, HttpStatus.OK);
    }

    @DeleteMapping(value= "/delete/{id}")
    public ResponseEntity<Persona> delete (@PathVariable int id){
        Persona personaEncontrada= personaService.damePersonaByID(id);
        if (personaEncontrada != null){
            personaService.borraByID(id);
        }else{
            return new ResponseEntity<Persona>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Persona>(personaEncontrada, HttpStatus.OK);
    }
}
