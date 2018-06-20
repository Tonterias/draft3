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

import com.raro.web.domain.enumeration.ProposalType;

/**
 * A Proposal.
 */
@Entity
@Table(name = "proposal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "proposal")
public class Proposal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @Column(name = "release_date")
    private Instant releaseDate;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "functionality", length = 100, nullable = false)
    private String functionality;

    @Enumerated(EnumType.STRING)
    @Column(name = "proposal_type")
    private ProposalType proposalType;

    @ManyToOne(optional = false)
    @NotNull
    private Party party;

    @ManyToOne(optional = false)
    @NotNull
    private Post post;

    @OneToMany(mappedBy = "proposal")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Vote> votes = new HashSet<>();

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

    public Proposal creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public Proposal releaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFunctionality() {
        return functionality;
    }

    public Proposal functionality(String functionality) {
        this.functionality = functionality;
        return this;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public ProposalType getProposalType() {
        return proposalType;
    }

    public Proposal proposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
        return this;
    }

    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }

    public Party getParty() {
        return party;
    }

    public Proposal party(Party party) {
        this.party = party;
        return this;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Post getPost() {
        return post;
    }

    public Proposal post(Post post) {
        this.post = post;
        return this;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public Proposal votes(Set<Vote> votes) {
        this.votes = votes;
        return this;
    }

    public Proposal addVote(Vote vote) {
        this.votes.add(vote);
        vote.setProposal(this);
        return this;
    }

    public Proposal removeVote(Vote vote) {
        this.votes.remove(vote);
        vote.setProposal(null);
        return this;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
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
        Proposal proposal = (Proposal) o;
        if (proposal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proposal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Proposal{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", functionality='" + getFunctionality() + "'" +
            ", proposalType='" + getProposalType() + "'" +
            "}";
    }
}
