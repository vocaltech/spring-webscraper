package fr.vocaltech.spring.webscraper.repositories;

import fr.vocaltech.spring.webscraper.models.Music;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicRepository extends CrudRepository<Music, Long> {
    /*
    @Query(
            value = "SELECT COUNT(*) FROM music_table WHERE artist_name LIKE :artistName || '%' AND album_title LIKE :albumTitle || '%' AND song_title LIKE :songTitle || '%'",
            nativeQuery = true
    )
    Integer isMusicAlreadyInDb(
            @Param("artistName") String artistName,
            @Param("albumTitle") String albumTitle,
            @Param("songTitle") String songTitle
    );

    */
    Music findByArtistNameAndSongTitle(String artistName, String songTitle);

    //  @Query("SELECT * FROM music_table WHERE artist_name LIKE :artist || '%' AND song_title LIKE :title || '%'")
    //Music findByArtistAndTitle(String artist, String title);
    //Music findByArtistStartingWithAndTitleStartingWith(String artist, String title);

    //Music findBySongTitleStartingWith(String songTitlePrefix);
    //List<Music> findByArtistStartingWith(String artistPrefix);
    // List<User> findByNameLike(String likePattern);

    //List<Music> findByArtistIs(String artist);

    /*
        @Query("SELECT COUNT(*) FROM music_table WHERE artist_name LIKE :artistName || '%' AND album_title LIKE :albumTitle || '%' AND song_title LIKE :songTitle || '%'")
    suspend fun isMusicAlreadyInserted(artistName: String, albumTitle: String, songTitle: String): Int

    @Query("SELECT id FROM music_table ORDER BY id DESC LIMIT 1")
    suspend fun lastInsertedMusic(): Int? // used to get the latest id value (maybe null)

     */
}
