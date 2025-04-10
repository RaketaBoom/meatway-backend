package com.example.meatwaybackend.dto.user;

import java.util.List;

public record UserProfilesResponse (
        List<UserProfileResponse> users,
        long size
) {
}
