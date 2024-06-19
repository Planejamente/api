package org.planejamente.planejamente.configuration;

import lombok.Data;

@Data
public class Response {
    private int status;
    private String message;
    private String url;
    private String id;
}
