package com.senna.spring_guards.guards.annotations;

import com.senna.spring_guards.guards.interfaces.Guard;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseGuards {
    Class<? extends Guard>[] value();
}
