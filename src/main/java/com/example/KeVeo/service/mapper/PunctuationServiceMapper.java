package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Punctuation;
import com.example.KeVeo.dto.PunctuationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PunctuationServiceMapper extends AbstractServiceMapper<Punctuation, PunctuationDTO> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Punctuation toEntity(PunctuationDTO punctuationDTO) {
        return modelMapper.map(punctuationDTO, Punctuation.class);
    }

    @Override
    public PunctuationDTO toDto(Punctuation punctuation) {
        return modelMapper.map(punctuation, PunctuationDTO.class);
    }
}
