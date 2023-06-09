package com.moham.coursemores.domain;

import com.moham.coursemores.domain.time.DeleteTimeEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends DeleteTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private int people;

    @NotNull
    @Column(length = 5000)
    private String content;

    @NotNull
    @Column
    private int likeCount;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 댓글 작성 유저

    @NotNull
    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course; // 해당 코스

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentImage> commentImageList; // 댓글의 사진 목록

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLikeList; // 댓글의 좋아요 목록

    @Builder
    public Comment(int people,
            String content,
            User user,
            Course course) {
        this.people = people;
        this.content = content;
        this.user = user;
        this.course = course;
        this.likeCount = 0;
    }

    public void update(String content, int people) {
        this.content = content;
        this.people = people;
    }

    public void delete() {
        this.deleteTime = LocalDateTime.now();
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

}