package fr.vocaltech.spring.webscraper.controllers;

import fr.vocaltech.spring.webscraper.models.Music;
import fr.vocaltech.spring.webscraper.services.SmoothJazzScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SmoothJazzController {
    @Autowired
    SmoothJazzScraperService smoothJazzScraperService;
    @GetMapping("/smooth-jazz")
    public List<Music> getAllFromPlaylist() {
        return smoothJazzScraperService.getAllFromPlaylist();
    }
}
