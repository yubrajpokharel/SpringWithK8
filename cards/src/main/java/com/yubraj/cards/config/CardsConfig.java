package com.yubraj.cards.config;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cards")
@Getter
@Setter
@ToString
public class CardsConfig {
  private String msg;
  private String buildVersion;
  private Map<String, String> mailDetails;
  private List<String> activeBranches;
}
