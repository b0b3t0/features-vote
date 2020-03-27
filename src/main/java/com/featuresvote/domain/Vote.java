package com.featuresvote.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Vote {


    private VoteId pk;
    private Boolean upvote;
    public Vote() {

    }

    public Boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(Boolean upvote) {
        this.upvote = upvote;
    }

    @EmbeddedId
    public VoteId getPk() {
        return pk;
    }

    public void setPk(VoteId pk) {
        this.pk = pk;
    }
}
