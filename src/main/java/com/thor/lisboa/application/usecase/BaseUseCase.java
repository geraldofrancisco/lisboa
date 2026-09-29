package com.thor.lisboa.application.usecase;

public abstract class BaseUseCase<T, R> {

  public R execute(T input) {
    return execute(input, new Object[0]);
  }

  protected R execute(T input, Object... args) {
    return null;
  }

}
