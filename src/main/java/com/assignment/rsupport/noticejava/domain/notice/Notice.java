package com.assignment.rsupport.noticejava.domain.notice;

import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.support.domain.AbstractEntity;

import javax.persistence.*;

@Entity
public class Notice extends AbstractEntity {
    @Column
    private String title;

    @Column
    @Lob
    private String content;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "writer_id")
    private User writer;

    public Notice() {
    }

    public Notice(String title, String content, User writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
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

    public User getWriter() {
        return writer;
    }

    public void writeBy(User writer) {
        this.writer = writer;
    }

    // domain
    public boolean isWriter(User loginedUser) {
        return this.writer.equals(loginedUser);
    }

    public void update(Notice updateNotice) {
        this.title = updateNotice.title;
        this.content = updateNotice.content;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer=" + writer +
                '}';
    }
}
