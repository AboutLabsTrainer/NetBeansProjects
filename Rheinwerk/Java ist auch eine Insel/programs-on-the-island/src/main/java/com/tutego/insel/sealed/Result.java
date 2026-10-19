package com.tutego.insel.sealed;

abstract sealed class Result
    permits Failure, Success {
  final Object body;
  Result( Object body ) {
    this.body = body;
  }
}
