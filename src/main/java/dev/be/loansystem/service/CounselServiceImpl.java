package dev.be.loansystem.service;

import dev.be.loansystem.domain.Counsel;
import dev.be.loansystem.dto.CounselDTO.Request;
import dev.be.loansystem.dto.CounselDTO.Response;
import dev.be.loansystem.exception.BaseException;
import dev.be.loansystem.exception.ResultType;
import dev.be.loansystem.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService{

    private final CounselRepository counselRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class);
        counsel.setAppliedAt(LocalDateTime.now());

        Counsel created = counselRepository.save(counsel);

        return modelMapper.map(created, Response.class);
    }

    @Override
    public Response get(Long counselId) {
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
            // TODO: 추후 더 디테일한 에러 메세지 정의 및 고도화 진행
        });

        return modelMapper.map(counsel, Response.class);
    }
}
