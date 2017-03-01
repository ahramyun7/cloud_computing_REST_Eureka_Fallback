package edu.cmu.mis.iccfb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.cmu.mis.iccfb.model.Author;
import edu.cmu.mis.iccfb.service.AuthorService;

@Configuration
public class SeedData {

    @Autowired
    private AuthorService authorService;
    
    private static final Logger log = LoggerFactory.getLogger(SeedData.class);

    @Bean
    public SeedData getBean() {
        
        Author a1 = new Author("Douglas Adams", 1L);
        Author a2 = new Author("Gautama Buddha", 2L);
        Author a3 = new Author("Albert Einstein", 3L);       
        
        authorService.save(a1);
        authorService.save(a2);
        authorService.save(a3);

        log.info("Authors found with findAll():");
        log.info("-------------------------------");
        for (Author m : authorService.findAll()) {
            log.info(m.toString());
        }
        return new SeedData();
    }
}
