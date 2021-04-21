package services;

import entities.CD;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CDServiceTest {
    CDService cdService;
    ArtistService artistService;

    @Before
    public void setUp() throws Exception {
        cdService = new CDService();
        artistService = new ArtistService();
    }

    @Test
    public void findByID() {
        //Given
        CD cd = new CD();

        //When
        cd.setTitle("Zipcode");
        CD actual = cdService.findById(1L);

        //Then
        Assert.assertEquals(cd.getTitle(), actual.getTitle());
    }

    @Test
    public void findAll() {

        //Given
        int expected = 3;

        //When
        List<CD> cdList = cdService.findAll();
        int actual = cdList.size();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() {
        //Given
        CD cd = new CD();
        cd.setTitle("BangBang");
        cd.setDesc("not bad");
        cd.setPrice(14.99);
        cd.setYear(1990L);

        //When
        cdService.update(4L, cd);
        CD actual = cdService.findById(4L);

        //Then
        Assert.assertEquals(cd.getTitle(), actual.getTitle());
    }

    @Test
    public void create() {
        //Given
        CD cd = new CD();
        cd.setTitle("DownInDaDUMPS");
        cd.setDesc("feeling down rn");
        cd.setPrice(49.99);
        cd.setYear(2021L);

        //When
        cdService.create(cd);
        CD actual = cdService.findById(4L);

        //Then
        Assert.assertEquals(cd, actual);
    }

    @Test
    public void delete() {
        //Given
        CD cd = cdService.findById(4L);

        //When
        cdService.delete(4L);

        //Then
        Assert.assertFalse(cdService.findAll().contains(cd));
    }
}