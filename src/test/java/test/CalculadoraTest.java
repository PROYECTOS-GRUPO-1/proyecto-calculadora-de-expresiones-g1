package test;

import org.junit.jupiter.api.Test;
import src.Calculadora;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void testOperacionSimple() {
        assertEquals(14, Calculadora.evaluar("2 + 3 * 4"), 0.0001);
    }

    @Test
    void testConParentesis() {
        assertEquals(20, Calculadora.evaluar("(2 + 3) * 4"), 0.0001);
    }

    @Test
    void testExpresionInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            Calculadora.evaluar("2 + * 4");
        });
    }
} 
 