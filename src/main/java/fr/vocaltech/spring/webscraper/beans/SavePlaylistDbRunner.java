package fr.vocaltech.spring.webscraper.beans;

import fr.vocaltech.spring.webscraper.models.Music;
import fr.vocaltech.spring.webscraper.repositories.MusicRepository;
import fr.vocaltech.spring.webscraper.services.SmoothJazzScraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SavePlaylistDbRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SavePlaylistDbRunner.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    SmoothJazzScraperService smoothJazzScraperService;

    @Override
    public void run(String... args) throws Exception {
        process();
    }

    @Scheduled(
            fixedRateString = "#{${savePlaylistDbRunner.scheduled.fixedRate}}",
            initialDelay = 60 * 1000
    )
    public void process() {
        logger.info("[SavePlaylistDbRunner.run()] @ {}", dateFormat.format(new Date()));

        List<Music> playlist = smoothJazzScraperService.getAllFromPlaylist();

        playlist.forEach(music -> {
            // check if music is already in DB
            Music foundMusic = musicRepository.findByArtistNameAndSongTitle(music.getArtistName().trim(), music.getSongTitle().trim());

            // if not, save in DB
            if (foundMusic == null) {
                //
                // we don't save music with
                // song_title ends with 'NEW RELEASE'
                //
                if (! music.getSongTitle().endsWith("NEW RELEASE")) {
                    Music savedMusic = musicRepository.save(music);
                    logger.info(String.format(" ---> savedMusic: %s / id: %d", savedMusic, savedMusic.getId()));
                }
            }
        });
    }
}
