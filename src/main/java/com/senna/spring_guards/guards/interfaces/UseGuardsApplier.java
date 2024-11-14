package com.senna.spring_guards.guards.interfaces;

public interface UseGuardsApplier {

  // Apply guards to the given classes
  // Return true if all guards are activated, otherwise return false
  boolean applyGuards(Class<? extends Guard>[] guardClasses) throws Exception;
}
