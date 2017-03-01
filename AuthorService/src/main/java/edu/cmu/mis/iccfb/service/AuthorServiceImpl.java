package edu.cmu.mis.iccfb.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import edu.cmu.mis.iccfb.model.Author;

public class AuthorServiceImpl implements AuthorServiceCustom {

    @Autowired
    private AuthorService authorService;
    
    @Override
     public Long findOne(String authorName) {     	
		for(Author a : this.authorService.findAll()) {
			if(a.getName().equals(authorName)) {    			
    			return a.getId();
    		}
    	}
    	return 0L;

     }
}
