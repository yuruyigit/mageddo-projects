package com.mageddo.featureswitch;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FeatureDefaults {
	Status status() default Status.INACTIVE;
	String value() default "";
}
