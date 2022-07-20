package com.example.KeVeo.service;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.GestionDTO;
import com.example.KeVeo.data.entity.GestionUser;
import com.example.KeVeo.data.repository.GestionRepository;
import com.example.KeVeo.service.mapper.GestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GestionService extends AbstractBusinessService<GestionUser,Integer,GestionDTO, GestionRepository, GestionMapper>{

    private final UserRepository userRepository;

    @Autowired
    public GestionService(GestionRepository repository, GestionMapper serviceMapper,
                       UserRepository userRepository) {
        super(repository, serviceMapper);
        this.userRepository = userRepository;
    }

    @Override
    public GestionDTO save(GestionDTO dto) {
        final GestionUser entity = getServiceMapper().toEntity(dto);
        entity.setUser(this.userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException(String.format("The user %s does not exist", dto.getUserId()))));
        final GestionUser savedEntity = this.getRepository().save(entity);
        return getServiceMapper().toDto(savedEntity);
    }

    public Page<GestionDTO> findAll(Integer userId, Pageable pageable) {
        return getRepository().findByUserId(userId, pageable).map(getServiceMapper()::toDto);
    }
}
