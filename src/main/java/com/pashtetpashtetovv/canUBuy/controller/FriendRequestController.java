package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.dto.FriendRequestDTO;
import com.pashtetpashtetovv.canUBuy.domain.model.FriendRequest;
import com.pashtetpashtetovv.canUBuy.service.FriendRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("friendReq")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/send")
    public String sendFriendRequest(@ModelAttribute FriendRequestDTO friendRequestDTO){
        friendRequestService.saveFriendRequest(friendRequestDTO);
        return "success";
    }

    @PostMapping("/accept/{requestId}")
    public String acceptFriendRequest(@PathVariable Long requestId){
        friendRequestService.accept(requestId);
        return "success";
    }

}
