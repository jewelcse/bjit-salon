package com.bjit.salon.service.controller;


import com.bjit.salon.service.dto.request.CatalogCreateDto;
import com.bjit.salon.service.dto.response.CatalogResponseDto;
import com.bjit.salon.service.service.CatalogService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bjit.salon.service.util.ConstraintsUtil.APPLICATION_BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_URL)
public class CatalogController {
    private static final Logger log = LoggerFactory.getLogger(CatalogController.class);
    private final CatalogService catalogService;
    @PostMapping("/salons/catalogs")
    public ResponseEntity<String> create(@RequestBody CatalogCreateDto catalogCreateDto) {
        log.info("Creating a new catalog with name: {}", catalogCreateDto.getName());
        catalogService.createNewCatalog(catalogCreateDto);
        return new ResponseEntity<>("catalog created success", HttpStatus.CREATED);
    }

    @GetMapping("/salons/catalogs")
    public ResponseEntity<List<CatalogResponseDto>> getAll(){
        List<CatalogResponseDto> allCatalog = catalogService.getAllCatalog();
        log.info("Getting all catalog with size: {}", allCatalog.size());
        return ResponseEntity.ok(allCatalog);
    }

    @GetMapping("/salons/catalogs/{id}")
    public ResponseEntity<CatalogResponseDto> get(@PathVariable("id") long id){
        log.info("Getting catalog details with id: {}", id);
        return ResponseEntity.ok(catalogService.getCatalog(id));
    }
}
