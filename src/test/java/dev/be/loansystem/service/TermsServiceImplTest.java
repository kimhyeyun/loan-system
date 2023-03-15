package dev.be.loansystem.service;

import dev.be.loansystem.domain.Terms;
import dev.be.loansystem.dto.TermsDTO.*;
import dev.be.loansystem.repository.TermsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TermsServiceImplTest {

    @InjectMocks TermsServiceImpl termsService;

    @Mock private TermsRepository termsRepository;

    @Spy private ModelMapper modelMapper;

    @Test
    void Shoule_ReturnResponseOfNewTermsEntity_When_RequestTerm() {
        Terms entity = Terms.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsd")
                .build();

        Request request = Request.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsd")
                .build();

        when(termsRepository.save(any(Terms.class))).thenReturn(entity);

        Response actual = termsService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getTermsDetailUrl()).isSameAs(entity.getTermsDetailUrl());
    }

    @Test
    void Should_ReturnAllResponseOfExistTermsEntities_When_RequestTermsList() {
        Terms entityA = Terms.builder()
                .name("대출 이용 약관 1")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdddads")
                .build();

        Terms entityB = Terms.builder()
                .name("대출 이용 약관 2")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdweqwq")
                .build();

        List<Terms> list = new LinkedList<>(Arrays.asList(entityA, entityB));

        when(termsRepository.findAll()).thenReturn(list);

        List<Response> actual = termsService.getAll();

        assertThat(actual.size()).isSameAs(list.size());
    }
}