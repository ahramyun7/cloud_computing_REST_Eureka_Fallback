package edu.cmu.mis.iccfb.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import edu.cmu.mis.iccfb.model.Author;
import edu.cmu.mis.iccfb.model.Quote;

@Service
public class RestAuthorServiceImpl implements AuthorService {
	@Value("${service.author.uri}")
    private String authorServiceUri;
    private String authorServiceUriDiscovery = "http://AuthorService/api/author";

    //@Autowired
    RestTemplate restTemplate = new RestTemplate();

	@Override
    @HystrixCommand(fallbackMethod = "findByNameFallBack")
    public Author findByName(String name) {
    	String uri = authorServiceUri + "/byname?name=" + name;
    	Author a = restTemplate.getForObject(uri, Author.class);
    	return a;
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBack")
    public Long save(Author a) {
    	String uri = authorServiceUri;
    	ResponseEntity<Long> st = restTemplate.postForEntity(uri, a, Long.class);
    	return st.getBody();
    }

    @Override
    @HystrixCommand(fallbackMethod = "findOneFallBack")
    public Author findOne(Long id) {
    	String uri = authorServiceUri + "/" + id;
    	Author a = restTemplate.getForObject(uri, Author.class);
    	return a;
    }
    // just Return Author with named : Author Not Found
    public Author findByNameFallBack(String name) { 
        Author a = new Author("Author Not Found", 0L);
        return a;
    }

    // Author ID is starting from 1L
    public Long saveFallBack(Author a) {
        System.out.println("Could not save..!!!");   
        return 0L;
    }

     // just Return Author with named : Author Not Found
    public Author findOneFallBack(Long id) {
        Author a = new Author("Author Not Found", 0L);
        return a;
    }   
}
