package edu.cmu.mis.iccfb.model;

public class Author {

    private Long id;
    private String name;    

    protected Author() {}

    public Author(String name, Long id) {
        this.name = name;
        this.id = id;
    }
/*
    private Iterable<Quote> quotes;

    public Iterable<Quote> getQuotes() {
        return quotes;
    }
    
    public void setQuotes(Iterable<Quote> quotes) {
        this.quotes = quotes;
    }
*/
    @Override
    public String toString() {
        return String.format("Author[id=%d, name='%s']", id, this.name);
    }
    
    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
