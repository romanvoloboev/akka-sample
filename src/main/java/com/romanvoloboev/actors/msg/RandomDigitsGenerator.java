package com.romanvoloboev.actors.msg;

import java.util.PrimitiveIterator;
import java.util.Random;

/**
 * Created at 10.11.17
 *
 * @author romanvoloboev
 */
public class RandomDigitsGenerator {
    private PrimitiveIterator.OfInt randomIterator;

    public RandomDigitsGenerator(int min, int max) {
        randomIterator = new Random().ints(min, max + 1).iterator();
    }

    public int nextInt() {
        return randomIterator.nextInt();
    }
}
