package com.bjit.salon.service.serviceImpl;

import com.bjit.salon.service.dto.request.SalonCreateDto;
import com.bjit.salon.service.dto.request.SalonUpdateDto;
import com.bjit.salon.service.dto.response.SalonResponseDto;
import com.bjit.salon.service.entiry.Salon;
import com.bjit.salon.service.exception.SalonNotFoundException;
import com.bjit.salon.service.mapper.SalonMapper;
import com.bjit.salon.service.repository.SalonRepository;
import com.bjit.salon.service.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalonServiceImpl implements SalonService {

    private static final Logger log = LoggerFactory.getLogger(SalonServiceImpl.class);

    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;

    @Override
    @Caching(evict = {
            @CacheEvict(value="salons", allEntries=true)
    }
    )
    public void create(SalonCreateDto salonCreateDto) {
        log.info("Saving a new salon, details: {}",salonCreateDto.toString());
        salonRepository.save(salonMapper.toSalon(salonCreateDto));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value="salons", allEntries=true)
        }
    )
    public void update(SalonUpdateDto salonUpdateDto) {
        Optional<Salon> salon = salonRepository.findById(salonUpdateDto.getId());
        if (salon.isEmpty()){
            throw new SalonNotFoundException("salon not found for id: " + salonUpdateDto.getId());
        }

        Salon updateSalon = Salon
                .builder()
                .id(salon.get().getId())
                .address(salonUpdateDto.getAddress())
                .description(salonUpdateDto.getDescription())
                .name(salonUpdateDto.getName())
                .openingTime(salonUpdateDto.getOpeningTime())
                .closingTime(salonUpdateDto.getClosingTime())
                .reviews(salon.get().getReviews())
                .userId(salon.get().getUserId())
                .contractNumber(salonUpdateDto.getContractNumber())
                .build();

        log.info("Updating salon, details: {}", updateSalon.toString());
        salonRepository.save(updateSalon);
    }

    @Override
    @Cacheable(cacheNames = "salons", key = "#id")
    public SalonResponseDto getSalon(Long id) {
        Optional<Salon> salon = salonRepository.findById(id);
        if(salon.isEmpty()){
            throw new SalonNotFoundException("Salon not found for id: " + id);
        }
        log.info("Getting salon details:{}, for id:{}", salon.get(),id);
        return salonMapper.toSalonResponse(salon.get());
    }

    @Override
    @Cacheable(cacheNames = "salons")
    public List<SalonResponseDto> getAllSalon() {
        List<Salon> salons = salonRepository.findAll();
        log.info("Getting all salons, details: {}", salons);
        return salonMapper.toListOfSalonResponseDto(salons);
    }

    @Override
    @Cacheable(cacheNames = "salons", key = "#str")
    public List<SalonResponseDto> getSalonsByQuery(String str) {
        log.info("Searching salon with string: {}", str);
        return salonMapper.toListOfSalonResponseDto(salonRepository.findByNameContainingIgnoreCase(str));
    }
}
