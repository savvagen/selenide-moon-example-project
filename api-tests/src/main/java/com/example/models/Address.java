package com.example.models;


import lombok.*;
import lombok.experimental.Accessors;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Address {

    @JsonProperty("street")
    public String street;

    @JsonProperty("number")
    public String number;

    @JsonProperty("country")
    public String country;

    @JsonProperty("city")
    public String city;

    @JsonProperty("postcode")
    public String postcode;

    @JsonProperty("userID")
    public String userID;




}


