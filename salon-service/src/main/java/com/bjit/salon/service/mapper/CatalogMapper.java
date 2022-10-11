package com.bjit.salon.service.mapper;

import com.bjit.salon.service.dto.request.CatalogCreateDto;
import com.bjit.salon.service.dto.response.CatalogResponseDto;
import com.bjit.salon.service.entiry.Catalog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogMapper {

    Catalog toCatalog(CatalogCreateDto catalogCreateDto);
    CatalogResponseDto toCatalogResponse(Catalog catalog);
    List<CatalogResponseDto> toCatalogsResponse(List<Catalog> catalogs);
}
