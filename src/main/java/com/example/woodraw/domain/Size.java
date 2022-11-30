package com.example.woodraw.domain;

import java.util.Arrays;
import java.util.Objects;

public enum Size {
	SIZE_250("250");

	private final String length;

	Size(String length) {
		this.length = length;
	}

	public static Size getSizeByLength(String length) {
		return Arrays.stream(values())
			.filter(i -> Objects.equals(i.length, length))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException());
	}
}
