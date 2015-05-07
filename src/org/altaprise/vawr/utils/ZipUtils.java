/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.altaprise.vawr.utils;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;


public class ZipUtils {

    private static final String INPUT_GZIP_FILE =
        "D:\\_NATO\\FY14-Programs\\20-20-for-SSC\\discovery-test-output\\collection_Wed_Sep_10_08_44_13_PDT_2014_node_all_cell_all.tgz";
    //private static final String INPUT_GZIP_FILE = "C:\\Git\\visual-awr\\docs\\osw_192.168.56.21.tar";
    private static final String OUTPUT_FILE = "D:\\collection_Wed_Sep_10_08_44_13_PDT_2014_node_all_cell_all.tar";

    public static void main(String[] args) {
        ZipUtils gZip = new ZipUtils();
        gZip.processFile();
    }

    public void processFile() {
        try {
            
            gunzipIt(this.INPUT_GZIP_FILE, this.OUTPUT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gunzipIt(String inputFileName, String outputFileName) {

        byte[] buffer = new byte[1024];

        try {

            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(inputFileName));
            BZip2CompressorInputStream bz;

            FileOutputStream out = new FileOutputStream(outputFileName);

            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            gzis.close();
            out.close();

            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void untar(String fileName) {
        String INPUT_FILE = "C:\\Git\\visual-awr\\docs\\osw_192.168.56.21.tar";

        try {
            /* Read TAR File into TarArchiveInputStream */
            TarArchiveInputStream myTarFile = new TarArchiveInputStream(new FileInputStream(new File(INPUT_FILE)));
            /* To read individual TAR file */
            TarArchiveEntry entry = null;
            String individualFiles;
            int offset;
            FileOutputStream outputFile = null;
            /* Create a loop to read every single entry in TAR file */
            while ((entry = myTarFile.getNextTarEntry()) != null) {
                /* Get the name of the file */
                individualFiles = entry.getName();
                individualFiles = individualFiles.substring(individualFiles.lastIndexOf("/") + 1);
                /* Get Size of the file and create a byte array for the size */
                byte[] content = new byte[(int) entry.getSize()];
                offset = 0;
                /* Some SOP statements to check progress */
                System.out.println("File Name in TAR File is: " + individualFiles);
                System.out.println("Size of the File is: " + entry.getSize());
                System.out.println("Byte Array length: " + content.length);
                /* Read file from the archive into byte array */
                myTarFile.read(content, offset, content.length - offset);
                /* Define OutputStream for writing the file */
                outputFile = new FileOutputStream(new File(individualFiles));
                /* Use IOUtiles to write content of byte array to physical file */
                outputFile.write(content);
                //IOUtils.write(content,outputFile);
                /* Close Output Stream */
                outputFile.close();
            }
            /* Close TarAchiveInputStream */
            myTarFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
