package fr.monsieurfreezee.stellarbot.commands.argumentscommands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static fr.monsieurfreezee.stellarbot.commands.argumentscommands.ArgumentType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ArgumentsList {
    ArgumentType[] arguments() default {TEXT};
}
