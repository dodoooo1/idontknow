package com.idontknow.business.adapter.system;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.system.DictionariesService;
import com.idontknow.business.application.services.system.dto.CreateDictionariesRequest;
import com.idontknow.business.application.services.system.dto.DictionariesResponse;
import com.idontknow.business.application.services.system.dto.UpdateDictionariesRequest;
import com.idontknow.business.application.services.system.query.SearchDictionariesRequest;
import com.idontknow.business.application.services.system.query.SearchUsersRequest;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.DictionaryEntity;
import com.idontknow.business.infra.assembler.DictionariesMapper;
import com.idontknow.business.shared.ApiListPaginationSimple;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(DictionariesController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class DictionariesController extends BaseSystemController<
        DictionaryEntity,
        CreateDictionariesRequest,
        UpdateDictionariesRequest,
        DictionariesResponse> {
    public static final String BASE_URL = AppUrls.DICTIONARY;
    private final DictionariesService service;
    private final DictionariesMapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<DictionariesResponse> getDictionaries(@RequestBody SearchDictionariesRequest request) {
        return service.getDictionaries(request);
    }
}
