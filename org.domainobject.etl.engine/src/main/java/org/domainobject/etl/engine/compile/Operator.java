package org.domainobject.etl.engine.compile;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import static com.google.common.collect.Maps.newHashMapWithExpectedSize;

public enum Operator {

  /* Arithmetic operators */
  PLUS("+"), MINUS("-"), TIMES("*"), DIV("/"), MOD("%"),
  /* Boolean operators */
  AND("&&"), OR("||"), NOT("!"),
  /* Comparison operators */
  EQ("="), NEQ("!="), LT("<"), GT(">"), LTE("<="), GTE(">="),
  /* String operators */
  STRCONCAT("&", "+"),
  /* Assignment operator */
  ASSIGN(":="),
  /* Library namespace operator */
  NAMESPACE("::", null);

  //@formatter:off
  private static final char[] startChars = new char[] {
    '+',
    '-',
    '*',
    '%',
    '/',
    '&',
    '|',
    '!',
    '=',
    '<',
    '>',
    ':'
  };
  //@formatter:on

  private static final HashMap<String, Operator> table;
  private static final EnumSet<Operator> arithOps;
  private static final EnumSet<Operator> compOps;

  static {
    Arrays.sort(startChars);
    arithOps = EnumSet.range(PLUS, MOD);
    compOps = EnumSet.range(EQ, GTE);
    table = newHashMapWithExpectedSize(values().length);
    for (Operator op : values())
      table.put(op.symbol, op);
  }

  public static Operator parse(String symbol) {
    return table.get(symbol);
  }

  public static boolean isOperatorStart(char c) {
    return Arrays.binarySearch(startChars, c) >= 0;
  }

  private final String symbol;
  private final String javaSymbol;

  private Operator(String symbol) {
    this.symbol = symbol;
    this.javaSymbol = symbol;
  }

  private Operator(String symbol, String javaSymbol) {
    this.symbol = symbol;
    this.javaSymbol = javaSymbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getJavaSymbol() {
    return javaSymbol;
  }

  public boolean isLogicalOperator() {
    return this == AND || this == OR;
  }

  public boolean isAssignmentOperator() {
    return this == ASSIGN;
  }

  public boolean isComparisonOperator() {
    return compOps.contains(this);
  }

  public boolean isStringOperator() {
    return this == STRCONCAT;
  }

  public boolean isArithmeticOperator() {
    return arithOps.contains(this);
  }

  public boolean isUnaryOperator() {
    return this == PLUS || this == MINUS || this == NOT;
  }
}
