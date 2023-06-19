package com.pashtetpashtetovv.canUBuy.domain.model.classId;

import com.pashtetpashtetovv.canUBuy.domain.model.User;

import java.util.Objects;

@Deprecated
public class FriendRequestId {

    private User requester;

    private User target;

    public FriendRequestId() {
    }

    public FriendRequestId(User requester, User target) {
        this.requester = requester;
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendRequestId that = (FriendRequestId) o;

        if (!Objects.equals(requester, that.requester)) return false;
        return Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        int result = requester != null ? requester.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }
}
