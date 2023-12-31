package com.ms.analise_tecnica_po.domain.user.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms.analise_tecnica_po.domain.user.controllers.dtos.RegisterUserRecordDto;
import com.ms.analise_tecnica_po.domain.user.controllers.dtos.UserDetailsRecordDto;
import com.ms.analise_tecnica_po.domain.user.services.FindUserByIdUseCase;
import com.ms.analise_tecnica_po.domain.user.services.RegisterUserUseCase;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private RegisterUserUseCase registerUserUC;
  @Autowired
  private FindUserByIdUseCase findUserByIdUC;

  @PostMapping
  @Transactional
  public ResponseEntity<UserDetailsRecordDto> register(@RequestBody @Valid RegisterUserRecordDto data,
      UriComponentsBuilder uriBuilder) {
    return registerUserUC.execute(data, uriBuilder);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping("/{id}")
  public ResponseEntity<UserDetailsRecordDto> findById() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
    return findUserByIdUC.execute(UUID.fromString(userId));
  }
}
