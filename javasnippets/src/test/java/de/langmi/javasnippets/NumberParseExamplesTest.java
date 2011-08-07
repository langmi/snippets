/*
 * Copyright 2011 Michael R. Lange <michael.r.lange@langmi.de>.
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
package de.langmi.javasnippets;

import static org.junit.Assert.*;
import java.util.Locale;
import org.junit.Test;

/**
 * NumberParseTest.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class NumberParseExamplesTest {

    /**
     * Various parsing problems with double.
     * 
     * @throws Exception 
     */
    @Test
    public void testDoubleParsingProblems() throws Exception {
        // double parses just ignores and stops at wrong characters
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1@", Locale.UK));
        // scientific notation, but wrong one, capital "E" needed, works either/sadly
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1e6", Locale.UK));
    }

    /**
     * Test parsing with scientific notation.
     * 
     * @throws Exception 
     */
    @Test
    public void testDoubleScientificNotation() throws Exception {
        // Locale does not matter
        // scientific notation, but wrong one, capital "E" needed
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1e6", Locale.GERMAN));
        // now the right scientific notation
        assertEquals(Double.valueOf(1000000d),
                     NumberParseExamples.parseDouble("1E6", Locale.GERMAN));
        // scientific notation, but wrong one, capital "E" needed
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1e6", Locale.UK));
        // now the right scientific notation
        assertEquals(Double.valueOf(1000000d),
                     NumberParseExamples.parseDouble("1E6", Locale.UK));
    }

    /**
     * Test parsing with german style decimal numbers.
     * 
     * @throws Exception 
     */
    @Test
    public void testDoubleGerman() throws Exception {
        Locale locale = Locale.GERMAN;
        // simple with comma
        assertEquals(Double.valueOf(11.01d),
                     NumberParseExamples.parseDouble("11,01", locale));
        // simple with 1000 point and comma
        assertEquals(Double.valueOf(1000d),
                     NumberParseExamples.parseDouble("1.000,00", locale));
        // comma just with 1
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1,0", locale));
        // more zeroes
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1,00", locale));
        // another one
        assertEquals(Double.valueOf(1.38d),
                     NumberParseExamples.parseDouble("1,38", locale));
    }

    /**
     * Test parsing with uk style decimal numbers.
     * 
     * @throws Exception 
     */
    @Test
    public void testDoubleUk() throws Exception {
        Locale locale = Locale.UK;
        // simple with comma
        assertEquals(Double.valueOf(11.01d),
                     NumberParseExamples.parseDouble("11.01", locale));
        // simple with 1000 point and comma
        assertEquals(Double.valueOf(1000d),
                     NumberParseExamples.parseDouble("1,000.00", locale));
        // comma just with 1
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1.0", locale));
        // more zeroes
        assertEquals(Double.valueOf(1d),
                     NumberParseExamples.parseDouble("1.00", locale));
        // another one
        assertEquals(Double.valueOf(1.38d),
                     NumberParseExamples.parseDouble("1.38", locale));
    }
}
