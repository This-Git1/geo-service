package ru.netology.i18n;

import ru.netology.entity.Country;

public class LocalizationServiceImpl implements LocalizationService {

    // TODO пменять название на более информативное changeLanguageToLocal
    public String changeLanguageToLocal(Country country) {
        switch (country) {
            case RUSSIA:
                return "Добро пожаловать";
            default:
                return "Welcome";
        }
    }
}
