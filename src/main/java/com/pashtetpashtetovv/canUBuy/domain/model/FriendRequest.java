package com.pashtetpashtetovv.canUBuy.domain.model;

import com.pashtetpashtetovv.canUBuy.domain.model.classId.FriendRequestId;
import jakarta.persistence.*;

@Entity
public class FriendRequest {
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_request_gen")
    @SequenceGenerator(name = "friend_request_gen", sequenceName = "friend_request_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID")
    private User requester;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TARGET_ID")
    private User target;

    private boolean active;

    public Long getId() {
        return id;
    }

    public FriendRequest() {
    }

    public FriendRequest(User requester, User target) {
        this.requester = requester;
        this.target = target;
        this.active = true;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendRequest that = (FriendRequest) o;

        if (!getRequester().equals(that.getRequester())) return false;
        return getTarget().equals(that.getTarget());
    }

    @Override
    public int hashCode() {
        int result = getRequester().hashCode();
        result = 31 * result + getTarget().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "requester=" + requester.getUsername() +
                ", target=" + target.getUsername() +
                ", active=" + active +
                '}';
    }
}
