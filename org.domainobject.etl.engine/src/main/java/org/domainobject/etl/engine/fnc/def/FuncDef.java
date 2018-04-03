package org.domainobject.etl.engine.fnc.def;

/**
 * Describes and defines a function within a function library.
 */
public final class FuncDef {

  private String uiName;
  private String description;
  private Class<?> type;

  public String getUiName() {
    return uiName;
  }

  public void setUiName(String uiName) {
    this.uiName = uiName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Class<?> getType() {
    return type;
  }

  public void setType(Class<?> type) {
    this.type = type;
  }

}
