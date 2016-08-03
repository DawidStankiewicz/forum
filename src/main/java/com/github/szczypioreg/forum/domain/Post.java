/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "posts")
public class Post implements Serializable {
    
    private static final long serialVersionUID = 4235393151425571253L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "id_topic")
    private Topic topic;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "creation_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @Column(name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    public Post() {}
    
    public Post(Topic topic,
                User user,
                String content,
                Date creationDate,
                Date lastUpdateDate) {
        this.topic = topic;
        this.user = user;
        this.content = content;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int idPost) {
        this.id = idPost;
    }
    
    public Topic getTopic() {
        return topic;
    }
    
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
        result = prime * result + ((topic == null) ? 0 : topic.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Post other = (Post) obj;
        if (content == null) {
            if (other.content != null) {
                return false;
            }
        } else if (!content.equals(other.content)) {
            return false;
        }
        if (creationDate == null) {
            if (other.creationDate != null) {
                return false;
            }
        } else if (!creationDate.equals(other.creationDate)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (lastUpdateDate == null) {
            if (other.lastUpdateDate != null) {
                return false;
            }
        } else if (!lastUpdateDate.equals(other.lastUpdateDate)) {
            return false;
        }
        if (topic == null) {
            if (other.topic != null) {
                return false;
            }
        } else if (!topic.equals(other.topic)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Post [id=" + id + ", topic=" + topic + ", user=" + user + ", content="
                + content + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate
                + "]";
    }
    
}
