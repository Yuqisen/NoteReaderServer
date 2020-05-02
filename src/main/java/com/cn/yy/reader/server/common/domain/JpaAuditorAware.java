package com.cn.yy.reader.server.common.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * JPA通用项目设定器.
 *
 * @author Yuqisen
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditorAware implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(0L);
  }
}
