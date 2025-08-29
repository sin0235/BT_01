package com.example.app.util;
import java.util.regex.Pattern;

public class Validation {
  private static final Pattern EMAIL =
      Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

  public static boolean isBlank(String s){ return s==null || s.trim().isEmpty(); }
  public static boolean isEmail(String s){ return s!=null && EMAIL.matcher(s).matches(); }
}