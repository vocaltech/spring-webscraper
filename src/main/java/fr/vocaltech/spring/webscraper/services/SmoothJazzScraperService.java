package fr.vocaltech.spring.webscraper.services;

import fr.vocaltech.spring.webscraper.models.Music;

import java.util.List;

public interface SmoothJazzScraperService extends ScraperService {
    List<Music> getPlayListLastHour();
    List<Music> getAllFromPlaylist(); // now playing + last hour
    Music getNowPlayingMusic();
}
