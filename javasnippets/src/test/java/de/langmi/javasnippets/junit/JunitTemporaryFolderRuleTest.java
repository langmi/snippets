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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

/**
 * Since JUnit 4.7 the {@link Rule} {@link TemporaryFolder} can be used to create
 * a temporary folder for the test.
 * 
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class JunitTemporaryFolderRuleTest {

    /** On Windows the temporary folder can be at c:\Temp\Junit\ */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    private File fileForTest;
    private static String TEST_CONTENT = "foo";

    /**
     * Create the test file in the temporary folder.
     *
     * @throws IOException 
     */
    @Before
    public void createTestData() throws IOException {
        fileForTest = tempFolder.newFile("test.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(fileForTest));
        out.write(TEST_CONTENT);
        out.close();
    }

    /**
     * Test if the file is successfully created.
     *
     * @throws IOException 
     */
    @Test
    public void testFileSuccessfullyCreated() throws IOException {
        assertTrue(fileForTest.exists());
    }

    /**
     * Check if the file has the proper content.
     *
     * @throws IOException 
     */
    @Test
    public void testFileContent() throws IOException {
        assertTrue(fileForTest.exists());
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileForTest));
            String line = null;
            while ((line = in.readLine()) != null) {
                assertEquals(TEST_CONTENT, line);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    @After
    public void cleanUp() {
        // file should still exist
        assertTrue(fileForTest.exists());
    }
}
