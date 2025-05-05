package ru.netology.geo;

import ru.netology.entity.Location;

public interface GeoService {

    Location giveAddressFromIP(String ip);

    Location byCoordinates(double latitude, double longitude);
}
