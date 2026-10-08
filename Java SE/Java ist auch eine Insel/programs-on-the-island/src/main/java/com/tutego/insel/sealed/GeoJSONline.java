package com.tutego.insel.sealed;

record GeoJSONline(Coordinate... coordinates) implements GeoJSONshape {}
