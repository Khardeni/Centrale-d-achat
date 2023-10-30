package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.TypeArticle;
import tn.esprit.spring.interfaces.ITypeArticleService;
import tn.esprit.spring.repositories.TypeArticleRepository;

@Service
@Slf4j
public class TypeArticleImpl implements ITypeArticleService {

    @Autowired
    TypeArticleRepository typeArticleRepository;
    @Override

    public void AddTypeArticle (TypeArticle typeArticle) {

        typeArticleRepository.save(typeArticle);
    }
    @Override
    public void DeleteTypeArticle(TypeArticle typeArticle){

        typeArticleRepository.delete(typeArticle);
    }
}
