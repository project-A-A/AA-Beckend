package org.example.aa__backend.domain.home.controller;

import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.home.payload.HomeResponse;
import org.example.aa__backend.domain.home.service.HomeService;
import org.example.aa__backend.domain.user.payload.AccountViewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    private final HomeService homeService;

    // 검색창/기본 데이터 노출 (명세: 검색창을 보여준다)
    @GetMapping
    public ResponseEntity<HomeResponse> home() {
        return ResponseEntity.ok(homeService.getHome());
    }

    // 검색: 전공/이름 키워드
    @GetMapping("/search")
    public ResponseEntity<List<AccountViewDTO>> search(@RequestParam(required = false) String major,
                                                       @RequestParam(required = false) String name) {
        return ResponseEntity.ok(homeService.search(Optional.ofNullable(major), Optional.ofNullable(name)));
    }
}


