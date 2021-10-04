package com.visionboard.security.event.listener;

import com.visionboard.data.dto.DeviceInfo;
import com.visionboard.security.cache.LoggedOutJwtTokenCache;
import com.visionboard.security.event.OnUserLogoutSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OnUserLogoutSuccessEventListener implements ApplicationListener<OnUserLogoutSuccessEvent> {

    private final LoggedOutJwtTokenCache tokenCache;

    @Autowired
    public OnUserLogoutSuccessEventListener(LoggedOutJwtTokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }


    @Override
    public void onApplicationEvent(OnUserLogoutSuccessEvent event) {
        if (null != event){
            DeviceInfo deviceInfo = event.getLogOutRequest().getDeviceInfo();
            log.info(String.format("Log out success event received for user [%s] for device [%s]", event.getUserEmail(), deviceInfo));
            tokenCache.markLogoutEventFromToken(event);
        }
    }


}
