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

import java.text.NumberFormat;
import static org.junit.Assert.*;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * NumberParseTest.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class NumberParseExamplesTest {

    /**
     * Test parsing with german style decimal numbers.
     * 
     * @throws Exception 
     */
    @Test
    public void testDoubleGerman() throws Exception {
        this.testParse(
                new Object[][]{
                    {"11,01", 11.01d},
                    {"1.000,00", 1000d},
                    {"1,0", 1d},
                    {"1,00", 1d},
                    {"1,38", 1.38d}},
                Locale.GERMAN);
    }

    /**
     * Test parsing with uk style decimal numbers.
     * 
     * @throws Exception 
     */
    @Test
    public void testDoubleUk() throws Exception {
        this.testParse(
                new Object[][]{
                    {"11.01", 11.01d},
                    {"1,000.00", 1000d},
                    {"1.0", 1d},
                    {"1.00", 1d},
                    {"1.38", 1.38d}},
                Locale.UK);
    }

    /**
     * Extracted method for convenience.
     * 
     * @param data
     * @param locale
     * @throws Exception 
     */
    private void testParse(Object[][] data, Locale locale) throws Exception {
        NumberFormat f = NumberFormat.getInstance(locale);
        Map<String, Double> map = ArrayUtils.toMap(data);
        for (Entry<String, Double> entry : map.entrySet()) {
            Double result = NumberParseExamples.parseDouble(entry.getKey(), locale);
            assertEquals(entry.getValue(), result);
        }
    }
}
