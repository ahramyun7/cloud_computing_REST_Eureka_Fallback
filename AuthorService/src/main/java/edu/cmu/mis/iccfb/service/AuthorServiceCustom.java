package edu.cmu.mis.iccfb.service;

import edu.cmu.mis.iccfb.model.Author;

public interface AuthorServiceCustom {
    public Long findOne(String authorName);
}
