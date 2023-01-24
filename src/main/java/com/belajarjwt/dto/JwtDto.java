package com.belajarjwt.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * JwtDto
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JwtDto {

    private String id;
    private String accessToken;
    private String refrestToken;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     */
    public JwtDto() {
    }

    /**
     * @param id
     * @param accessToken
     * @param refrestToken
     */
    public JwtDto(String id, String accessToken, String refrestToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.refrestToken = refrestToken;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return the refrestToken
     */
    public String getRefrestToken() {
        return refrestToken;
    }

    /**
     * @param refrestToken the refrestToken to set
     */
    public void setRefrestToken(String refrestToken) {
        this.refrestToken = refrestToken;
    }

}