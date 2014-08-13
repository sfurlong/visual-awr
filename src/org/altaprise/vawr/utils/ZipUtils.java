package org.altaprise.vawr.utils;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;


public class ZipUtils {

    private static final String INPUT_GZIP_FILE = "C:\\Git\\visual-awr\\docs\\osw_192.168.56.21.tar";
    private static final String OUTPUT_FILE = "C:\\Git\\visual-awr\\docs\\osw_192.168.56.21";

    public static void main(String[] args) {
        ZipUtils gZip = new ZipUtils();
        gZip.gunzipIt();
    }

    public void gunzipIt() {

        byte[] buffer = new byte[1024];

        try {
            this.untar("");

            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));
            BZip2CompressorInputStream bz;

            FileOutputStream out = new FileOutputStream(OUTPUT_FILE);

            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            gzis.close();
            out.close();
            
            this.untar("");

            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void untar(String fileName) {    
        String INPUT_FILE = "C:\\Git\\visual-awr\\docs\\osw_192.168.56.21.tar";

        try {
                    /* Read TAR File into TarArchiveInputStream */
                    TarArchiveInputStream myTarFile=new TarArchiveInputStream(new FileInputStream(new File(INPUT_FILE)));
                    /* To read individual TAR file */
                    TarArchiveEntry entry = null;
                    String individualFiles;
                    int offset;
                    FileOutputStream outputFile=null;
                    /* Create a loop to read every single entry in TAR file */
                    while ((entry = myTarFile.getNextTarEntry()) != null) {
                            /* Get the name of the file */
                            individualFiles = entry.getName();
                            individualFiles = individualFiles.substring(individualFiles.lastIndexOf("/")+1);
                            /* Get Size of the file and create a byte array for the size */
                            byte[] content = new byte[(int) entry.getSize()];
                            offset=0;
                            /* Some SOP statements to check progress */
                            System.out.println("File Name in TAR File is: " + individualFiles);
                            System.out.println("Size of the File is: " + entry.getSize());                  
                            System.out.println("Byte Array length: " + content.length);
                            /* Read file from the archive into byte array */
                            myTarFile.read(content, offset, content.length - offset);
                            /* Define OutputStream for writing the file */
                            outputFile=new FileOutputStream(new File(individualFiles));
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
