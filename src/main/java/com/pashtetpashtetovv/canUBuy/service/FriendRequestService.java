package com.pashtetpashtetovv.canUBuy.service;

import com.pashtetpashtetovv.canUBuy.domain.dto.FriendRequestDTO;
import com.pashtetpashtetovv.canUBuy.domain.model.FriendRequest;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.mapper.FriendRequestMapper;
import com.pashtetpashtetovv.canUBuy.repository.FriendRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {

    private static final Logger log = LoggerFactory.getLogger(FriendRequestService.class);

    private final FriendRequestRepository friendsRequestRepository;

    private FriendRequestMapper friendRequestMapper;

    public FriendRequestService(FriendRequestRepository friendsRequestRepository) {
        this.friendsRequestRepository = friendsRequestRepository;
    }

    public FriendRequestMapper getFriendRequestMapper() {
        return friendRequestMapper;
    }

    @Autowired
    public void setFriendRequestMapper(FriendRequestMapper friendRequestMapper) {
        this.friendRequestMapper = friendRequestMapper;
    }

    public FriendRequest saveFriendRequest(FriendRequest friendRequest) {
        return friendsRequestRepository.save(friendRequest);
    }

    public FriendRequest saveFriendRequest(FriendRequestDTO dto){
        return saveFriendRequest(friendRequestMapper.toEntity(dto));
    }

    public FriendRequest accept(FriendRequest friendRequest){
        log.info(friendRequest.toString());
        friendRequest.setActive(false);
        return friendsRequestRepository.save(friendRequest);
    }

    public void accept(Long id){
        friendsRequestRepository.setActive(id, false);
    }

    public List<FriendRequest> findByTarget(User user){
        return friendsRequestRepository.findByTarget(user);
    }
}
