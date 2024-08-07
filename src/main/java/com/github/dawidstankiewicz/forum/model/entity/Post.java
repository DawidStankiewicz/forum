package com.github.dawidstankiewicz.forum.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Topic topic;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(length = 100000)
    private String content;

    private ContentType contentType;

    @Column(updatable = false, nullable = false)
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime modificationDate;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modificationDate = LocalDateTime.now();
    }

    public enum ContentType {
        TEXT,
        MARKDOWN,
    }
}

