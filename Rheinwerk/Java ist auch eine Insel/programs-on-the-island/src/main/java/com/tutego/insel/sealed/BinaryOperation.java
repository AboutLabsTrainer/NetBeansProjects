package com.tutego.insel.sealed;

abstract sealed class BinaryOperation permits Commutative, Noncommutative {}
abstract sealed class Commutative extends BinaryOperation permits Addition, Multiplication {}
final class Addition extends Commutative {}
final class Multiplication extends Commutative {}
non-sealed class Noncommutative extends BinaryOperation {}
class Subtraction extends Noncommutative {}
