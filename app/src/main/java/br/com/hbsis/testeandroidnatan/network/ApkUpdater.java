package br.com.hbsis.testeandroidnatan.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * Created by natan on 08/02/17.
 */

public class ApkUpdater {

    private String urlApk="";

    public File downloadNewVersion() throws IOException {
    File newApk = new File(DIRECTORY_DOWNLOADS);
        downloadFile(urlApk,newApk);
        return newApk;
    }
    private static void downloadFile(String url, File outputFile) throws IOException {

            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(u.openStream());

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
            fos.write(buffer);
            fos.flush();
            fos.close();

    }
}
