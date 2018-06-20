package com.raro.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Urllink.
 */
@Entity
@Table(name = "urllink")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "urllink")
public class Urllink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "link_text", nullable = false)
    private String linkText;

    @NotNull
    @Column(name = "link_url", nullable = false)
    private String linkURL;

    @OneToOne(mappedBy = "usefulLinks1")
    @JsonIgnore
    private Frontpageconfig usefulLinks1;

    @OneToOne(mappedBy = "usefulLinks2")
    @JsonIgnore
    private Frontpageconfig usefulLinks2;

    @OneToOne(mappedBy = "usefulLinks3")
    @JsonIgnore
    private Frontpageconfig usefulLinks3;

    @OneToOne(mappedBy = "usefulLinks4")
    @JsonIgnore
    private Frontpageconfig usefulLinks4;

    @OneToOne(mappedBy = "usefulLinks5")
    @JsonIgnore
    private Frontpageconfig usefulLinks5;

    @OneToOne(mappedBy = "usefulLinks6")
    @JsonIgnore
    private Frontpageconfig usefulLinks6;

    @OneToOne(mappedBy = "recentVideos1")
    @JsonIgnore
    private Frontpageconfig recentVideos1;

    @OneToOne(mappedBy = "recentVideos2")
    @JsonIgnore
    private Frontpageconfig recentVideos2;

    @OneToOne(mappedBy = "recentVideos3")
    @JsonIgnore
    private Frontpageconfig recentVideos3;

    @OneToOne(mappedBy = "recentVideos4")
    @JsonIgnore
    private Frontpageconfig recentVideos4;

    @OneToOne(mappedBy = "recentVideos5")
    @JsonIgnore
    private Frontpageconfig recentVideos5;

    @OneToOne(mappedBy = "recentVideos6")
    @JsonIgnore
    private Frontpageconfig recentVideos6;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkText() {
        return linkText;
    }

    public Urllink linkText(String linkText) {
        this.linkText = linkText;
        return this;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public Urllink linkURL(String linkURL) {
        this.linkURL = linkURL;
        return this;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public Frontpageconfig getUsefulLinks1() {
        return usefulLinks1;
    }

    public Urllink usefulLinks1(Frontpageconfig frontpageconfig) {
        this.usefulLinks1 = frontpageconfig;
        return this;
    }

    public void setUsefulLinks1(Frontpageconfig frontpageconfig) {
        this.usefulLinks1 = frontpageconfig;
    }

    public Frontpageconfig getUsefulLinks2() {
        return usefulLinks2;
    }

    public Urllink usefulLinks2(Frontpageconfig frontpageconfig) {
        this.usefulLinks2 = frontpageconfig;
        return this;
    }

    public void setUsefulLinks2(Frontpageconfig frontpageconfig) {
        this.usefulLinks2 = frontpageconfig;
    }

    public Frontpageconfig getUsefulLinks3() {
        return usefulLinks3;
    }

    public Urllink usefulLinks3(Frontpageconfig frontpageconfig) {
        this.usefulLinks3 = frontpageconfig;
        return this;
    }

    public void setUsefulLinks3(Frontpageconfig frontpageconfig) {
        this.usefulLinks3 = frontpageconfig;
    }

    public Frontpageconfig getUsefulLinks4() {
        return usefulLinks4;
    }

    public Urllink usefulLinks4(Frontpageconfig frontpageconfig) {
        this.usefulLinks4 = frontpageconfig;
        return this;
    }

    public void setUsefulLinks4(Frontpageconfig frontpageconfig) {
        this.usefulLinks4 = frontpageconfig;
    }

    public Frontpageconfig getUsefulLinks5() {
        return usefulLinks5;
    }

    public Urllink usefulLinks5(Frontpageconfig frontpageconfig) {
        this.usefulLinks5 = frontpageconfig;
        return this;
    }

    public void setUsefulLinks5(Frontpageconfig frontpageconfig) {
        this.usefulLinks5 = frontpageconfig;
    }

    public Frontpageconfig getUsefulLinks6() {
        return usefulLinks6;
    }

    public Urllink usefulLinks6(Frontpageconfig frontpageconfig) {
        this.usefulLinks6 = frontpageconfig;
        return this;
    }

    public void setUsefulLinks6(Frontpageconfig frontpageconfig) {
        this.usefulLinks6 = frontpageconfig;
    }

    public Frontpageconfig getRecentVideos1() {
        return recentVideos1;
    }

    public Urllink recentVideos1(Frontpageconfig frontpageconfig) {
        this.recentVideos1 = frontpageconfig;
        return this;
    }

    public void setRecentVideos1(Frontpageconfig frontpageconfig) {
        this.recentVideos1 = frontpageconfig;
    }

    public Frontpageconfig getRecentVideos2() {
        return recentVideos2;
    }

    public Urllink recentVideos2(Frontpageconfig frontpageconfig) {
        this.recentVideos2 = frontpageconfig;
        return this;
    }

    public void setRecentVideos2(Frontpageconfig frontpageconfig) {
        this.recentVideos2 = frontpageconfig;
    }

    public Frontpageconfig getRecentVideos3() {
        return recentVideos3;
    }

    public Urllink recentVideos3(Frontpageconfig frontpageconfig) {
        this.recentVideos3 = frontpageconfig;
        return this;
    }

    public void setRecentVideos3(Frontpageconfig frontpageconfig) {
        this.recentVideos3 = frontpageconfig;
    }

    public Frontpageconfig getRecentVideos4() {
        return recentVideos4;
    }

    public Urllink recentVideos4(Frontpageconfig frontpageconfig) {
        this.recentVideos4 = frontpageconfig;
        return this;
    }

    public void setRecentVideos4(Frontpageconfig frontpageconfig) {
        this.recentVideos4 = frontpageconfig;
    }

    public Frontpageconfig getRecentVideos5() {
        return recentVideos5;
    }

    public Urllink recentVideos5(Frontpageconfig frontpageconfig) {
        this.recentVideos5 = frontpageconfig;
        return this;
    }

    public void setRecentVideos5(Frontpageconfig frontpageconfig) {
        this.recentVideos5 = frontpageconfig;
    }

    public Frontpageconfig getRecentVideos6() {
        return recentVideos6;
    }

    public Urllink recentVideos6(Frontpageconfig frontpageconfig) {
        this.recentVideos6 = frontpageconfig;
        return this;
    }

    public void setRecentVideos6(Frontpageconfig frontpageconfig) {
        this.recentVideos6 = frontpageconfig;
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
        Urllink urllink = (Urllink) o;
        if (urllink.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), urllink.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Urllink{" +
            "id=" + getId() +
            ", linkText='" + getLinkText() + "'" +
            ", linkURL='" + getLinkURL() + "'" +
            "}";
    }
}
