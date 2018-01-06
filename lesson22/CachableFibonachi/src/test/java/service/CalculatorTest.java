package service;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void fibonachi() throws Exception {
        assertEquals(calculator.fibonachi(1),1);
        assertEquals(calculator.fibonachi(2),1);
        assertEquals(calculator.fibonachi(8),21);
        assertEquals(calculator.fibonachi(10),55);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgument0() throws Exception {
        calculator.fibonachi(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgument47() throws Exception {
        calculator.fibonachi(47);
    }
}