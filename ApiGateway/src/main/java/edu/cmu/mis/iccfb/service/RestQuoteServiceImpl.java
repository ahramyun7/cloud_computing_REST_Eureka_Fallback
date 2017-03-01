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
import java.util.*;

@Service
public class RestQuoteServiceImpl implements QuoteService {
	@Value("${service.quote.uri}")
    private String quoteServiceUri;
    private String quoteServiceUriDiscovery = "http://QuoteService/api/quote";
    
    //@Autowired
    RestTemplate restTemplate = new RestTemplate();

	@Override
    @HystrixCommand(fallbackMethod = "randomQuoteFallBack")
    public Quote randomQuote() {
        String uri = quoteServiceUri + "/random";
        Quote q = restTemplate.getForObject(uri, Quote.class);
        return q;
    }
    
    @Override
    @HystrixCommand(fallbackMethod = "findByAuthorIdFallBack")
    public Iterable<Quote> findByAuthorId(Long authorId) {        
        String uri = quoteServiceUri + "/author/" + authorId;
        System.out.println("request uri : " + uri);
        ResponseEntity<Quote[]> r = restTemplate.getForEntity(uri, Quote[].class);
        Quote[] objects = r.getBody();
        Iterable<Quote> q = Arrays.asList(objects);
        for(Quote q_test : q) {
            System.out.println(q_test.getText());
        }
        return q;
    }

    @Override
    @HystrixCommand(fallbackMethod = "saveFallBack")
    public Long save(Quote q) {
        String uri = quoteServiceUri;
        ResponseEntity<Long> st = restTemplate.postForEntity(uri, q, Long.class);
        return st.getBody();
    }

    public Quote randomQuoteFallBack() {        
        Quote q = new Quote("Not Found", "Not Found");
        return q;
    }

    public Iterable<Quote> findByAuthorIdFallBack(Long authorId) {        
        ArrayList<Quote> l = new ArrayList<Quote>();
        Quote q = new Quote("Not Found", "Not Found");
        l.add(q);
        Iterable<Quote> res = l;
        return res;
    }

    public Long saveFallBack(Quote q) {
        System.out.println("Could not save..!!!");
        return 0L;
    }
}
