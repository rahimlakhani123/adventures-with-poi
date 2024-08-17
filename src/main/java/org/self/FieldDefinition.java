package org.self;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class FieldDefinition {
    private final String name;
    private final Optional<String> defaultValue;
    private final Optional<String> formatter;
    private final Optional<String> source;
}
