package com.github.queueflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    @JsonProperty("telegram_id")
    public String telegramId;

    @JsonProperty("display_name")
    public String displayName;
}
