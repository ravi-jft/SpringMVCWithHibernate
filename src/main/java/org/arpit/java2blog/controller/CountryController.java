
package org.arpit.java2blog.controller;


import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, headers = "Accept=application/json")
    public String getCountries(Model model) {

        List listOfCountries = countryService.getAllCountries();
        model.addAttribute("country", new Country());
        model.addAttribute("listOfCountries", listOfCountries);
        return "countryDetails";
    }

    @RequestMapping(value = "/getCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Country getCountryById(@PathVariable int id) {
        return countryService.getCountry(id);
    }

    @RequestMapping(value = "/addCountry", method = RequestMethod.POST, headers = "Accept=application/json")
    public String addCountry(@ModelAttribute("country") Country country) {
        if(country.getId()==0)
        {
            countryService.addCountry(country);
        }
        else
        {
            countryService.updateCountry(country);
        }

        return "redirect:/getAllCountries";
    }

    @RequestMapping(value = "/updateCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String updateCountry(@PathVariable("id") int id,Model model) {
        model.addAttribute("country", this.countryService.getCountry(id));
        model.addAttribute("listOfCountries", this.countryService.getAllCountries());
        return "countryDetails";
    }

    @RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String deleteCountry(@PathVariable("id") int id) {
        countryService.deleteCountry(id);
        return "redirect:/getAllCountries";

    }
}
