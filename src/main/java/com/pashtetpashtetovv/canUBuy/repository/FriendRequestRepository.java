package com.pashtetpashtetovv.canUBuy.repository;

import com.pashtetpashtetovv.canUBuy.domain.model.FriendRequest;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findByTarget(User user);

    @Transactional
    @Modifying
    @Query("update FriendRequest fr set fr.active = :active where fr.id = :id")
    void setActive(Long id, boolean active);
}
