package com.nexcircle.application.user.usecase;

import com.nexcircle.application.user.dto.FriendShipFilter;
import com.nexcircle.application.user.dto.FriendShipResponseDto;
import com.nexcircle.application.user.mapper.FriendShipMapper;
import com.nexcircle.domain.user.repository.FriendshipRepository;
import com.nexcircle.domain.user.service.SecurityContextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GetFriendShipUseCase {
    FriendshipRepository friendshipRepository;
    FriendShipMapper friendShipMapper;
    SecurityContextService securityContextService;

    public Page<FriendShipResponseDto> getFriendShipsByUserLogin(FriendShipFilter filter){
        Pageable pageable = PageRequest.of(filter.getPage(),filter.getSize(), Sort.by("created_at").descending());

        return this.friendshipRepository.findAllByUserId(this.securityContextService.getCurrentUserId(), pageable).map(
                fs -> new FriendShipResponseDto(
                        fs.getId(),
                        fs.getUserId(),
                        fs.getFullName(),
                        fs.getAvatar()
                )
        );
    }
}
