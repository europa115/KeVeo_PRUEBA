package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Punctuation;
import com.example.KeVeo.data.repository.PunctuationRepository;
import com.example.KeVeo.dto.PunctuationDTO;
import com.example.KeVeo.service.mapper.PunctuationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PunctuationService extends AbstractBusinessService<Punctuation,Integer, PunctuationDTO, PunctuationRepository, PunctuationMapper>{

    @Autowired
    protected PunctuationService(PunctuationRepository repository, PunctuationMapper serviceMapper) {
        super(repository, serviceMapper);
    }
}
