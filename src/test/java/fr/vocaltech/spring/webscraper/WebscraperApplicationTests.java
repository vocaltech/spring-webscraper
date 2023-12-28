package fr.vocaltech.spring.webscraper;

import fr.vocaltech.spring.webscraper.models.Music;
import fr.vocaltech.spring.webscraper.services.SmoothJazzScraperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WebscraperApplicationTests {
	@Autowired
	SmoothJazzScraperService smoothJazzScraperService;

	@Test
	void contextLoads() {
	}

	@Test
	void whenContextLoads_thenSmoothJazzScraperServiceIsNotNull() {
		assertNotNull(smoothJazzScraperService);
	}

	@Test
	void givenSmoothJazzScraperService_whenGetPlayListLastHour_thenOk() {
		List<Music> lastHourPlaylist = smoothJazzScraperService.getPlayListLastHour();

		lastHourPlaylist
				.forEach(System.out::println);
	}

	@Test
	void givenSmoothJazzScraperService_whenGetNowPlaying_thenOk() {
		Music nowPlaying = smoothJazzScraperService.getNowPlayingMusic();
		System.out.println(nowPlaying);
	}

	@Test
	void givenSmoothJazzScraperService_whenGetAllMusics_thenOk() {
		List<Music> allMusics = smoothJazzScraperService.getAllFromPlaylist();
		allMusics.forEach(System.out::println);
	}
}
