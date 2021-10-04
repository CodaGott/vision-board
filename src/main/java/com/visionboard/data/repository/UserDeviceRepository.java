package com.visionboard.data.repository;

import com.visionboard.data.model.RefreshToken;
import com.visionboard.data.model.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {
    Optional<UserDevice> findByUserUserId(String userId);
    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);
}
