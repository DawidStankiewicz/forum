/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic implements Serializable {
    
    private static final long serialVersionUID = -1722083052479276312L;
    
    @Id
    @Column(name = "idtopic")
    private int idTopic;
    
    @Column(name = "title")
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "idsection")
    private Section section;
    
    @Column(name = "views")
    private int views;
    
    @OneToOne
    @JoinColumn(name = "idfirstpost")
    private Post firstPost;
    
    public Topic() {}
    
    public Topic(int idTopic, String title, Section section, int views, Post firstPost) {
        super();
        this.idTopic = idTopic;
        this.title = title;
        this.section = section;
        this.views = views;
        this.firstPost = firstPost;
    }
    
    public int getIdTopic() {
        return idTopic;
    }
    
    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Section getSection() {
        return section;
    }
    
    public void setSection(Section section) {
        this.section = section;
    }
    
    public int getViews() {
        return views;
    }
    
    public void setViews(int views) {
        this.views = views;
    }
    
    public Post getFirstPost() {
        return firstPost;
    }
    
    public void setFirstPost(Post firstPost) {
        this.firstPost = firstPost;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstPost == null) ? 0 : firstPost.hashCode());
        result = prime * result + idTopic;
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + views;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topic other = (Topic) obj;
        if (firstPost == null) {
            if (other.firstPost != null)
                return false;
        } else if (!firstPost.equals(other.firstPost))
            return false;
        if (idTopic != other.idTopic)
            return false;
        if (section == null) {
            if (other.section != null)
                return false;
        } else if (!section.equals(other.section))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (views != other.views)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Topic [idTopic=" + idTopic + ", title=" + title + ", section=" + section
                + ", views=" + views + ", firstPost=" + firstPost + "]";
    }
    
}
