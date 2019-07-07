package com.example.models;

import com.github.javafaker.Faker;
import lombok.*;
import lombok.experimental.Accessors;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Locale;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {

    @JsonProperty("firstName") public String firstName;
    @JsonProperty("lastName") public String lastName;
    @JsonProperty("username") public String username;
    @JsonProperty("email") public String email;
    @JsonProperty("password") public String password;
    @JsonProperty("id") public String id;

    private User(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.id = builder.id;
    }

    public static Builder generate(){
        return new Builder();
    }

    public static Builder Builder(){
        return new Builder();
    }

    public static class Builder {

        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private String password;
        private String id;

        private Builder(){ }

        public Builder withUsername(String username){
            this.username = username;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }


        public User build() {
            return new User(this);
        }


        public User withLocale(Locales.Locale locale){
            Faker faker = new Faker(new Locale(locale.locale));
            return new User().setFirstName(faker.name().firstName())
                    .setLastName(faker.name().lastName())
                    .setUsername(faker.name().username())
                    .setEmail(faker.internet().emailAddress())
                    .setPassword(faker.internet().password())
                    .setId(faker.internet().uuid());
        }



    }


}

