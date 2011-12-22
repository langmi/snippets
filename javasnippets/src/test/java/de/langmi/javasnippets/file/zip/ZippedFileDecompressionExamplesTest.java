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
package de.langmi.javasnippets.file.zip;

import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZippedFileDecompressionExamplesTest.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class ZippedFileDecompressionExamplesTest {

    private static final Logger LOG = LoggerFactory.getLogger(ZippedFileDecompressionExamples.class);
    private static final String PATH_TO_COMPRESSED_TEST_FILE = "src/test/resources/input/input.txt.zip";
    private static final String OUTPUT_PATH = "target/zip-test-outputs/";
    private static final int EXPECTED_COUNT = 20;

    @Test
    public void testReadDecompressToFile() throws Exception {
        long startTime = startTimer();
        ZippedFileDecompressionExamples.readDecompressToFile(PATH_TO_COMPRESSED_TEST_FILE, OUTPUT_PATH);
        endTimerAndLogDuration(startTime);
    }

    @Test
    public void testReadDecompressInMemory() throws Exception {
        long startTime = startTimer();
        int readCount = ZippedFileDecompressionExamples.readDecompressInMemory(PATH_TO_COMPRESSED_TEST_FILE);
        assertEquals(EXPECTED_COUNT, readCount);
        endTimerAndLogDuration(startTime);
    }

    private long startTimer() {
        // start timer
        final long startTime = System.nanoTime();
        return startTime;
    }

    private void endTimerAndLogDuration(long startTime) {
        // end timer
        long endTime = System.nanoTime();
        LOG.info("duration of readDecompressToFile:" + String.valueOf((endTime - startTime) / 1000000));
    }
}
