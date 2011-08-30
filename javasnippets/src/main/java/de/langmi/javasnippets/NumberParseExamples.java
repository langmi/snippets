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
package de.langmi.javasnippets;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * NumberParseTest.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class NumberParseExamples {

    /**
     * Parse String to Double according to provided locale
     * 
     * @param value
     * @param locale
     * @return
     * @throws Exception 
     */
    public static Double parseDouble(String value, Locale locale) throws Exception {
        // use proper way to get NumberFormat instance
        NumberFormat f = NumberFormat.getInstance(locale);
        // its not guaranteed to get a decimalFormat instance
        if (f instanceof DecimalFormat) {
            // some configuration possibilities
            //((DecimalFormat) f).setRoundingMode(RoundingMode.UNNECESSARY);
            //((DecimalFormat) f).setMinimumIntegerDigits(6);
            //((DecimalFormat) f).setMinimumIntegerDigits(6);
            //((DecimalFormat) f).setMaximumFractionDigits(6);
            //((DecimalFormat) f).setMaximumIntegerDigits(6);
            Double result = ((DecimalFormat) f).parse(value).doubleValue();
            return result;
        } else {
            return f.parse(value).doubleValue();
        }
    }
}
