package edu.cmu.mis.iccfb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import edu.cmu.mis.iccfb.model.Author;
import edu.cmu.mis.iccfb.service.AuthorService;

@RestController
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    @RequestMapping("/api/author")
    public Iterable<Author> listAuthors() {
        return authorService.findAll();
    }  

    @RequestMapping("/api/author/{id}")
	public Author getAuthor(@PathVariable("id") long id) {
		for(Author a : this.authorService.findAll()) {
    		if(a.getId() == id) {
    			return a;
    		}
    	}
    	return new Author("Not Found", 0L);
	}

 	@RequestMapping("/api/author/byname")
	public Author getAuthorByName(@RequestParam("name") String authorName) {
		//System.out.println(authorName);
		authorName = authorName.replaceAll("\\r\\n", "");
		for(Author a : this.authorService.findAll()) {
			if(a.getName().equals(authorName)) {    			
    			return a;
    		}
    	}
    	return new Author("Not Found", 0L);
	}

	@RequestMapping(value = "/api/author", method = RequestMethod.POST)
    public Long saveAuthor(@RequestBody Author author) {
        System.out.println(author);
        if(author == null) return 0L;        
        System.out.println("Saving author");
        authorService.save(author);
        return author.getId();
    }
    
}
