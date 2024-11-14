package com.senna.spring_guards.guards.aspect;

import com.senna.spring_guards.guards.annotations.UseGuards;
import com.senna.spring_guards.guards.default_beans.DefaultBeanNames;
import com.senna.spring_guards.guards.interfaces.Guard;
import com.senna.spring_guards.guards.interfaces.UseGuardsApplier;
import com.senna.spring_guards.guards.interfaces.UseGuardsAspectHandler;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UseGuardsAspect {

  private final ApplicationContext applicationContext;

  public UseGuardsAspect(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Around("@annotation(useGuards)")
  public Object useGuardsApplierAdapter(ProceedingJoinPoint joinPoint, UseGuards useGuards)
      throws Throwable {
    UseGuardsAspectHandler useGuardsAspectHandler = this.findUseGuardsAspectHandler();

    useGuardsAspectHandler.beforeApplyingGuards();

    Class<? extends Guard>[] guardClasses = useGuards.value();

    UseGuardsApplier useGuardsApplier = this.findUseGuardsApplier();
    if (!useGuardsApplier.applyGuards(guardClasses)) {
      return useGuardsAspectHandler.whenGuardFails();
    }

    useGuardsAspectHandler.afterApplyingGuards();

    Object proceed = joinPoint.proceed();

    useGuardsAspectHandler.afterControllerProceed();

    return proceed;
  }

  private UseGuardsApplier findUseGuardsApplier() {
    UseGuardsApplier useGuardsApplier = null;
    try {
      useGuardsApplier = this.applicationContext.getBean(UseGuardsApplier.class);
    } catch (NoUniqueBeanDefinitionException noUniqueBeanDefinitionException) {
      useGuardsApplier = this.applicationContext.getBeansOfType(UseGuardsApplier.class)
          .entrySet().stream()
          .filter(entry -> !entry.getKey()
              .contentEquals(DefaultBeanNames.DEFAULT_USE_GUARDS_APPLIER))
          .map(Map.Entry::getValue)
          .findFirst()
          .orElse(this.applicationContext.getBean(DefaultBeanNames.DEFAULT_USE_GUARDS_APPLIER,
              UseGuardsApplier.class));
    }
    return useGuardsApplier;
  }

  private UseGuardsAspectHandler findUseGuardsAspectHandler() {
    UseGuardsAspectHandler useGuardsAspectHandler = null;
    try {
      useGuardsAspectHandler = this.applicationContext.getBean(UseGuardsAspectHandler.class);
    } catch (NoUniqueBeanDefinitionException noUniqueBeanDefinitionException) {
      useGuardsAspectHandler = this.applicationContext.getBeansOfType(UseGuardsAspectHandler.class)
          .entrySet().stream()
          .filter(entry -> !entry.getKey()
              .contentEquals(DefaultBeanNames.DEFAULT_USE_GUARDS_ASPECT_HANDLER))
          .map(Map.Entry::getValue)
          .findFirst()
          .orElse(this.applicationContext.getBean(DefaultBeanNames.DEFAULT_USE_GUARDS_ASPECT_HANDLER,
              UseGuardsAspectHandler.class));
    }
    return useGuardsAspectHandler;
  }
}
