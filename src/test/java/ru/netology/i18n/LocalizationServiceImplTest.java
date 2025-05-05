package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.netology.entity.Country;


class LocalizationServiceImplTest {

    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    void testChangeLanguageToLocal_Russia() {
        String result = localizationService.changeLanguageToLocal(Country.RUSSIA);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    void testChangeLanguageToLocal_OtherCountry() {
        String result = localizationService.changeLanguageToLocal(Country.USA);
        assertEquals("Welcome", result);

        result = localizationService.changeLanguageToLocal(Country.BRAZIL);
        assertEquals("Welcome", result);
    }


}