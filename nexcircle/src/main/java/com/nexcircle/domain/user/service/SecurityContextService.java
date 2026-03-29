package com.nexcircle.domain.user.service;

import java.util.UUID;

public interface SecurityContextService {
    UUID getCurrentUserId();
    String getCurrentUserName();
}
