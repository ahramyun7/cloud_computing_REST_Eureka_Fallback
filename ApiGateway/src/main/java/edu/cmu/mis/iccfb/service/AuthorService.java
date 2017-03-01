package edu.cmu.mis.iccfb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.cmu.mis.iccfb.model.Author;
import edu.cmu.mis.iccfb.model.Quote;

@Service
public interface AuthorService {

    Author findByName(String name);
    Long save(Author a);
    Author findOne(Long id);
}
