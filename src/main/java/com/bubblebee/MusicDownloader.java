package com.bubblebee;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by paladii on 21.07.2016.
 */
public class MusicDownloader {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://psv4.vk.me/c1635/u629723/audios/6cbe513e7e04.mp3?extra=99lSc9dl_cf0YtrXtn_T07PeFlbJ-tDkBh6mihrwpi3r2uHrjomKovYSzeh_ukgEEdIiBvqxIjAL9kfovkjQvGAjspirIZZqXr2F3QH1L7p_dRqOi_Gzh01F1Bac_fH8Me6Z7Zv5BcE");

        URLConnection con = url.openConnection();
        con.setConnectTimeout(Integer.MAX_VALUE);
        con.setReadTimeout(Integer.MAX_VALUE);
        InputStream in = con.getInputStream();

//InputStream inputStream = website.openStream();
        ReadableByteChannel rbc = Channels.newChannel(in);
        FileOutputStream fos = new FileOutputStream("music.mp3");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

}
