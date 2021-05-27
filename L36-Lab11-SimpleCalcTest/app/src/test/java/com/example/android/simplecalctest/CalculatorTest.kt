/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.simplecalctest

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * JUnit4 unit tests for the calculator logic.
 * These are local unit tests; no device needed.
 */
@RunWith(JUnit4::class)
class CalculatorTest {
    private var mCalculator: Calculator? = null

    /**
     * Sets up the environment for testing.
     */
    @Before
    fun setUp() {
        mCalculator = Calculator()
    }

    /**
     * Tests for simple addition.
     */
    @Test
    fun addTwoNumbers() {
        val resultAdd = mCalculator!!.add(1.0, 1.0)
        assertEquals(resultAdd, 2.0, 0.0)
    }

    /**
     * Tests for addition with a negative operand.
     */
    @Test
    fun addTwoNumbersNegative() {
        val resultAdd = mCalculator!!.add(-1.0, 2.0)
        assertEquals(resultAdd, 1.0, 0.0)
    }

    /**
     * Tests for addition where the result is negative.
     */
    @Test
    fun addTwoNumbersWorksWithNegativeResult() {
        val resultAdd = mCalculator!!.add(-1.0, -17.0)
        assertEquals(resultAdd, -18.0, 0.0)
    }

    /**
     * Tests for floating-point addition.
     */
    @Test
    fun addTwoNumbersFloats() {
        val resultAdd = mCalculator!!.add(1.111, 1.111)
        assertEquals(resultAdd, 2.222, 0.0)
    }

    /**
     * Tests for especially large numbers.
     */
    @Test
    fun addTwoNumbersBignums() {
        val resultAdd = mCalculator!!.add(123456781.0, 111111111.0)
        assertEquals(resultAdd, 234567892.0, 0.0)
    }

    /**
     * Tests for simple subtraction.
     */
    @Test
    fun subTwoNumbers() {
        val resultSub = mCalculator!!.sub(1.0, 1.0)
        assertEquals(resultSub, 0.0, 0.0)
    }

    /**
     * Tests for simple subtraction with a negative result.
     */
    @Test
    fun subWorksWithNegativeResult() {
        val resultSub = mCalculator!!.sub(1.0, 17.0)
        assertEquals(resultSub, -16.0, 0.0)
    }

    /**
     * Tests for simple division.
     */
    @Test
    fun divTwoNumbers() {
        val resultDiv = mCalculator!!.div(32.0, 2.0)
        assertEquals(resultDiv, 16.0, 0.0)
    }

    /**
     * Tests for divide by zero; must throw IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException::class)
    fun divByZeroThrows() {
        mCalculator!!.div(32.0, 0.0)
    }

    /**
     * Tests for divide by zero; always fails, so removed.
     */
    /*@Test
    open fun divTwoNumbersZero() {
        val resultDiv = mCalculator!!.div(32.0, 0.0)
        assertEquals(resultDiv, Double.POSITIVE_INFINITY)
    }*/

    /**
     * Tests for simple multiplication.
     */
    @Test
    fun mulTwoNumbers() {
        val resultMul = mCalculator!!.mul(32.0, 2.0)
        assertEquals(resultMul, 64.0, 0.0)
    }
}