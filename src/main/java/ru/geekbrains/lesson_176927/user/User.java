package ru.geekbrains.lesson_176927.user;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return new StringBuilder("User: ")
                .append(firstName).append(" ")
                .append(lastName).append(" ")
                .append(middleName).append(" ")
                .append(age).append(" years old, ")
                .append(gender).append(", ")
                .append("address: ")
                .append(country).append(", ")
                .append(address).append(", ")
                .append("phone: ")
                .append(phone)
                .toString();
    }


    public static class UserBuilder {
        private final User user;

        public UserBuilder() {
            this.user = new User();
        }

        public UserBuilder firstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder lastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder middleName(String middleName) {
            user.setMiddleName(middleName);
            return this;
        }

        public UserBuilder country(String country) {
            user.setCountry(country);
            return this;
        }

        public UserBuilder address(String address) {
            user.setAddress(address);
            return this;
        }

        public UserBuilder phone(String phone) {
            user.setPhone(phone);
            return this;
        }

        public UserBuilder age(int age) {
            user.setAge(age);
            return this;
        }

        public UserBuilder gender(String gender) {
            user.setGender(gender);
            return this;
        }

        public User getUser() {
            return user;
        }
    }
}
