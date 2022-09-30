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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;
    private final SalonMapper salonMapper;

    @Override
    public void create(SalonCreateDto salonCreateDto) {
       salonRepository.save(salonMapper.toSalon(salonCreateDto));
    }

    @Override
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


        salonRepository.save(updateSalon);
    }

    @Override
    public SalonResponseDto getSalon(Long id) {
        Optional<Salon> salon = salonRepository.findById(id);
        if(salon.isEmpty()){
            throw new SalonNotFoundException("Salon not found for id: " + id);
        }
        return salonMapper.toSalonResponse(salon.get());
    }

    @Override
    public List<SalonResponseDto> getAllSalon() {
        return salonMapper.toListOfSalonResponseDto(salonRepository.findAll());
    }
}
