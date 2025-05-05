package ru.netology.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты MessageSenderImpl")
class MessageSenderImplTest {

    MessageSenderImpl messageSender;

    @Mock
    GeoService geoService;

    @Mock
    LocalizationService localizationService;

    @BeforeEach
    void setUp() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    @DisplayName("При наличии IP адресса")
    void test_send_if_IP_exists() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Location locationMap = new Location("SomeCity", Country.RUSSIA, null, 0);
        String ip = "172.123.12.19";

        when(geoService.giveAddressFromIP(ip)).thenReturn(locationMap);
        when(localizationService.changeLanguageToLocal(Country.RUSSIA)).thenReturn("Добро пожаловать!");

        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals("Добро пожаловать!", result);
        verify(localizationService, times(2)).changeLanguageToLocal(Country.RUSSIA);

    }

    @Test
    @DisplayName("При отсутвии IP адресса")
    void test_send_if_IP_not_exists() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");
        String expectedMassage = "Welcome";

        when(localizationService.changeLanguageToLocal(Country.USA)).thenReturn("Welcome");

        // Act
        String result = messageSender.send(headers);

        // Assert
        assertEquals(expectedMassage, result);

    }


}