package org.self;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Optional;

public class TestOptionalPresence {
    private HashMap<String,String> cache;
    private FieldDefinition definition;

    @BeforeEach
    void init() {
        System.out.println("Strating");
        cache = new HashMap<>();
        cache.put("1","One");
        cache.put("2","Two");
    }

    @Test
    void testDefinitionWhenSourceHasData() {
        definition = new FieldDefinition("FirstTest", Optional.empty(),Optional.empty(), Optional.of("Cache"));

        var output = definition.getSource()
                .map(x->retrieveValueFromSource("1"))
                .get();

        Assertions.assertEquals(output, "One");
    }


    @Test
    void testDefinitionWhenSourceHasDefaultData() {
        definition = new FieldDefinition("FirstTest", Optional.of("Three"),Optional.empty(), Optional.of("Cache"));

        var output = definition.getSource()
                .map(x->retrieveValueFromSource("3"))
                .orElseGet(()->definition.getDefaultValue().orElseThrow(()->new RuntimeException("Unexcpteced")));

        Assertions.assertEquals(output, "Three");
    }

    private String retrieveValueFromSource(String src) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(Object.class, Object.class);

        try {
            MethodHandle getHandle = lookup.findVirtual(HashMap.class, "get", methodType);
            return (String) getHandle.invoke(cache, src);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
