package com.raro.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Post.
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @Column(name = "publication_date")
    private Instant publicationDate;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "headline", length = 100, nullable = false)
    private String headline;

    @Size(min = 2, max = 1000)
    @Column(name = "lead", length = 1000)
    private String lead;

    @NotNull
    @Size(min = 3, max = 50000)
    @Lob
    @Column(name = "bodytext", nullable = false)
    private String bodytext;

    @Size(min = 2, max = 1000)
    @Column(name = "quote", length = 1000)
    private String quote;

    @Size(min = 2, max = 2000)
    @Column(name = "conclusion", length = 2000)
    private String conclusion;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comment> comments = new HashSet<>();

    @OneToOne(mappedBy = "topNews1")
    @JsonIgnore
    private Frontpageconfig topNews1;

    @OneToOne(mappedBy = "topNews2")
    @JsonIgnore
    private Frontpageconfig topNews2;

    @OneToOne(mappedBy = "topNews3")
    @JsonIgnore
    private Frontpageconfig topNews3;

    @OneToOne(mappedBy = "topNews4")
    @JsonIgnore
    private Frontpageconfig topNews4;

    @OneToOne(mappedBy = "topNews5")
    @JsonIgnore
    private Frontpageconfig topNews5;

    @OneToOne(mappedBy = "latestNews1")
    @JsonIgnore
    private Frontpageconfig latestNews1;

    @OneToOne(mappedBy = "latestNews2")
    @JsonIgnore
    private Frontpageconfig latestNews2;

    @OneToOne(mappedBy = "latestNews3")
    @JsonIgnore
    private Frontpageconfig latestNews3;

    @OneToOne(mappedBy = "latestNews4")
    @JsonIgnore
    private Frontpageconfig latestNews4;

    @OneToOne(mappedBy = "latestNews5")
    @JsonIgnore
    private Frontpageconfig latestNews5;

    @OneToOne(mappedBy = "breakingNews1")
    @JsonIgnore
    private Frontpageconfig breakingNews1;

    @OneToOne(mappedBy = "recentPosts1")
    @JsonIgnore
    private Frontpageconfig recentPosts1;

    @OneToOne(mappedBy = "recentPosts2")
    @JsonIgnore
    private Frontpageconfig recentPosts2;

    @OneToOne(mappedBy = "recentPosts3")
    @JsonIgnore
    private Frontpageconfig recentPosts3;

    @OneToOne(mappedBy = "recentPosts4")
    @JsonIgnore
    private Frontpageconfig recentPosts4;

    @OneToOne(mappedBy = "featuredArticles1")
    @JsonIgnore
    private Frontpageconfig featuredArticles1;

    @OneToOne(mappedBy = "featuredArticles2")
    @JsonIgnore
    private Frontpageconfig featuredArticles2;

    @OneToOne(mappedBy = "featuredArticles3")
    @JsonIgnore
    private Frontpageconfig featuredArticles3;

    @OneToOne(mappedBy = "featuredArticles4")
    @JsonIgnore
    private Frontpageconfig featuredArticles4;

    @OneToOne(mappedBy = "featuredArticles5")
    @JsonIgnore
    private Frontpageconfig featuredArticles5;

    @OneToOne(mappedBy = "featuredArticles6")
    @JsonIgnore
    private Frontpageconfig featuredArticles6;

    @OneToOne(mappedBy = "featuredArticles7")
    @JsonIgnore
    private Frontpageconfig featuredArticles7;

    @OneToOne(mappedBy = "featuredArticles8")
    @JsonIgnore
    private Frontpageconfig featuredArticles8;

    @OneToOne(mappedBy = "featuredArticles9")
    @JsonIgnore
    private Frontpageconfig featuredArticles9;

    @OneToOne(mappedBy = "featuredArticles10")
    @JsonIgnore
    private Frontpageconfig featuredArticles10;

    @OneToOne(mappedBy = "popularNews1")
    @JsonIgnore
    private Frontpageconfig popularNews1;

    @OneToOne(mappedBy = "popularNews2")
    @JsonIgnore
    private Frontpageconfig popularNews2;

    @OneToOne(mappedBy = "popularNews3")
    @JsonIgnore
    private Frontpageconfig popularNews3;

    @OneToOne(mappedBy = "popularNews4")
    @JsonIgnore
    private Frontpageconfig popularNews4;

    @OneToOne(mappedBy = "popularNews5")
    @JsonIgnore
    private Frontpageconfig popularNews5;

    @OneToOne(mappedBy = "popularNews6")
    @JsonIgnore
    private Frontpageconfig popularNews6;

    @OneToOne(mappedBy = "popularNews7")
    @JsonIgnore
    private Frontpageconfig popularNews7;

    @OneToOne(mappedBy = "popularNews8")
    @JsonIgnore
    private Frontpageconfig popularNews8;

    @OneToOne(mappedBy = "weeklyNews1")
    @JsonIgnore
    private Frontpageconfig weeklyNews1;

    @OneToOne(mappedBy = "weeklyNews2")
    @JsonIgnore
    private Frontpageconfig weeklyNews2;

    @OneToOne(mappedBy = "weeklyNews3")
    @JsonIgnore
    private Frontpageconfig weeklyNews3;

    @OneToOne(mappedBy = "weeklyNews4")
    @JsonIgnore
    private Frontpageconfig weeklyNews4;

    @OneToOne(mappedBy = "newsFeeds1")
    @JsonIgnore
    private Frontpageconfig newsFeeds1;

    @OneToOne(mappedBy = "newsFeeds2")
    @JsonIgnore
    private Frontpageconfig newsFeeds2;

    @OneToOne(mappedBy = "newsFeeds3")
    @JsonIgnore
    private Frontpageconfig newsFeeds3;

    @OneToOne(mappedBy = "newsFeeds4")
    @JsonIgnore
    private Frontpageconfig newsFeeds4;

    @OneToOne(mappedBy = "newsFeeds5")
    @JsonIgnore
    private Frontpageconfig newsFeeds5;

    @OneToOne(mappedBy = "newsFeeds6")
    @JsonIgnore
    private Frontpageconfig newsFeeds6;

    @ManyToOne(optional = false)
    @NotNull
    private Blog blog;

    @ManyToMany(mappedBy = "posts")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(mappedBy = "posts")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Topic> topics = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Post creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public Post publicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getHeadline() {
        return headline;
    }

    public Post headline(String headline) {
        this.headline = headline;
        return this;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLead() {
        return lead;
    }

    public Post lead(String lead) {
        this.lead = lead;
        return this;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getBodytext() {
        return bodytext;
    }

    public Post bodytext(String bodytext) {
        this.bodytext = bodytext;
        return this;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext;
    }

    public String getQuote() {
        return quote;
    }

    public Post quote(String quote) {
        this.quote = quote;
        return this;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getConclusion() {
        return conclusion;
    }

    public Post conclusion(String conclusion) {
        this.conclusion = conclusion;
        return this;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public byte[] getImage() {
        return image;
    }

    public Post image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Post imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Post comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Post addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
        return this;
    }

    public Post removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setPost(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Frontpageconfig getTopNews1() {
        return topNews1;
    }

    public Post topNews1(Frontpageconfig frontpageconfig) {
        this.topNews1 = frontpageconfig;
        return this;
    }

    public void setTopNews1(Frontpageconfig frontpageconfig) {
        this.topNews1 = frontpageconfig;
    }

    public Frontpageconfig getTopNews2() {
        return topNews2;
    }

    public Post topNews2(Frontpageconfig frontpageconfig) {
        this.topNews2 = frontpageconfig;
        return this;
    }

    public void setTopNews2(Frontpageconfig frontpageconfig) {
        this.topNews2 = frontpageconfig;
    }

    public Frontpageconfig getTopNews3() {
        return topNews3;
    }

    public Post topNews3(Frontpageconfig frontpageconfig) {
        this.topNews3 = frontpageconfig;
        return this;
    }

    public void setTopNews3(Frontpageconfig frontpageconfig) {
        this.topNews3 = frontpageconfig;
    }

    public Frontpageconfig getTopNews4() {
        return topNews4;
    }

    public Post topNews4(Frontpageconfig frontpageconfig) {
        this.topNews4 = frontpageconfig;
        return this;
    }

    public void setTopNews4(Frontpageconfig frontpageconfig) {
        this.topNews4 = frontpageconfig;
    }

    public Frontpageconfig getTopNews5() {
        return topNews5;
    }

    public Post topNews5(Frontpageconfig frontpageconfig) {
        this.topNews5 = frontpageconfig;
        return this;
    }

    public void setTopNews5(Frontpageconfig frontpageconfig) {
        this.topNews5 = frontpageconfig;
    }

    public Frontpageconfig getLatestNews1() {
        return latestNews1;
    }

    public Post latestNews1(Frontpageconfig frontpageconfig) {
        this.latestNews1 = frontpageconfig;
        return this;
    }

    public void setLatestNews1(Frontpageconfig frontpageconfig) {
        this.latestNews1 = frontpageconfig;
    }

    public Frontpageconfig getLatestNews2() {
        return latestNews2;
    }

    public Post latestNews2(Frontpageconfig frontpageconfig) {
        this.latestNews2 = frontpageconfig;
        return this;
    }

    public void setLatestNews2(Frontpageconfig frontpageconfig) {
        this.latestNews2 = frontpageconfig;
    }

    public Frontpageconfig getLatestNews3() {
        return latestNews3;
    }

    public Post latestNews3(Frontpageconfig frontpageconfig) {
        this.latestNews3 = frontpageconfig;
        return this;
    }

    public void setLatestNews3(Frontpageconfig frontpageconfig) {
        this.latestNews3 = frontpageconfig;
    }

    public Frontpageconfig getLatestNews4() {
        return latestNews4;
    }

    public Post latestNews4(Frontpageconfig frontpageconfig) {
        this.latestNews4 = frontpageconfig;
        return this;
    }

    public void setLatestNews4(Frontpageconfig frontpageconfig) {
        this.latestNews4 = frontpageconfig;
    }

    public Frontpageconfig getLatestNews5() {
        return latestNews5;
    }

    public Post latestNews5(Frontpageconfig frontpageconfig) {
        this.latestNews5 = frontpageconfig;
        return this;
    }

    public void setLatestNews5(Frontpageconfig frontpageconfig) {
        this.latestNews5 = frontpageconfig;
    }

    public Frontpageconfig getBreakingNews1() {
        return breakingNews1;
    }

    public Post breakingNews1(Frontpageconfig frontpageconfig) {
        this.breakingNews1 = frontpageconfig;
        return this;
    }

    public void setBreakingNews1(Frontpageconfig frontpageconfig) {
        this.breakingNews1 = frontpageconfig;
    }

    public Frontpageconfig getRecentPosts1() {
        return recentPosts1;
    }

    public Post recentPosts1(Frontpageconfig frontpageconfig) {
        this.recentPosts1 = frontpageconfig;
        return this;
    }

    public void setRecentPosts1(Frontpageconfig frontpageconfig) {
        this.recentPosts1 = frontpageconfig;
    }

    public Frontpageconfig getRecentPosts2() {
        return recentPosts2;
    }

    public Post recentPosts2(Frontpageconfig frontpageconfig) {
        this.recentPosts2 = frontpageconfig;
        return this;
    }

    public void setRecentPosts2(Frontpageconfig frontpageconfig) {
        this.recentPosts2 = frontpageconfig;
    }

    public Frontpageconfig getRecentPosts3() {
        return recentPosts3;
    }

    public Post recentPosts3(Frontpageconfig frontpageconfig) {
        this.recentPosts3 = frontpageconfig;
        return this;
    }

    public void setRecentPosts3(Frontpageconfig frontpageconfig) {
        this.recentPosts3 = frontpageconfig;
    }

    public Frontpageconfig getRecentPosts4() {
        return recentPosts4;
    }

    public Post recentPosts4(Frontpageconfig frontpageconfig) {
        this.recentPosts4 = frontpageconfig;
        return this;
    }

    public void setRecentPosts4(Frontpageconfig frontpageconfig) {
        this.recentPosts4 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles1() {
        return featuredArticles1;
    }

    public Post featuredArticles1(Frontpageconfig frontpageconfig) {
        this.featuredArticles1 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles1(Frontpageconfig frontpageconfig) {
        this.featuredArticles1 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles2() {
        return featuredArticles2;
    }

    public Post featuredArticles2(Frontpageconfig frontpageconfig) {
        this.featuredArticles2 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles2(Frontpageconfig frontpageconfig) {
        this.featuredArticles2 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles3() {
        return featuredArticles3;
    }

    public Post featuredArticles3(Frontpageconfig frontpageconfig) {
        this.featuredArticles3 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles3(Frontpageconfig frontpageconfig) {
        this.featuredArticles3 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles4() {
        return featuredArticles4;
    }

    public Post featuredArticles4(Frontpageconfig frontpageconfig) {
        this.featuredArticles4 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles4(Frontpageconfig frontpageconfig) {
        this.featuredArticles4 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles5() {
        return featuredArticles5;
    }

    public Post featuredArticles5(Frontpageconfig frontpageconfig) {
        this.featuredArticles5 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles5(Frontpageconfig frontpageconfig) {
        this.featuredArticles5 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles6() {
        return featuredArticles6;
    }

    public Post featuredArticles6(Frontpageconfig frontpageconfig) {
        this.featuredArticles6 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles6(Frontpageconfig frontpageconfig) {
        this.featuredArticles6 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles7() {
        return featuredArticles7;
    }

    public Post featuredArticles7(Frontpageconfig frontpageconfig) {
        this.featuredArticles7 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles7(Frontpageconfig frontpageconfig) {
        this.featuredArticles7 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles8() {
        return featuredArticles8;
    }

    public Post featuredArticles8(Frontpageconfig frontpageconfig) {
        this.featuredArticles8 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles8(Frontpageconfig frontpageconfig) {
        this.featuredArticles8 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles9() {
        return featuredArticles9;
    }

    public Post featuredArticles9(Frontpageconfig frontpageconfig) {
        this.featuredArticles9 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles9(Frontpageconfig frontpageconfig) {
        this.featuredArticles9 = frontpageconfig;
    }

    public Frontpageconfig getFeaturedArticles10() {
        return featuredArticles10;
    }

    public Post featuredArticles10(Frontpageconfig frontpageconfig) {
        this.featuredArticles10 = frontpageconfig;
        return this;
    }

    public void setFeaturedArticles10(Frontpageconfig frontpageconfig) {
        this.featuredArticles10 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews1() {
        return popularNews1;
    }

    public Post popularNews1(Frontpageconfig frontpageconfig) {
        this.popularNews1 = frontpageconfig;
        return this;
    }

    public void setPopularNews1(Frontpageconfig frontpageconfig) {
        this.popularNews1 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews2() {
        return popularNews2;
    }

    public Post popularNews2(Frontpageconfig frontpageconfig) {
        this.popularNews2 = frontpageconfig;
        return this;
    }

    public void setPopularNews2(Frontpageconfig frontpageconfig) {
        this.popularNews2 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews3() {
        return popularNews3;
    }

    public Post popularNews3(Frontpageconfig frontpageconfig) {
        this.popularNews3 = frontpageconfig;
        return this;
    }

    public void setPopularNews3(Frontpageconfig frontpageconfig) {
        this.popularNews3 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews4() {
        return popularNews4;
    }

    public Post popularNews4(Frontpageconfig frontpageconfig) {
        this.popularNews4 = frontpageconfig;
        return this;
    }

    public void setPopularNews4(Frontpageconfig frontpageconfig) {
        this.popularNews4 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews5() {
        return popularNews5;
    }

    public Post popularNews5(Frontpageconfig frontpageconfig) {
        this.popularNews5 = frontpageconfig;
        return this;
    }

    public void setPopularNews5(Frontpageconfig frontpageconfig) {
        this.popularNews5 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews6() {
        return popularNews6;
    }

    public Post popularNews6(Frontpageconfig frontpageconfig) {
        this.popularNews6 = frontpageconfig;
        return this;
    }

    public void setPopularNews6(Frontpageconfig frontpageconfig) {
        this.popularNews6 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews7() {
        return popularNews7;
    }

    public Post popularNews7(Frontpageconfig frontpageconfig) {
        this.popularNews7 = frontpageconfig;
        return this;
    }

    public void setPopularNews7(Frontpageconfig frontpageconfig) {
        this.popularNews7 = frontpageconfig;
    }

    public Frontpageconfig getPopularNews8() {
        return popularNews8;
    }

    public Post popularNews8(Frontpageconfig frontpageconfig) {
        this.popularNews8 = frontpageconfig;
        return this;
    }

    public void setPopularNews8(Frontpageconfig frontpageconfig) {
        this.popularNews8 = frontpageconfig;
    }

    public Frontpageconfig getWeeklyNews1() {
        return weeklyNews1;
    }

    public Post weeklyNews1(Frontpageconfig frontpageconfig) {
        this.weeklyNews1 = frontpageconfig;
        return this;
    }

    public void setWeeklyNews1(Frontpageconfig frontpageconfig) {
        this.weeklyNews1 = frontpageconfig;
    }

    public Frontpageconfig getWeeklyNews2() {
        return weeklyNews2;
    }

    public Post weeklyNews2(Frontpageconfig frontpageconfig) {
        this.weeklyNews2 = frontpageconfig;
        return this;
    }

    public void setWeeklyNews2(Frontpageconfig frontpageconfig) {
        this.weeklyNews2 = frontpageconfig;
    }

    public Frontpageconfig getWeeklyNews3() {
        return weeklyNews3;
    }

    public Post weeklyNews3(Frontpageconfig frontpageconfig) {
        this.weeklyNews3 = frontpageconfig;
        return this;
    }

    public void setWeeklyNews3(Frontpageconfig frontpageconfig) {
        this.weeklyNews3 = frontpageconfig;
    }

    public Frontpageconfig getWeeklyNews4() {
        return weeklyNews4;
    }

    public Post weeklyNews4(Frontpageconfig frontpageconfig) {
        this.weeklyNews4 = frontpageconfig;
        return this;
    }

    public void setWeeklyNews4(Frontpageconfig frontpageconfig) {
        this.weeklyNews4 = frontpageconfig;
    }

    public Frontpageconfig getNewsFeeds1() {
        return newsFeeds1;
    }

    public Post newsFeeds1(Frontpageconfig frontpageconfig) {
        this.newsFeeds1 = frontpageconfig;
        return this;
    }

    public void setNewsFeeds1(Frontpageconfig frontpageconfig) {
        this.newsFeeds1 = frontpageconfig;
    }

    public Frontpageconfig getNewsFeeds2() {
        return newsFeeds2;
    }

    public Post newsFeeds2(Frontpageconfig frontpageconfig) {
        this.newsFeeds2 = frontpageconfig;
        return this;
    }

    public void setNewsFeeds2(Frontpageconfig frontpageconfig) {
        this.newsFeeds2 = frontpageconfig;
    }

    public Frontpageconfig getNewsFeeds3() {
        return newsFeeds3;
    }

    public Post newsFeeds3(Frontpageconfig frontpageconfig) {
        this.newsFeeds3 = frontpageconfig;
        return this;
    }

    public void setNewsFeeds3(Frontpageconfig frontpageconfig) {
        this.newsFeeds3 = frontpageconfig;
    }

    public Frontpageconfig getNewsFeeds4() {
        return newsFeeds4;
    }

    public Post newsFeeds4(Frontpageconfig frontpageconfig) {
        this.newsFeeds4 = frontpageconfig;
        return this;
    }

    public void setNewsFeeds4(Frontpageconfig frontpageconfig) {
        this.newsFeeds4 = frontpageconfig;
    }

    public Frontpageconfig getNewsFeeds5() {
        return newsFeeds5;
    }

    public Post newsFeeds5(Frontpageconfig frontpageconfig) {
        this.newsFeeds5 = frontpageconfig;
        return this;
    }

    public void setNewsFeeds5(Frontpageconfig frontpageconfig) {
        this.newsFeeds5 = frontpageconfig;
    }

    public Frontpageconfig getNewsFeeds6() {
        return newsFeeds6;
    }

    public Post newsFeeds6(Frontpageconfig frontpageconfig) {
        this.newsFeeds6 = frontpageconfig;
        return this;
    }

    public void setNewsFeeds6(Frontpageconfig frontpageconfig) {
        this.newsFeeds6 = frontpageconfig;
    }

    public Blog getBlog() {
        return blog;
    }

    public Post blog(Blog blog) {
        this.blog = blog;
        return this;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Post tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Post addTag(Tag tag) {
        this.tags.add(tag);
        tag.getPosts().add(this);
        return this;
    }

    public Post removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getPosts().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public Post topics(Set<Topic> topics) {
        this.topics = topics;
        return this;
    }

    public Post addTopic(Topic topic) {
        this.topics.add(topic);
        topic.getPosts().add(this);
        return this;
    }

    public Post removeTopic(Topic topic) {
        this.topics.remove(topic);
        topic.getPosts().remove(this);
        return this;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        if (post.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", headline='" + getHeadline() + "'" +
            ", lead='" + getLead() + "'" +
            ", bodytext='" + getBodytext() + "'" +
            ", quote='" + getQuote() + "'" +
            ", conclusion='" + getConclusion() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}
