package br.com.hbsis.testeandroidnatan.util;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by natan on 08/02/17.
 */

public class ApkUpdater {

    private String urlApk="http://185.28.21.78/app.zip";

    public File downloadNewVersion() throws IOException {

        URL url = new URL(urlApk);
        HttpURLConnection c = (HttpURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.setDoOutput(true);
        c.connect();

        String PATH = Environment.getExternalStorageDirectory().toString();
        File file = new File(PATH);
        file.mkdirs();
        File outputFile = new File(file, "appv2.zip");

        FileOutputStream fos = new FileOutputStream(outputFile);
        InputStream is = c.getInputStream();

        byte[] buffer = new byte[4096];
        int len1 = 0;

        while ((len1 = is.read(buffer)) != -1)
        {
            fos.write(buffer, 0, len1);
        }
        fos.close();
        is.close();

        File newApk = new File(file,"hbsisv2.apk");
        UnzipUtility unzipUtility = new UnzipUtility();
        unzipUtility.unzip(outputFile.getAbsolutePath(),newApk.getAbsolutePath());

        return newApk;
    }
}
