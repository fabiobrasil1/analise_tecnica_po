package com.ms.analise_tecnica_po.models.user;

import jakarta.validation.constraints.NotNull;

public enum UserRole {
  @NotNull
  ADMIN("admin"),
  @NotNull
  USER("user");

  private String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}