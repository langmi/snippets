/**
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
package de.langmi.javasnippets.junit;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

/**
 * Some tests to grok {@link Parameterized}.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

    /**
     * Method which provides the parameters for the tests.
     *
     * @return 
     */
    @Parameters
    public static List<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                    {0, 0}, {1, 2}, {2, 4}, {3, 6}, {4, 8}, {5, 10}, {6, 12}
                });
    }
    /** Variables to take test data. */
    private int testNumber;
    /** Variables to take test data. */
    private int expectedNumber;

    /**
     * Suitable constructor for the test data.
     *
     * @param testNumber
     * @param expectedNumber 
     */
    public ParameterizedTest(int testNumber, int expectedNumber) {
        this.testNumber = testNumber;
        this.expectedNumber = expectedNumber;
    }

    /**
     * Simple test method working with the test data.
     *
     * @throws Exception 
     */
    @Test
    public void testFoo() throws Exception {
        assertTrue(expectedNumber == testNumber * 2);
    }
}
