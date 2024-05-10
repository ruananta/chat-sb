package ru.ananta.chatsb;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;
    @Column(name = "author")
    private String author;

    public Message() {
    }

    public Message(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", text=" + text + ", author=" + author + "]";
    }
}
