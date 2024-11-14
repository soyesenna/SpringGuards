package com.senna.spring_guards.guards.interfaces;

public interface UseGuardsAspectHandler {

  void beforeApplyingGuards();
  void afterApplyingGuards();
  void afterControllerProceed();
  // Return the object to be returned when a guard fails
  Object whenGuardFails();
}
