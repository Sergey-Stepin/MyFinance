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
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
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
    private InstrumentClient instrumentClient;

    @ModelAttribute("availableCurrencies")
    public Set<Currency> populateAvailableCurrencies(){
        return Currency.getAvailableCurrencies();
    }

    @ModelAttribute("avialableInstrumentTypes")
    public List<InstrumentType> populateAvailableInstrumentTypes(){
        return Arrays.asList(InstrumentType.values());
    }

    @ModelAttribute("avialableExchangeReceiverNames")
    public List<ExchangeReceiverName> populateExchangeReceiverNames(){
        return Arrays.asList(ExchangeReceiverName.values());
    }
    
    @GetMapping("/list")
    public String getList(Model model){
        model.addAttribute("instruments", instrumentClient.getAll().getBody());
        return "instrument/list";
    }
    
    @GetMapping("/add")
    public String getAdd(Model model) {
        model.addAttribute("instrument", new Instrument());
        return "/instrument/edit";
    }

    @GetMapping("/edit/{indtrumentId}")
    public String getEdit(
            @PathVariable("indtrumentId") long indtrumentId,
            Model model) {
        
        Instrument instrument = instrumentClient.getById(indtrumentId).getBody();
        System.out.println("### Instrument=" + instrument);
        model.addAttribute("instrument", instrument);
        return "/instrument/edit";
    }
    
    @PostMapping("/edit")
    public String postEdit(Instrument instrument) {
        instrumentClient.post(instrument);
        return "redirect:/instrument/list";
    }
    
    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        instrumentClient.delete(id);
        return "redirect:/instrument/list";
    }
    
}
