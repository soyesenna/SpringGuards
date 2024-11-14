package com.senna.spring_guards.guards.default_beans;

import com.senna.spring_guards.guards.interfaces.UseGuardsAspectHandler;
import org.springframework.stereotype.Component;

@Component(DefaultBeanNames.DEFAULT_USE_GUARDS_ASPECT_HANDLER)
public class DefaultGuardsAspectHandler implements UseGuardsAspectHandler {

  @Override
  public void beforeApplyingGuards() {

  }

  @Override
  public void afterApplyingGuards() {

  }

  @Override
  public void afterControllerProceed() {

  }

  @Override
  public Object whenGuardFails() {
    return null;
  }
}
