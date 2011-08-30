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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Some ZIP File Decompression Examples.
 *
 * @author Michael R. Lange
 */
public class ZippedFileDecompressionExamples {

    public static void readDecompressToFile(String pathToFile, String outputPath) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(pathToFile);
            ZipInputStream zin = new ZipInputStream(new BufferedInputStream(fis, 20000));
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                File dir = new File(outputPath);
                dir.mkdir();
                File file = new File(outputPath + entry.getName());
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);

                int data = 0;
                while ((data = zin.read()) != - 1) {
                    fos.write(data);
                }
                // close early
                fos.close();
            }
            fis.close();
            zin.close();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    public static int readDecompressInMemory(String pathToFile) throws Exception {
        int count = 0;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(pathToFile);
            ZipInputStream zin = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                int data = 0;
                while ((data = zin.read()) != - 1) {
                    output.write(data);
                }
                // close early
                output.close();
                // convert output to inputStream
                InputStream in = new ByteArrayInputStream(output.toByteArray());
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader br = new BufferedReader(isr);
                String item;
                do {
                    item = br.readLine();
                    if (item != null) {
                        count++;
                    }
                } while (item != null);
                br.close();
            }
            fis.close();
            zin.close();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return count;
    }

    public static void unzip(File zip, File extractTo) throws IOException {
        ZipFile archive = new ZipFile(zip);
        Enumeration e = archive.entries();
        while (e.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) e.nextElement();
            File file = new File(extractTo, entry.getName());
            if (entry.isDirectory() && !file.exists()) {
                file.mkdirs();
            } else {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                InputStream in = archive.getInputStream(entry);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(file));

                byte[] buffer = new byte[8192];
                int read;

                while (-1 != (read = in.read(buffer))) {
                    out.write(buffer, 0, read);
                }

                in.close();
                out.close();
            }
        }
    }
}
