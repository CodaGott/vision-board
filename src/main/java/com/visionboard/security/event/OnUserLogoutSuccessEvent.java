package com.visionboard.security.event;

import com.visionboard.data.dto.loginNlogout.LogOutRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.Date;

@Data
@Getter
@Setter
public class OnUserLogoutSuccessEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private final String userEmail;
    private final String token;
    private final transient LogOutRequest logOutRequest;
    private final Date eventTime;

    public OnUserLogoutSuccessEvent(String userEmail, String token, LogOutRequest logOutRequest) {
        super(userEmail);
        this.userEmail = userEmail;
        this.token = token;
        this.logOutRequest = logOutRequest;
        this.eventTime = Date.from(Instant.now());
    }
}
