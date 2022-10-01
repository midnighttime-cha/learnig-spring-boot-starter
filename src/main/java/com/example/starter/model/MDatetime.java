package com.example.starter.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

// Referance
// https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format

@Service
public class MDatetime {

  private final String[] numberThai = new String[] { "๐", "๑", "๒", "๓", "๔", "๕", "๖", "๗", "๘", "๙" };

  public String dateFormat(String dateTime, String pattern) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("th", "EN"));
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
    String date = simpleDateFormat.format(new Date(dateTime));
    return date;
  }

  public String dateFormatTHNumber(String dateTime, String type) {
    String d = dateFormat(dateTime, "d");

    String y = dateFormat(dateTime, "yyyy");
    String _dTH = loopNumberThai(d.split(""));
    String _yTH = loopNumberThai(y.split(""));

    String _mTH = "";
    if (type == "SHORT") {
      _mTH = dateFormat(dateTime, "MMM");
    } else {
      _mTH = dateFormat(dateTime, "MMMM");
    }

    String date = _dTH + " " + _mTH + " " + _yTH;
    return date;
  }

  private String loopNumberThai(String[] splitText) {
    String _numberThai = "";
    for (int i = 0; i < splitText.length; i++) {
      int intNum = Integer.parseInt(splitText[i]);
      _numberThai += numberThai[intNum];
    }
    return _numberThai;
  }

}
