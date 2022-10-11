package com.bjit.salon.service.service;

import com.bjit.salon.service.dto.request.CatalogCreateDto;
import com.bjit.salon.service.dto.response.CatalogResponseDto;

import java.util.List;

public interface CatalogService {

    void createNewCatalog(CatalogCreateDto catalogDto);
    CatalogResponseDto getCatalog(long id);
    List<CatalogResponseDto> getAllCatalog();


}
