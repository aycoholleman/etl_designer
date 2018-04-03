package org.domainobject.etl.engine.fnc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a function library.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Library {

  /**
   * The namespace of the library (e.g. "sql" in sql->select).
   * 
   * @return
   */
  String namespace();

}
