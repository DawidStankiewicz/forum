package com.github.dawidstankiewicz.forum.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "topics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Section section;

    @Column(length = 50)
    private String title;

    @Column
    private int views;

    @Column(updatable = false, nullable = false)
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime lastUpdateDate;

    @Column
    private boolean closed;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
    }

}
