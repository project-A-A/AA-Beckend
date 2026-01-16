package org.example.aa__backend.domain.user.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountViewDTO {
    private UUID id;
    private String email;
    private String name;
    private String major;
    private String role;
} 
