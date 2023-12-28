package fr.vocaltech.spring.webscraper.services;

import fr.vocaltech.spring.webscraper.models.Music;
import lombok.val;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SmoothJazzScraperServiceImpl implements SmoothJazzScraperService {
    private final String documentUrl = "https://www.smoothjazz.com/smoothjazz-playlist";

    @Override
    public List<Music> getPlayListLastHour() {
        var ref = new Object() {
            int i = 0;
            String artistName = "";
            String albumTitle = "";
            String songTitle = "";
            String imgUrl = "";
            String imgText = "";
        };

        Document document = getDocument();
        Elements viewContents = document.select("div.view-content");
        Elements lastHourElements = viewContents.get(3).getAllElements();

        List<Music> musicElements = lastHourElements
                .select("img.image-style-album2x, span.artist-name, span.album-title, span.song-title")
                        .stream()
                                .map(element -> {
                                    if (element.tag().getName().equals("img")) { // --- img section ---
                                        try {
                                            ref.imgUrl = buildImageUrl(element.attr("src"));
                                        } catch (MalformedURLException e) {
                                            throw new RuntimeException(e);
                                        }
                                        ref.imgText = element.attr("alt");
                                    }

                                    switch (element.className()) {
                                        case "artist-name" -> ref.artistName = element.text();
                                        case "album-title" -> ref.albumTitle = element.text();
                                        case "song-title" -> ref.songTitle = element.text();
                                    }

                                    ref.i++;

                                    if (ref.i == 4) {
                                        Music music = new Music(ref.artistName, ref.albumTitle, ref.songTitle, ref.imgUrl, ref.imgText);

                                        ref.i = 0;
                                        ref.artistName = "";
                                        ref.albumTitle = "";
                                        ref.songTitle = "";
                                        ref.imgUrl = "";
                                        ref.imgText = "";

                                        return music;
                                    } else {
                                        return null;
                                    }
                                })
                                        .toList();

        return musicElements.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Music getNowPlayingMusic() {
        Document document = getDocument();
        val viewContents = document.select("div.view-content");

        val nowArtistName = viewContents.get(1).select("span.artist-name").text();
        val nowAlbumTitle = viewContents.get(1).select("span.album-title").text();
        val nowSongTitle = viewContents.get(1).select("span.song-title").text();

        val nowImg = viewContents.get(1).select("img");
        String nomImgUrl;
        try {
            nomImgUrl = buildImageUrl(nowImg.attr("src"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        val nowImgText = nowImg.attr("alt");

        return new Music(nowArtistName, nowAlbumTitle, nowSongTitle, nomImgUrl, nowImgText);
    }

    @Override
    public String getDocumentUrl() {
        return documentUrl;
    }

    @Override
    public List<Music> getAllFromPlaylist() {
        Music nowPlayingMusic = getNowPlayingMusic();
        List<Music> lastHourPlayList = getPlayListLastHour();
        lastHourPlayList.add(nowPlayingMusic);
        return lastHourPlayList;
    }

    private Document getDocument() {
        Document document = new Document("");

        try {
            document = Jsoup.connect(documentUrl).get();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return document;
    }

    private String buildImageUrl(String imgSrc) throws MalformedURLException {
        URL url = new URL(documentUrl);

        return url.getProtocol() +
                "://" +
                url.getHost() +
                imgSrc;
    }
}
