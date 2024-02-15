package data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru-RU"));

    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return java.time.LocalDate.now().plusDays(shift).format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            String city = generateCity(locale);
            String name = generateName(locale);
            String phone = generatePhone(locale);

            return new UserInfo(city, name, phone);
        }
    }

    public static String generateCity(String locale) {
        return faker.address().city();
    }

    public static String generateName(String locale) {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        return faker.phoneNumber().phoneNumber();
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;

        public UserInfo(String city, String name, String phone) {
            this.city = city;
            this.name = name;
            this.phone = phone;
        }

        public String getCity() {
            return city;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }
    }

    public static void main(String[] args) {
        UserInfo user = Registration.generateUser("ru");
        System.out.println("Generated User: " + user);
    }
}