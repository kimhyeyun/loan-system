package dev.be.loansystem.service;

import dev.be.loansystem.domain.Application;
import dev.be.loansystem.domain.Entry;
import dev.be.loansystem.dto.BalanceDTO.*;
import dev.be.loansystem.dto.EntryDTO.Request;
import dev.be.loansystem.dto.EntryDTO.Response;
import dev.be.loansystem.exception.BaseException;
import dev.be.loansystem.exception.ResultType;
import dev.be.loansystem.repository.ApplicationRepository;
import dev.be.loansystem.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService{

    private final EntryRepository entryRepository;
    private final ApplicationRepository applicationRepository;
    private final BalanceService balanceService;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Long applicationId, Request request) {
        if (!isContractedApplication(applicationId)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Entry entry = modelMapper.map(request, Entry.class);
        entry.setApplicationId(applicationId);

        entryRepository.save(entry);

        balanceService.create(applicationId,
                CreateRequest.builder()
                        .entryAmount(request.getEntryAmount())
                        .build()
        );

        return modelMapper.map(entry, Response.class);
    }

    private boolean isContractedApplication(Long applicationId) {
        Optional<Application> existed = applicationRepository.findById(applicationId);
        if(existed.isEmpty()) return false;

        return existed.get().getContractedAt() != null;
    }
}
