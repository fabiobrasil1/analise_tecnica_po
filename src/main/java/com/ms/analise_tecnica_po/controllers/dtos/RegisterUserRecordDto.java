package com.ms.analise_tecnica_po.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRecordDto(
    @NotBlank 
    String name,
                              
    @NotBlank 
    @Email 
    String email
  ) {
}
