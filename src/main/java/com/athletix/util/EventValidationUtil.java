package com.athletix.util;

import java.util.Set;
import com.athletix.enums.SportEnum;

public class EventValidationUtil {
    private static final Set<String> FORBIDDEN_USERNAMES = Set.of(
            "admin", "administrator", "root", "superuser", "index", "login",
            "register", "signup", "signin", "logout", "signout", "home", "dashboard",
            "profile", "settings", "configuration", "preferences", "account", "user",
            "users", "adminpanel", "homepage", "main", "default", "welcome", "about",
            "contact", "services", "create-account", "profiles", "create", "edit", "delete");

    public static void validateTitle(String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("El titulo no puede ser nula");
        if (title.length() > 50)
            throw new IllegalArgumentException("El titulo no puede superar los 50 caracteres");

    }

    public static void validateShortDescription(String shortDescription) {
        if (shortDescription == null || shortDescription.trim().isEmpty())
            throw new IllegalArgumentException("La descripcion corta no puede ser nula");
        if (shortDescription.length() > 100)
            throw new IllegalArgumentException("La descripcion corta no puede superar los 100 caracteres");
    }

    public static void validateDescription(String Description) {
        if (Description.length() > 1999)
            throw new IllegalArgumentException("La descripcion no puede superar los 1999 caracteres");
    }

    public static void validateUbication(String ubication) {
        if (ubication == null || ubication.trim().isEmpty())
            throw new IllegalArgumentException("La ubicaion no puede ser nula");
        if (ubication.length() > 255)
            throw new IllegalArgumentException("La ubicaion no puede superar los 255 caracteres");

    }

    public static void validateDistance(Float distance) {
        if (distance == null)
            throw new IllegalArgumentException("La distancia no puede ser nula");
        if (distance < 0)
            throw new IllegalArgumentException("La distancia no puede ser negativa");
    }

    /*public static void validateHour(int hour) {
        if (hour == null || hour.trim().isEmpty())
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo");
        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException("La hora debe estar entre 0 y 23");
    }


      public static void validateMinute(int minute) {
        if (minute == null || minute.trim().isEmpty())
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo");
        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException("El minuto debe estar entre 0 y 59");
     
    }

    public static void validateActivity(SportEnum activity) {
        if (activity == null)
            throw new IllegalArgumentException("La actividad no puede ser nula");
    }*/

}
