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
package de.langmi.javasnippets.file.split;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Groking file splitting pure java.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class SplitFileInHalf {

    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Split a file in two even files.
     *
     * @param fileInput
     * @param fileOutput
     * @param fileOutput2
     * @throws Exception 
     */
    public static void splitFileInHalf(String fileInput, String fileOutput, String fileOutput2) throws Exception {
        // compute half
        int count = getLineCount(fileInput);
        int firstHalf = count / 2;
        int secondHalf = count - firstHalf;

        BufferedReader input = null;

        try {
            // open input
            FileInputStream fis = new FileInputStream(fileInput);
            InputStreamReader is = new InputStreamReader(fis, ENCODING_UTF_8);
            input = new BufferedReader(is);

            // first file
            writeFile(input, firstHalf, fileOutput);
            // second file
            writeFile(input, secondHalf, fileOutput2);
        } finally {
            if (input != null) {
                // close'em all
                input.close();
            }
        }
    }

    /**
     * Write to a (new) file from an already open file.
     *
     * @param input
     * @param limit
     * @param outputFile
     * @throws Exception 
     */
    public static void writeFile(BufferedReader input, int limit, String outputFile) throws Exception {

        BufferedWriter output = null;

        try {
            // open output, FileWriter/PrintWriter could be used, but its not possible to set encoding with it
            FileOutputStream fos = new FileOutputStream(outputFile);
            OutputStreamWriter os = new OutputStreamWriter(fos, ENCODING_UTF_8);
            output = new BufferedWriter(os);

            String line = null;
            int count = 0;

            while ((line = input.readLine()) != null) {
                output.write(line);
                count++;
                // break at split count, do not add line separator at end of file
                if (count != limit) {
                    output.write(LINE_SEPARATOR);
                } else {
                    break;
                }
            }
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * Get the line count for a file using Java 1.5 possibilities.
     *
     * @param file
     * @return
     * @throws Exception 
     */
    public static int getLineCount(String file) throws Exception {

        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(file), ENCODING_UTF_8);
            // read lines, not tokens for line-content
            scanner.useDelimiter(LINE_SEPARATOR);
            int count = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
            return count;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Nice short version from <a href="http://stackoverflow.com/a/1647015/62201">stackoverflow</a>.
     *
     * @param file
     * @return
     * @throws Exception 
     */
    public static int getLineCountAlternative(String file) throws Exception {
        LineNumberReader lnr = null;
        try {
            lnr = new LineNumberReader(new FileReader(file));
            String line = null;
            int count = 0;
            while ((line = lnr.readLine()) != null) {
                count = lnr.getLineNumber();
            }
            return count;
        } finally {
            if (lnr != null) {
                lnr.close();
            }
        }
    }
}
