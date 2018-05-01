package org.domainobject.etl.engine.tokenizer;

import static org.junit.Assert.*;
import org.junit.Test;

@SuppressWarnings("static-method")
public class CursorTest {

  @Test
  public void test01() {
    Cursor c = new Cursor("abcdefghij");
    assertTrue("01", c.at('a'));
    assertTrue("02", c.at() == 'a');
    assertTrue("03", c.position() == 0);
    assertTrue("04", c.line() == 0);
    assertTrue("05", c.column() == 0);
    assertTrue("06", c.peek() == 'b');
    assertTrue("07", c.forward().at() == 'b');
  }

  @Test
  public void test02() {
    Cursor c = new Cursor("abcdefghij");
    c.forward().forward().forward();
    assertTrue("01", c.at('d'));
    assertTrue("02", c.at() == 'd');
    assertTrue("03", c.position() == 3);
    assertTrue("04", c.line() == 0);
    assertTrue("05", c.column() == 3);
  }

  @Test
  public void test03() {
    Cursor c = new Cursor("ab\ncdefghij");
    c.forward().forward().forward();
    assertTrue("01", c.at('c'));
    assertTrue("02", c.at() == 'c');
    assertTrue("03", c.position() == 3);
    assertTrue("04", c.line() == 1);
    assertTrue("05", c.column() == 1);
  }

  @Test
  public void test04() {
    Cursor c = new Cursor("ab\n\ncdefghij");
    c.forward().forward().forward();
    assertTrue("01", c.at(Cursor.LF));
    assertTrue("02", c.at() == Cursor.LF);
    assertTrue("03", c.position() == 3);
    assertTrue("04", c.line() == 2);
    assertTrue("05", c.column() == 0);
  }

  @Test
  public void test05() {
    Cursor c = new Cursor("");
    assertTrue("01", c.at(Cursor.END_OF_RULE));
    c.forward();
    assertTrue("02", c.at(Cursor.END_OF_RULE));
    c.forward();
    assertTrue("03", c.at(Cursor.END_OF_RULE));
  }

}
