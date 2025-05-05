package ru.netology.sender;

import java.util.Map;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

public class MessageSenderImpl implements MessageSender {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    private final GeoService geoService;

    private final LocalizationService localizationService;

    public MessageSenderImpl(GeoService geoService, LocalizationService localizationService) {
        this.geoService = geoService;
        this.localizationService = localizationService;
    }

    /* TODO
    * метод упадет если IP не передан (headers пустой)
     */
    public String send(Map<String, String> headers) {
        String ipAddress = String.valueOf(headers.get(IP_ADDRESS_HEADER));
        if (ipAddress != null && !ipAddress.isEmpty()) {
            Location location = geoService.giveAddressFromIP(ipAddress);
            System.out.printf("Отправлено сообщение: %s",
                    localizationService.changeLanguageToLocal(location.getCountry()));

            return localizationService.changeLanguageToLocal(location.getCountry());
        }
        return localizationService.changeLanguageToLocal(Country.USA);
    }



}
