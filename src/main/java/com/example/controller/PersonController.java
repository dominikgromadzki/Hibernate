package com.example.controller;

import com.example.dao.PersonDao;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by dominikgromadzki on 30.03.2017.
 */

@Controller
public class PersonController {


    @GetMapping("/people/add")
    public String add() {
        return "people/add";
    }

    @Autowired
    PersonDao personDao;
    // personDao=newPersonDaoImpl();

    @PostMapping("people/display")
    public String display(@ModelAttribute Person people) {
//            (@ModelAttribute Person people, ModelMap modelMap) {
//        modelMap.addAttribute("people",people);
        //person=new Person(name,surname)
        personDao.save(people);
        return "people/display";
    }

    @GetMapping("/people/list")
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("people", personDao.findAll());
        return "people/list";
    }

    @GetMapping("/people/find")
    public String find() {
        return "people/find";
    }

    @PostMapping("/people/find")
    public String results(@ModelAttribute Person person, ModelMap modelMap) {
        //String surname=person.getSurname();
        //Person(0,null,"Kowalski")
        modelMap.addAttribute("people", personDao.findSurname(person.getSurname()));
        return "people/list";
    }

    //people/1 people/2 /people/3
    @GetMapping("/people/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
//                         ModelMap modelMap){
            // id=1, id=2, id=3
        personDao.remove(id);
//        modelMap.addAttribute("people",personDao.findAll());
        redirectAttributes.addFlashAttribute("message","Successful deleted");   //wyświetlenie informacji po usunięciu
//        return "people/list";
        return "redirect:/people/list";
    }

}
