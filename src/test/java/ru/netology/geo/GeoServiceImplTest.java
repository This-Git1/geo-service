package ru.netology.geo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты GeoServiceImpl.giveAddressFromIP() с разными IP")
class GeoServiceImplTest {

    private final GeoServiceImpl geoService = new GeoServiceImpl();

    public static Stream<org.junit.jupiter.params.provider.Arguments> ipProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider
                        .Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                org.junit.jupiter.params.provider
                        .Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                org.junit.jupiter.params.provider
                        .Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                org.junit.jupiter.params.provider
                        .Arguments.of("172.123.45.67", new Location("Moscow", Country.RUSSIA, null, 0)),
                org.junit.jupiter.params.provider
                        .Arguments.of("96.100.1.1", new Location("New York", Country.USA, null, 0)),
                org.junit.jupiter.params.provider
                        .Arguments.of("10.0.0.1", null));
    }



    @ParameterizedTest(name = "IP: {0} → ожидаемое местоположение: {1}")
    @MethodSource("ipProvider")
    void testGiveAddressFromIP(String ip, Location expected) {
        Location actual = geoService.giveAddressFromIP(ip);
        assertEquals(expected, actual, () -> "Для IP " + ip + " ожидали " + expected + ", но получили " + actual);
    }


}