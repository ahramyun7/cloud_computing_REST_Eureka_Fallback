package edu.cmu.mis.iccfb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import edu.cmu.mis.iccfb.model.Quote;
import edu.cmu.mis.iccfb.service.QuoteService;
import java.util.*;

@RestController
public class QuoteController {
    
    @Autowired
    private QuoteService quoteService;    
    
    @RequestMapping("/api/quote/random")
    public Quote random() {
        return quoteService.randomQuote();
    }  

    @RequestMapping("/api/quote")
    public Iterable<Quote> listQuotes() {
        return quoteService.findAll();
    }

    @RequestMapping("/api/quote/author/{id}")
    public List<Quote> getListQuotesByAuthorId(@PathVariable("id") long id) {
    	List<Quote> list = new ArrayList<Quote>();
    	for(Quote q : quoteService.findAll()) {
    		if(q.getAuthorId() == id) {
    			list.add(q);
    		}
    	}    	
        return list;
    }

    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
    public void saveQuote(@RequestBody Quote quote) {
        System.out.println(quote);
        System.out.println("Saving quote");
        quoteService.save(quote);
    }
}
