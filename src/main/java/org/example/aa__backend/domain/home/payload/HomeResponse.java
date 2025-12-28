package org.example.aa__backend.domain.home.payload;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HomeResponse {
    private final List<String> tags;         // 예: 기술 스택/전공 태그
    private final List<String> majors;       // 예: 전공 키워드
    private final List<String> trending;     // 예: 인기 검색어
}


