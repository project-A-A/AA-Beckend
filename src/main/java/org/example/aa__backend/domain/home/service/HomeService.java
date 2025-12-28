package org.example.aa__backend.domain.home.service;

import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.home.payload.HomeResponse;
import org.example.aa__backend.domain.user.payload.AccountViewDTO;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final AccountRepository accountRepository;

    public HomeResponse getHome() {
        List<String> majors = accountRepository.findDistinctMajors();
        return HomeResponse.builder()
                .tags(Collections.emptyList())
                .majors(majors)
                .trending(Collections.emptyList())
                .build();
    }

    public List<AccountViewDTO> search(Optional<String> major, Optional<String> name) {
        boolean hasName = name.isPresent() && !name.get().isBlank();
        boolean hasMajor = major.isPresent() && !major.get().isBlank();

        if (hasName && hasMajor) {
            return accountRepository.findByNameContainingIgnoreCaseOrMajorContainingIgnoreCase(name.get(), major.get()).stream()
                    .map(acc -> new AccountViewDTO(acc.getId(), acc.getEmail(), acc.getName(), acc.getMajor(), acc.getRole()))
                    .collect(Collectors.toList());
        }
        if (hasName) {
            return accountRepository.findByNameContainingIgnoreCase(name.get()).stream()
                    .map(acc -> new AccountViewDTO(acc.getId(), acc.getEmail(), acc.getName(), acc.getMajor(), acc.getRole()))
                    .collect(Collectors.toList());
        }
        if (hasMajor) {
            return accountRepository.findByMajorContainingIgnoreCase(major.get()).stream()
                    .map(acc -> new AccountViewDTO(acc.getId(), acc.getEmail(), acc.getName(), acc.getMajor(), acc.getRole()))
                    .collect(Collectors.toList());
        }
        return accountRepository.findAll().stream()
                .map(acc -> new AccountViewDTO(acc.getId(), acc.getEmail(), acc.getName(), acc.getMajor(), acc.getRole()))
                .collect(Collectors.toList());
    }
}


