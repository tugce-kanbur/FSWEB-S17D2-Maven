package com.workintech.s17d2.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.DeveloperTax;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    final Taxable taxable;
    private Map<Integer,Developer> developers;

    public Map<Integer, Developer> getDevelopers() {
        return developers;
    }

    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init(){
        developers = new HashMap<>();
    }
    @GetMapping
    public List<Developer> getAll(){
        return new ArrayList<>(developers.values());
    }
    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id){
        return developers.get(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Developer addDeveloper(@RequestBody Developer developer){
        double salary = developer.getSalary();
        Experience experience = developer.getExperience();
        Developer newDeveloper;
        switch (experience){
            case JUNIOR :
                salary -= salary * taxable.getSimpleTaxRate();
                newDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(), developer.getSalary());
                break;
            case MID:
                salary -= salary * taxable.getMiddleTaxRate();
                newDeveloper = new MidDeveloper(developer.getId(), developer.getName(), developer.getSalary());
                break;
            case SENIOR:
                salary -= salary * taxable.getUpperTaxRate();
                newDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(), developer.getSalary());
                break;
            default : throw new IllegalArgumentException("Geçersiz deneyim seviyesi!");
        }
        developers.put(newDeveloper.getId(),newDeveloper);
        return newDeveloper;
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id, @RequestBody Developer updatedDev) {
        developers.put(id, updatedDev);
        return updatedDev;
    }

    @DeleteMapping("/{id}")
    public Developer deleteDeveloper(@PathVariable int id) {
        return developers.remove(id);
    }
}
