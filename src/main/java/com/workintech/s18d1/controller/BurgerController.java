package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDaoImpl;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*") // erişim
@RestController
@RequestMapping("/burgers")
public class BurgerController {
    private BurgerDaoImpl burgerDao;
    @Autowired
    public BurgerController(BurgerDaoImpl burgerDao) {
        this.burgerDao = burgerDao;
    }
    @GetMapping("/")
    public List<Burger> findAll(){
        return burgerDao.findAll();
    }
    @GetMapping("/{id}")
    public Burger find(@PathVariable long id){
        return burgerDao.findByID(id);
    }
    @GetMapping("/findByPrice")
    public List<Burger> findByPrice(@RequestBody int price){
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/findByBreadType")
    public List<Burger> findByBreadType(@RequestBody BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }
    @GetMapping("/findByContent")
    public List<Burger> findByContent(@RequestBody String content){
        return burgerDao.findByContent(content);
    }
    @PostMapping("/")
    public Burger save(@RequestBody Burger burger){
        return burgerDao.save(burger);
    }
    @PutMapping("/")
    public Burger update(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }
    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable long id) {
        return burgerDao.remove(id);
    }
}
