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
        // 명세에 따른 태그/전공/트렌딩 키워드는 현재 소스에 데이터가 없어 빈 리스트로 제공.
        // 이후 태그/전공 테이블이 추가되면 여기서 조회하도록 확장.
        return HomeResponse.builder()
                .tags(Collections.emptyList())
                .majors(Collections.emptyList())
                .trending(Collections.emptyList())
                .build();
    }

    public List<AccountViewDTO> search(Optional<String> major, Optional<String> name) {
        // 현재 스키마에는 major 필드가 없어 name 기준으로만 검색.
        // major 데이터가 추가되면 repository 메서드를 확장하여 필터링 가능.
        if (name.isPresent() && !name.get().isBlank()) {
            return accountRepository.findByNameContainingIgnoreCase(name.get()).stream()
                    .map(acc -> new AccountViewDTO(acc.getId(), acc.getEmail(), acc.getName(), acc.getRole()))
                    .collect(Collectors.toList());
        }
        // 이름 키워드 없으면 전체 반환 (간단히 제한)
        return accountRepository.findAll().stream()
                .map(acc -> new AccountViewDTO(acc.getId(), acc.getEmail(), acc.getName(), acc.getRole()))
                .collect(Collectors.toList());
    }
}


