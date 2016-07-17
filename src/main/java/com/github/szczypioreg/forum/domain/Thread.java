/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "threads")
public class Thread implements Serializable {
    
    private static final long serialVersionUID = -1722083052479276312L;

    @Id
    @Column(name = "idthread")
    private int idThread;
    
    @Column(name = "title")
    private String title;
    
    @OneToMany
    @JoinColumn(name = "idsection")
    private Section section;
    
    @Column(name = "views")
    private int views;
    
    @OneToOne
    @JoinColumn(name = "idfirstpost")
    private Post firstPost;
    
    public Thread() {}
    
    public Thread(int idThread, String title, Section section, int views, Post firstPost) {
        super();
        this.idThread = idThread;
        this.title = title;
        this.section = section;
        this.views = views;
        this.firstPost = firstPost;
    }
    
    public int getIdThread() {
        return idThread;
    }
    
    public void setIdThread(int idThread) {
        this.idThread = idThread;
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstPost == null) ? 0 : firstPost.hashCode());
        result = prime * result + idThread;
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
        Thread other = (Thread) obj;
        if (firstPost == null) {
            if (other.firstPost != null)
                return false;
        } else if (!firstPost.equals(other.firstPost))
            return false;
        if (idThread != other.idThread)
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
        return "Thread [idThread=" + idThread + ", title=" + title + ", section=" + section
                + ", views=" + views + ", firstPost=" + firstPost + "]";
    }
    
}
