package com.grupo.pag.pagadminapi.config.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mail",ignoreUnknownFields = true)
@Component
@Data
public class EmailProperties {
    public String  hostName;
    public Integer  smtpPort;
    public String  username;
    public String password;
    public boolean tls;
}
