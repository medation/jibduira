package com.devit.model;

import java.time.LocalDateTime;

public class Message {

    private String contenu;
    private LocalDateTime localDateTime;

    public Message(String contenu) {
        this.contenu = contenu;
        this.localDateTime = LocalDateTime.now();
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
