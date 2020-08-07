package com.World_Navigator.demo.config;

import java.security.Principal;

class StompPrincipal implements Principal {
  String name;

  public StompPrincipal(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
