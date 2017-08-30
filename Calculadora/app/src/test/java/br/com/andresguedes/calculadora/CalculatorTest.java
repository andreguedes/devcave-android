package br.com.andresguedes.calculadora;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by andreguedes on 29/08/17.
 */
public class CalculatorTest {

    @Test
    public void sum() throws Exception {
        double sum = Calculator.sum(1, 4);

        assertEquals(5, sum, 0.00001);
    }

    @Test
    public void subtract() throws Exception {
        double subtract = Calculator.subtract(4, 2);

        assertEquals(2, subtract, 0.00001);
    }

    @Test
    public void multiply() throws Exception {
        double multiply = Calculator.multiply(3, 6);

        assertEquals(18, multiply, 0.00001);
    }

    @Test
    public void divider() throws Exception {
        double divider = Calculator.divider(6, 3);

        assertEquals(2, divider, 0.00001);
    }

}