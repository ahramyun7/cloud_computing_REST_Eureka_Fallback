package edu.cmu.mis.iccfb.service;

import edu.cmu.mis.iccfb.model.Quote;

public interface QuoteService {
	public Quote randomQuote();
	public Iterable<Quote> findByAuthorId(Long authorId);
	public Long save(Quote quote);	
}
