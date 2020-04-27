/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.webclient.controller;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import steps.dev.myfinance7.common.model.instrument.Instrument;
import steps.dev.myfinance7.common.model.instrument.InstrumentType;
import steps.dev.myfinance7.webclient.client.InstrumentClient;

/**
 *
 * @author stepin
 */

@Controller
@RequestMapping("/instrument")
public class InstrumentController {
    
    @Autowired
    private InstrumentClient client;

    @ModelAttribute("availableCurrencies")
    public Set<Currency> populateAvailableCurrencies(){
        return Currency.getAvailableCurrencies();
    }

    @ModelAttribute("avialableInstrumentTypes")
    public List<InstrumentType> populateAvailableInstrumentTypes(){
        return Arrays.asList(InstrumentType.values());
    }
    
    @GetMapping("/list")
    public String getList(Model model){
        model.addAttribute("instruments", client.getAll().getBody());
        return "instrument/list";
    }
    
    @GetMapping("/add")
    public String getEdit(Model model) {
        model.addAttribute("instrument", new Instrument());
        return "/instrument/edit";
    }
    
    @PostMapping("/edit")
    public String postEdit(Instrument instrument) {
        client.post(instrument);
        return "redirect:/instrument/list";
    }
    
    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        client.delete(id);
        return "redirect:/instrument/list";
    }

    
    
}
