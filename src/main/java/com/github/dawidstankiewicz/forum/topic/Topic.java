/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.dawidstankiewicz.forum.topic;

import com.github.dawidstankiewicz.forum.section.Section;
import com.github.dawidstankiewicz.forum.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@Entity
public class Topic implements Serializable {

    private static final long serialVersionUID = -1722083052479276312L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Section section;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private int views;

    @Column(updatable = false, nullable = false)
    private Date creationDate;

    @Column
    private Date lastUpdateDate;

    @Column
    private boolean closed;

    public Topic() {
    }

    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.lastUpdateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (closed ? 1231 : 1237);
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
        result = prime * result + ((section == null) ? 0 : section.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + views;
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
        Topic other = (Topic) obj;
        if (closed != other.closed) {
            return false;
        }
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
        if (section == null) {
            if (other.section != null) {
                return false;
            }
        } else if (!section.equals(other.section)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        if (views != other.views) {
            return false;
        }
        return true;
    }
}
