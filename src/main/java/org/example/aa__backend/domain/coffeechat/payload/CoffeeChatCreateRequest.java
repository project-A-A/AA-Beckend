package org.example.aa__backend.domain.coffeechat.payload;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoffeeChatCreateRequest {

    @Size(max = 1000)
    private String message;
}


