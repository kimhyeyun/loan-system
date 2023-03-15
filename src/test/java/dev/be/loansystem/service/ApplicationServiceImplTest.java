package dev.be.loansystem.service;

import dev.be.loansystem.domain.Application;
import dev.be.loansystem.dto.ApplicationDTO.*;
import dev.be.loansystem.exception.BaseException;
import dev.be.loansystem.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @InjectMocks ApplicationServiceImpl applicationService;

    @Mock private ApplicationRepository applicationRepository;

    @Spy private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewApplyEntity_When_RequestApply() {
        Application entity = Application.builder()
                .name("Member Kim")
                .cellPhone("010-1111-2222")
                .email("mail@mail.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();


        Request request = Request.builder()
                .name("Member Kim")
                .cellPhone("010-1111-2222")
                .email("mail@mail.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);

        Response actual = applicationService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void Should_ReturnResponseOfExistApplicationEntity_When_RequestExistsApplicationId() {
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.get(1L);

        assertThat(actual.getApplicationId()).isSameAs(findId);
    }

    @Test
    void Should_ThrowException_When_RequestNotExistApplicationId(){
        Long findId = 2L;

        when(applicationRepository.findById(findId)).thenThrow(BaseException.class);

        assertThrows(BaseException.class, () -> applicationService.get(findId));
    }

    @Test
    void Should_ReturnUpdatedResponseOfExistApplicationEnity_When_ReuestUpdateExistApplicationInfo() {
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .name("Member Kim")
                .hopeAmount(BigDecimal.valueOf(500000))
                .build();

        Request request = Request.builder()
                .name("Member Yun")
                .hopeAmount(BigDecimal.valueOf(10000000))
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.update(findId, request);

        assertThat(actual.getApplicationId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }
}