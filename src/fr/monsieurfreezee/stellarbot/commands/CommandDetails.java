package fr.monsieurfreezee.stellarbot.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CommandDetails {
    String name();
    String commandName();
    String[] aliases() default {};
    String description();
}
