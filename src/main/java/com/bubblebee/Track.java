package com.bubblebee;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Objects;

/**
 * Created by paladii on 21.07.2016.
 */
public class Track {
    public static final String MUSIC_COLLECTION_FAILED_JSON = "music-collection-failed.json";
    public static final String MUSIC_COLLECTION_FILE_NAME = "music-collection.json";
    private Integer id;
    private Integer owner_id;
    private String artist;
    private String title;
    private Integer duration;
    private Integer date;
    private String url;
    private Integer genre_id;

    public Track(Integer id, Integer owner_id, String artist, String title, Integer duration, Integer date, String url, Integer genre_id) {
        this.id = id;
        this.owner_id = owner_id;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.date = date;
        this.url = url;
        this.genre_id = genre_id;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Track> list = getTracks(gson, MUSIC_COLLECTION_FILE_NAME);
int counter = 0;
        for (Track track : list) {
            try {
                counter++;
                downloadTrackToFile(track);
                System.out.println(counter + "__DOWNLOADED----------" + track);
            } catch (IOException e) {
                System.err.println("failed to download " + track.toString());
                e.printStackTrace();

                List<Track> failedTracks = getTracks(gson, MUSIC_COLLECTION_FAILED_JSON);
                failedTracks.add(track);
                writeList(failedTracks, MUSIC_COLLECTION_FAILED_JSON);
            }
        }
        System.out.println();

//        writeToFile();
    }

    private static void downloadTrackToFile(Track track) throws IOException {
        URL url = new URL(track.url);

        URLConnection con = url.openConnection();
        con.setConnectTimeout(Integer.MAX_VALUE);
        con.setReadTimeout(Integer.MAX_VALUE);
        InputStream in = con.getInputStream();

//InputStream inputStream = website.openStream();
        ReadableByteChannel rbc = Channels.newChannel(in);
        FileOutputStream fos = new FileOutputStream("music/" + track.artist + " - " + track.title + ".mp3" );
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    private static void writeList(List<Track> failedTracks, String filename) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filename)) {

            gson.toJson(failedTracks, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Track> getTracks(Gson gson, String fileName) {
        List<Track> list = null;

        try (Reader reader = new FileReader(fileName)) {
            // Convert JSON to Java Object
            list = gson.fromJson(reader, new TypeToken<List<Track>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void writeToFile() {
        Track track = new Track(1, 1, "d", "d", 1, 1, "d", 1);

        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(track);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("staff.json")) {

            gson.toJson(track, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Track{");
        sb.append("id=").append(id);
        sb.append(", owner_id=").append(owner_id);
        sb.append(", artist='").append(artist).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", date=").append(date);
        sb.append(", url='").append(url).append('\'');
        sb.append(", genre_id=").append(genre_id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id) &&
                Objects.equals(owner_id, track.owner_id) &&
                Objects.equals(artist, track.artist) &&
                Objects.equals(title, track.title) &&
                Objects.equals(duration, track.duration) &&
                Objects.equals(date, track.date) &&
                Objects.equals(url, track.url) &&
                Objects.equals(genre_id, track.genre_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner_id, artist, title, duration, date, url, genre_id);
    }
}
