/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post implements Serializable {
    
    private static final long serialVersionUID = 4235393151425571253L;

    @Id
    @Column(name = "idpost")
    private int idPost;
    
    @Column(name = "date")
    private Date date;
    
    @Column(name = "content")
    private String content;
    
    @OneToMany
    @JoinColumn(name = "iduser")
    private User user;
    
    @OneToMany
    @JoinColumn(name = "idthread")
    private Thread thread;
    
    public Post() {}
    
    public Post(int idPost, Date date, String content, User user, Thread thread) {
        super();
        this.idPost = idPost;
        this.date = date;
        this.content = content;
        this.user = user;
        this.thread = thread;
    }
    
    public int getIdPost() {
        return idPost;
    }
    
    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Thread getThread() {
        return thread;
    }
    
    public void setThread(Thread thread) {
        this.thread = thread;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + idPost;
        result = prime * result + ((thread == null) ? 0 : thread.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Post other = (Post) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (idPost != other.idPost)
            return false;
        if (thread == null) {
            if (other.thread != null)
                return false;
        } else if (!thread.equals(other.thread))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Post [idPost=" + idPost + ", date=" + date + ", content=" + content + ", user="
                + user + ", thread=" + thread + "]";
    }
    
}
