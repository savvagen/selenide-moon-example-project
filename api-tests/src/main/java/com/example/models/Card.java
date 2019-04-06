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
public class Card {

    @JsonProperty("longNum")
    public String longNum;

    @JsonProperty("expires")
    public String expires;

    @JsonProperty("ccv")
    public String ccv;

    @JsonProperty("userID")
    public String userID;

    @JsonProperty("id")
    public String id;


}
