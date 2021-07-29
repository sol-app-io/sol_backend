package com.sol.client.base.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Error {
    private PresentationData presentationData;
    private String domain;
    private String detail;

    public Error(String title, String message, String domain, String detail) {
        this.presentationData = PresentationData.of(title, message);
        this.domain = domain;
        this.detail = detail;
    }

    public static Error of(String title, String message, String domain, String detail) {
        return new Error(title, message, domain, detail);
    }

    public static Error unknownError(String title, String message, Exception e) {
        return new Error(title, message, "server", e.getLocalizedMessage());
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class PresentationData {
        private String title;
        private String message;
    }
}
