package org.example.aa__backend.domain.notice.controller;

import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.notice.payload.NoticeResponse;
import org.example.aa__backend.domain.notice.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
@CrossOrigin(origins = "http://localhost:3000")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<NoticeResponse>> list() {
        return ResponseEntity.ok(noticeService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.get(id));
    }
}


