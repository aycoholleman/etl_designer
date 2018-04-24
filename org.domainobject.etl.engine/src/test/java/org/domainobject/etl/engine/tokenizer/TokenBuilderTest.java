package org.domainobject.etl.engine.tokenizer;

import static org.junit.Assert.*;
import org.junit.Test;

@SuppressWarnings("static-method")
public class TokenBuilderTest {

  @Test
  public void testAdd01() {
    TokenBuilder tb = new TokenBuilder(3);
    tb.add('a');
    assertEquals("01", "a", tb.toString());
    assertEquals("02", 1, tb.length());
  }

  @Test
  public void testAdd02() {
    TokenBuilder tb = new TokenBuilder(3);
    tb.add('a', 'b');
    assertEquals("01", "ab", tb.toString());
    assertEquals("02", 2, tb.length());
  }

  @Test
  public void testAdd03() {
    TokenBuilder tb = new TokenBuilder(3);
    tb.add('a', 'b');
    tb.add('c', 'd');
    tb.add('e', 'f');
    assertEquals("01", "abcdef", tb.toString());
    assertEquals("02", 6, tb.length());
  }

}
