package services;

import entities.Artist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ArtistServiceTest {
    ArtistService artistService;

    @Before
    public void setUp() throws Exception {
        artistService = new ArtistService();
    }

    @Test
    public void testFindById() {
        //Given
        Artist artist = new Artist();

        //When
        artist.setFirstName("Xiong");
        artist.setLastName("Yuan");
        Artist actual = artistService.findById(1L);

        //Then
        Assert.assertEquals(artist.getFirstName(), actual.getFirstName());
    }

    @Test
    public void testFindAll() {
        //Given
        int expected = 9;

        //When
        List<Artist> artistList = artistService.findAll();
        int actual = artistList.size();

        artistList.forEach(System.out::println);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        //Given
        Artist update = new Artist();
        update.setFirstName("Harry");
        update.setLastName("Mack");
        update.setInstrument("Vocals");

        //When
        artistService.update(10L, update);
        String expected = "Harry";
        String actual = artistService.findById(10L).getFirstName();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreate() {
        //Given
        Artist singer = new Artist();
        singer.setFirstName("Trey");
        singer.setLastName("Songz");
        singer.setInstrument("Vocals");

        //When
        artistService.create(singer);
        String expected = "Trey";
        String actual = artistService.findById(11L).getFirstName();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete() {
        //Given
        Artist singer = artistService.findById(11L);

        //When
        artistService.delete(11L);
        List<Artist> actual = artistService.findAll();

        //Then
        Assert.assertFalse(actual.contains(singer));
    }
}