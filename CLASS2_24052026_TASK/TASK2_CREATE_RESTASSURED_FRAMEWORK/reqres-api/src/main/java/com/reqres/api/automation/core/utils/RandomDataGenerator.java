package com.reqres.api.automation.core.utils;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomDataGenerator {
    private static final ThreadLocal<Faker> FAKER = ThreadLocal.withInitial(Faker::new);
    private RandomDataGenerator() {}
    private static Faker faker() { return FAKER.get(); }

    public static String fullName()    { return faker().name().fullName();   }
    public static String firstName()   { return faker().name().firstName();  }
    public static String lastName()    { return faker().name().lastName();   }
    public static String username()    { return faker().name().username();   }
    public static String email()       { return faker().internet().emailAddress(); }
    public static String phone()       { return faker().phoneNumber().phoneNumber(); }
    public static String address()     { return faker().address().fullAddress(); }

    public static String uniqueEmail() {
        return "test_" + timestamp() + "_" + faker().number().numberBetween(1000, 9999) + "@automation.com";
    }

    public static String alphanumeric(int length) {
        return faker().regexify("[A-Za-z0-9]{" + length + "}");
    }
    public static int    randomInt(int min, int max) { return faker().number().numberBetween(min, max); }
    public static String uuid()        { return UUID.randomUUID().toString(); }
    public static String companyName() { return faker().company().name(); }
    public static String password()    { return faker().internet().password(10, 20, true, true, true); }

    public static String timestamp() { return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()); }
    public static String isoDate()   { return new SimpleDateFormat("yyyy-MM-dd").format(new Date()); }
}
