package com.tutego.insel.sealed;

final class Failure extends Result {
  final int errorCode;
  Failure( int errorCode, Object body ) {
    super( body );
    this.errorCode = errorCode;
  }
}
