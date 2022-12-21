package com.arrange.play.enums;

public enum AimPlaceEnum {

  PARAM("param"),
  HEAD("head");

  AimPlaceEnum(String aim) {
    this.aim = aim;
  }

  private String aim;

  public String getAim() {
    return aim;
  }
}
