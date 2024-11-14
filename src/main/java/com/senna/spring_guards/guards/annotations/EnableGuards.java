package com.senna.spring_guards.guards.annotations;

import com.senna.spring_guards.guards.default_beans.DefaultUseGuardsApplier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
    DefaultUseGuardsApplier.class
})
public @interface EnableGuards {

}
