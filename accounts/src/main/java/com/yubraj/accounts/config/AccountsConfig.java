package com.yubraj.accounts.config;

import java.util.List;import java.util.Map;import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
@ToString
public class AccountsConfig {
  private String msg;
  private String buildVersion;
  private Map<String, String> mailDetails;
  private List<String> activeBranches;
}
