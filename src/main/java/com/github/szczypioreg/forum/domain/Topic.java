/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic implements Serializable {
    
    private static final long serialVersionUID = -1722083052479276312L;
    
    @Id
    @Column(name = "idtopic")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTopic;
    
    @ManyToOne
    @JoinColumn(name = "idsection")
    private Section section;
    
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "date")
    private Date date;
    
    @Column(name = "views")
    private int views;
    
    @OneToMany(mappedBy = "topic")
    private List<Post> posts;
    
    public Topic() {}
    
    public Topic(Section section, User user, String title, String content, Date date, int views,
            List<Post> posts) {
        super();
        this.section = section;
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = date;
        this.views = views;
        this.posts = posts;
    }
    
    public int getIdTopic() {
        return idTopic;
    }
    
    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }
    
    public Section getSection() {
        return section;
    }
    
    public void setSection(Section section) {
        this.section = section;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getViews() {
        return views;
    }
    
    public void setViews(int views) {
        this.views = views;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + idTopic;
        result = prime * result + ((posts == null) ? 0 : posts.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        if (idTopic != other.idTopic)
            return false;
        if (posts == null) {
            if (other.posts != null)
                return false;
        } else if (!posts.equals(other.posts))
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
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (views != other.views)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Topic [idTopic=" + idTopic + ", section=" + section + ", user=" + user + ", title="
                + title + ", content=" + content + ", date=" + date + ", views=" + views
                + ", posts=" + posts + "]";
    }
    
}
