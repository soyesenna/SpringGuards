package com.senna.spring_guards.guards.default_beans;

import com.senna.spring_guards.guards.interfaces.Guard;
import com.senna.spring_guards.guards.interfaces.UseGuardsApplier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component(DefaultBeanNames.DEFAULT_USE_GUARDS_APPLIER)
public class DefaultUseGuardsApplier implements UseGuardsApplier {

  private final ApplicationContext applicationContext;

  public DefaultUseGuardsApplier(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public boolean applyGuards(Class<? extends Guard>[] guardClasses) throws Exception {
    for (Class<? extends Guard> guardClass : guardClasses) {
      Guard guard = this.applicationContext.getBean(guardClass);
      if (!guard.canActivate()) {
        return false;
      }
    }
    return true;
  }
}
