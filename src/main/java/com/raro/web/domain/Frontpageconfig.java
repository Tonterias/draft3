package com.raro.web.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Frontpageconfig.
 */
@Entity
@Table(name = "frontpageconfig")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "frontpageconfig")
public class Frontpageconfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @OneToOne
    @JoinColumn(unique = true)
    private Post topNews1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post topNews2;

    @OneToOne
    @JoinColumn(unique = true)
    private Post topNews3;

    @OneToOne
    @JoinColumn(unique = true)
    private Post topNews4;

    @OneToOne
    @JoinColumn(unique = true)
    private Post topNews5;

    @OneToOne
    @JoinColumn(unique = true)
    private Post latestNews1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post latestNews2;

    @OneToOne
    @JoinColumn(unique = true)
    private Post latestNews3;

    @OneToOne
    @JoinColumn(unique = true)
    private Post latestNews4;

    @OneToOne
    @JoinColumn(unique = true)
    private Post latestNews5;

    @OneToOne
    @JoinColumn(unique = true)
    private Post breakingNews1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post recentPosts1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post recentPosts2;

    @OneToOne
    @JoinColumn(unique = true)
    private Post recentPosts3;

    @OneToOne
    @JoinColumn(unique = true)
    private Post recentPosts4;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles2;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles3;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles4;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles5;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles6;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles7;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles8;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles9;

    @OneToOne
    @JoinColumn(unique = true)
    private Post featuredArticles10;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews2;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews3;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews4;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews5;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews6;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews7;

    @OneToOne
    @JoinColumn(unique = true)
    private Post popularNews8;

    @OneToOne
    @JoinColumn(unique = true)
    private Post weeklyNews1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post weeklyNews2;

    @OneToOne
    @JoinColumn(unique = true)
    private Post weeklyNews3;

    @OneToOne
    @JoinColumn(unique = true)
    private Post weeklyNews4;

    @OneToOne
    @JoinColumn(unique = true)
    private Post newsFeeds1;

    @OneToOne
    @JoinColumn(unique = true)
    private Post newsFeeds2;

    @OneToOne
    @JoinColumn(unique = true)
    private Post newsFeeds3;

    @OneToOne
    @JoinColumn(unique = true)
    private Post newsFeeds4;

    @OneToOne
    @JoinColumn(unique = true)
    private Post newsFeeds5;

    @OneToOne
    @JoinColumn(unique = true)
    private Post newsFeeds6;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink usefulLinks1;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink usefulLinks2;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink usefulLinks3;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink usefulLinks4;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink usefulLinks5;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink usefulLinks6;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink recentVideos1;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink recentVideos2;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink recentVideos3;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink recentVideos4;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink recentVideos5;

    @OneToOne
    @JoinColumn(unique = true)
    private Urllink recentVideos6;

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

    public Frontpageconfig creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Post getTopNews1() {
        return topNews1;
    }

    public Frontpageconfig topNews1(Post post) {
        this.topNews1 = post;
        return this;
    }

    public void setTopNews1(Post post) {
        this.topNews1 = post;
    }

    public Post getTopNews2() {
        return topNews2;
    }

    public Frontpageconfig topNews2(Post post) {
        this.topNews2 = post;
        return this;
    }

    public void setTopNews2(Post post) {
        this.topNews2 = post;
    }

    public Post getTopNews3() {
        return topNews3;
    }

    public Frontpageconfig topNews3(Post post) {
        this.topNews3 = post;
        return this;
    }

    public void setTopNews3(Post post) {
        this.topNews3 = post;
    }

    public Post getTopNews4() {
        return topNews4;
    }

    public Frontpageconfig topNews4(Post post) {
        this.topNews4 = post;
        return this;
    }

    public void setTopNews4(Post post) {
        this.topNews4 = post;
    }

    public Post getTopNews5() {
        return topNews5;
    }

    public Frontpageconfig topNews5(Post post) {
        this.topNews5 = post;
        return this;
    }

    public void setTopNews5(Post post) {
        this.topNews5 = post;
    }

    public Post getLatestNews1() {
        return latestNews1;
    }

    public Frontpageconfig latestNews1(Post post) {
        this.latestNews1 = post;
        return this;
    }

    public void setLatestNews1(Post post) {
        this.latestNews1 = post;
    }

    public Post getLatestNews2() {
        return latestNews2;
    }

    public Frontpageconfig latestNews2(Post post) {
        this.latestNews2 = post;
        return this;
    }

    public void setLatestNews2(Post post) {
        this.latestNews2 = post;
    }

    public Post getLatestNews3() {
        return latestNews3;
    }

    public Frontpageconfig latestNews3(Post post) {
        this.latestNews3 = post;
        return this;
    }

    public void setLatestNews3(Post post) {
        this.latestNews3 = post;
    }

    public Post getLatestNews4() {
        return latestNews4;
    }

    public Frontpageconfig latestNews4(Post post) {
        this.latestNews4 = post;
        return this;
    }

    public void setLatestNews4(Post post) {
        this.latestNews4 = post;
    }

    public Post getLatestNews5() {
        return latestNews5;
    }

    public Frontpageconfig latestNews5(Post post) {
        this.latestNews5 = post;
        return this;
    }

    public void setLatestNews5(Post post) {
        this.latestNews5 = post;
    }

    public Post getBreakingNews1() {
        return breakingNews1;
    }

    public Frontpageconfig breakingNews1(Post post) {
        this.breakingNews1 = post;
        return this;
    }

    public void setBreakingNews1(Post post) {
        this.breakingNews1 = post;
    }

    public Post getRecentPosts1() {
        return recentPosts1;
    }

    public Frontpageconfig recentPosts1(Post post) {
        this.recentPosts1 = post;
        return this;
    }

    public void setRecentPosts1(Post post) {
        this.recentPosts1 = post;
    }

    public Post getRecentPosts2() {
        return recentPosts2;
    }

    public Frontpageconfig recentPosts2(Post post) {
        this.recentPosts2 = post;
        return this;
    }

    public void setRecentPosts2(Post post) {
        this.recentPosts2 = post;
    }

    public Post getRecentPosts3() {
        return recentPosts3;
    }

    public Frontpageconfig recentPosts3(Post post) {
        this.recentPosts3 = post;
        return this;
    }

    public void setRecentPosts3(Post post) {
        this.recentPosts3 = post;
    }

    public Post getRecentPosts4() {
        return recentPosts4;
    }

    public Frontpageconfig recentPosts4(Post post) {
        this.recentPosts4 = post;
        return this;
    }

    public void setRecentPosts4(Post post) {
        this.recentPosts4 = post;
    }

    public Post getFeaturedArticles1() {
        return featuredArticles1;
    }

    public Frontpageconfig featuredArticles1(Post post) {
        this.featuredArticles1 = post;
        return this;
    }

    public void setFeaturedArticles1(Post post) {
        this.featuredArticles1 = post;
    }

    public Post getFeaturedArticles2() {
        return featuredArticles2;
    }

    public Frontpageconfig featuredArticles2(Post post) {
        this.featuredArticles2 = post;
        return this;
    }

    public void setFeaturedArticles2(Post post) {
        this.featuredArticles2 = post;
    }

    public Post getFeaturedArticles3() {
        return featuredArticles3;
    }

    public Frontpageconfig featuredArticles3(Post post) {
        this.featuredArticles3 = post;
        return this;
    }

    public void setFeaturedArticles3(Post post) {
        this.featuredArticles3 = post;
    }

    public Post getFeaturedArticles4() {
        return featuredArticles4;
    }

    public Frontpageconfig featuredArticles4(Post post) {
        this.featuredArticles4 = post;
        return this;
    }

    public void setFeaturedArticles4(Post post) {
        this.featuredArticles4 = post;
    }

    public Post getFeaturedArticles5() {
        return featuredArticles5;
    }

    public Frontpageconfig featuredArticles5(Post post) {
        this.featuredArticles5 = post;
        return this;
    }

    public void setFeaturedArticles5(Post post) {
        this.featuredArticles5 = post;
    }

    public Post getFeaturedArticles6() {
        return featuredArticles6;
    }

    public Frontpageconfig featuredArticles6(Post post) {
        this.featuredArticles6 = post;
        return this;
    }

    public void setFeaturedArticles6(Post post) {
        this.featuredArticles6 = post;
    }

    public Post getFeaturedArticles7() {
        return featuredArticles7;
    }

    public Frontpageconfig featuredArticles7(Post post) {
        this.featuredArticles7 = post;
        return this;
    }

    public void setFeaturedArticles7(Post post) {
        this.featuredArticles7 = post;
    }

    public Post getFeaturedArticles8() {
        return featuredArticles8;
    }

    public Frontpageconfig featuredArticles8(Post post) {
        this.featuredArticles8 = post;
        return this;
    }

    public void setFeaturedArticles8(Post post) {
        this.featuredArticles8 = post;
    }

    public Post getFeaturedArticles9() {
        return featuredArticles9;
    }

    public Frontpageconfig featuredArticles9(Post post) {
        this.featuredArticles9 = post;
        return this;
    }

    public void setFeaturedArticles9(Post post) {
        this.featuredArticles9 = post;
    }

    public Post getFeaturedArticles10() {
        return featuredArticles10;
    }

    public Frontpageconfig featuredArticles10(Post post) {
        this.featuredArticles10 = post;
        return this;
    }

    public void setFeaturedArticles10(Post post) {
        this.featuredArticles10 = post;
    }

    public Post getPopularNews1() {
        return popularNews1;
    }

    public Frontpageconfig popularNews1(Post post) {
        this.popularNews1 = post;
        return this;
    }

    public void setPopularNews1(Post post) {
        this.popularNews1 = post;
    }

    public Post getPopularNews2() {
        return popularNews2;
    }

    public Frontpageconfig popularNews2(Post post) {
        this.popularNews2 = post;
        return this;
    }

    public void setPopularNews2(Post post) {
        this.popularNews2 = post;
    }

    public Post getPopularNews3() {
        return popularNews3;
    }

    public Frontpageconfig popularNews3(Post post) {
        this.popularNews3 = post;
        return this;
    }

    public void setPopularNews3(Post post) {
        this.popularNews3 = post;
    }

    public Post getPopularNews4() {
        return popularNews4;
    }

    public Frontpageconfig popularNews4(Post post) {
        this.popularNews4 = post;
        return this;
    }

    public void setPopularNews4(Post post) {
        this.popularNews4 = post;
    }

    public Post getPopularNews5() {
        return popularNews5;
    }

    public Frontpageconfig popularNews5(Post post) {
        this.popularNews5 = post;
        return this;
    }

    public void setPopularNews5(Post post) {
        this.popularNews5 = post;
    }

    public Post getPopularNews6() {
        return popularNews6;
    }

    public Frontpageconfig popularNews6(Post post) {
        this.popularNews6 = post;
        return this;
    }

    public void setPopularNews6(Post post) {
        this.popularNews6 = post;
    }

    public Post getPopularNews7() {
        return popularNews7;
    }

    public Frontpageconfig popularNews7(Post post) {
        this.popularNews7 = post;
        return this;
    }

    public void setPopularNews7(Post post) {
        this.popularNews7 = post;
    }

    public Post getPopularNews8() {
        return popularNews8;
    }

    public Frontpageconfig popularNews8(Post post) {
        this.popularNews8 = post;
        return this;
    }

    public void setPopularNews8(Post post) {
        this.popularNews8 = post;
    }

    public Post getWeeklyNews1() {
        return weeklyNews1;
    }

    public Frontpageconfig weeklyNews1(Post post) {
        this.weeklyNews1 = post;
        return this;
    }

    public void setWeeklyNews1(Post post) {
        this.weeklyNews1 = post;
    }

    public Post getWeeklyNews2() {
        return weeklyNews2;
    }

    public Frontpageconfig weeklyNews2(Post post) {
        this.weeklyNews2 = post;
        return this;
    }

    public void setWeeklyNews2(Post post) {
        this.weeklyNews2 = post;
    }

    public Post getWeeklyNews3() {
        return weeklyNews3;
    }

    public Frontpageconfig weeklyNews3(Post post) {
        this.weeklyNews3 = post;
        return this;
    }

    public void setWeeklyNews3(Post post) {
        this.weeklyNews3 = post;
    }

    public Post getWeeklyNews4() {
        return weeklyNews4;
    }

    public Frontpageconfig weeklyNews4(Post post) {
        this.weeklyNews4 = post;
        return this;
    }

    public void setWeeklyNews4(Post post) {
        this.weeklyNews4 = post;
    }

    public Post getNewsFeeds1() {
        return newsFeeds1;
    }

    public Frontpageconfig newsFeeds1(Post post) {
        this.newsFeeds1 = post;
        return this;
    }

    public void setNewsFeeds1(Post post) {
        this.newsFeeds1 = post;
    }

    public Post getNewsFeeds2() {
        return newsFeeds2;
    }

    public Frontpageconfig newsFeeds2(Post post) {
        this.newsFeeds2 = post;
        return this;
    }

    public void setNewsFeeds2(Post post) {
        this.newsFeeds2 = post;
    }

    public Post getNewsFeeds3() {
        return newsFeeds3;
    }

    public Frontpageconfig newsFeeds3(Post post) {
        this.newsFeeds3 = post;
        return this;
    }

    public void setNewsFeeds3(Post post) {
        this.newsFeeds3 = post;
    }

    public Post getNewsFeeds4() {
        return newsFeeds4;
    }

    public Frontpageconfig newsFeeds4(Post post) {
        this.newsFeeds4 = post;
        return this;
    }

    public void setNewsFeeds4(Post post) {
        this.newsFeeds4 = post;
    }

    public Post getNewsFeeds5() {
        return newsFeeds5;
    }

    public Frontpageconfig newsFeeds5(Post post) {
        this.newsFeeds5 = post;
        return this;
    }

    public void setNewsFeeds5(Post post) {
        this.newsFeeds5 = post;
    }

    public Post getNewsFeeds6() {
        return newsFeeds6;
    }

    public Frontpageconfig newsFeeds6(Post post) {
        this.newsFeeds6 = post;
        return this;
    }

    public void setNewsFeeds6(Post post) {
        this.newsFeeds6 = post;
    }

    public Urllink getUsefulLinks1() {
        return usefulLinks1;
    }

    public Frontpageconfig usefulLinks1(Urllink urllink) {
        this.usefulLinks1 = urllink;
        return this;
    }

    public void setUsefulLinks1(Urllink urllink) {
        this.usefulLinks1 = urllink;
    }

    public Urllink getUsefulLinks2() {
        return usefulLinks2;
    }

    public Frontpageconfig usefulLinks2(Urllink urllink) {
        this.usefulLinks2 = urllink;
        return this;
    }

    public void setUsefulLinks2(Urllink urllink) {
        this.usefulLinks2 = urllink;
    }

    public Urllink getUsefulLinks3() {
        return usefulLinks3;
    }

    public Frontpageconfig usefulLinks3(Urllink urllink) {
        this.usefulLinks3 = urllink;
        return this;
    }

    public void setUsefulLinks3(Urllink urllink) {
        this.usefulLinks3 = urllink;
    }

    public Urllink getUsefulLinks4() {
        return usefulLinks4;
    }

    public Frontpageconfig usefulLinks4(Urllink urllink) {
        this.usefulLinks4 = urllink;
        return this;
    }

    public void setUsefulLinks4(Urllink urllink) {
        this.usefulLinks4 = urllink;
    }

    public Urllink getUsefulLinks5() {
        return usefulLinks5;
    }

    public Frontpageconfig usefulLinks5(Urllink urllink) {
        this.usefulLinks5 = urllink;
        return this;
    }

    public void setUsefulLinks5(Urllink urllink) {
        this.usefulLinks5 = urllink;
    }

    public Urllink getUsefulLinks6() {
        return usefulLinks6;
    }

    public Frontpageconfig usefulLinks6(Urllink urllink) {
        this.usefulLinks6 = urllink;
        return this;
    }

    public void setUsefulLinks6(Urllink urllink) {
        this.usefulLinks6 = urllink;
    }

    public Urllink getRecentVideos1() {
        return recentVideos1;
    }

    public Frontpageconfig recentVideos1(Urllink urllink) {
        this.recentVideos1 = urllink;
        return this;
    }

    public void setRecentVideos1(Urllink urllink) {
        this.recentVideos1 = urllink;
    }

    public Urllink getRecentVideos2() {
        return recentVideos2;
    }

    public Frontpageconfig recentVideos2(Urllink urllink) {
        this.recentVideos2 = urllink;
        return this;
    }

    public void setRecentVideos2(Urllink urllink) {
        this.recentVideos2 = urllink;
    }

    public Urllink getRecentVideos3() {
        return recentVideos3;
    }

    public Frontpageconfig recentVideos3(Urllink urllink) {
        this.recentVideos3 = urllink;
        return this;
    }

    public void setRecentVideos3(Urllink urllink) {
        this.recentVideos3 = urllink;
    }

    public Urllink getRecentVideos4() {
        return recentVideos4;
    }

    public Frontpageconfig recentVideos4(Urllink urllink) {
        this.recentVideos4 = urllink;
        return this;
    }

    public void setRecentVideos4(Urllink urllink) {
        this.recentVideos4 = urllink;
    }

    public Urllink getRecentVideos5() {
        return recentVideos5;
    }

    public Frontpageconfig recentVideos5(Urllink urllink) {
        this.recentVideos5 = urllink;
        return this;
    }

    public void setRecentVideos5(Urllink urllink) {
        this.recentVideos5 = urllink;
    }

    public Urllink getRecentVideos6() {
        return recentVideos6;
    }

    public Frontpageconfig recentVideos6(Urllink urllink) {
        this.recentVideos6 = urllink;
        return this;
    }

    public void setRecentVideos6(Urllink urllink) {
        this.recentVideos6 = urllink;
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
        Frontpageconfig frontpageconfig = (Frontpageconfig) o;
        if (frontpageconfig.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), frontpageconfig.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Frontpageconfig{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
