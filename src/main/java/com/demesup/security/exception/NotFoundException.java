package com.demesup.security.exception;

public class NotFoundException extends RuntimeException{

  public NotFoundException(Class<?> aClass, String value) {
    super(aClass.getSimpleName() + "with " + value + " is not found");
  }
}
