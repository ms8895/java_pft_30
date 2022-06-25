package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.79.42.85");
   assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>87</State></GeoIP>");
    }


    @Test
    public void testMyInvalIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.79.42.xx");
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>87</State></GeoIP>");
    }
}
