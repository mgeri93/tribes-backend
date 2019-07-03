package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeServiceImpTest {

  @Autowired
  TimeServiceImp timeServiceImp;

  @Test
  public void timeLeftWithFutureFinish() {
    Timestamp testStamp = Timestamp.valueOf("1970-01-01 01:08:57.975");
    Timestamp myStamp = timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:48:05.123"),
        Timestamp.valueOf("2019-07-02 18:57:03.098"));
    assertEquals(myStamp, testStamp);
  }

  @Test
  public void timeLeftWithAssertNotSame() {
    Timestamp testStamp = Timestamp.valueOf("1970-01-01 01:08:57.975");
    Timestamp myStamp = timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:48:05.123"),
        Timestamp.valueOf("2019-07-02 18:51:03.098"));
    assertNotSame(myStamp, testStamp);
  }

  @Test
  public void timeLeftWithFinishEarlierThanStart() {
    Timestamp testStamp = Timestamp.valueOf("1970-01-01 00:00:00.000");
    Timestamp myStamp = timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:57:05.123"),
        Timestamp.valueOf("2019-07-01 18:48:03.098"));
    assertEquals(myStamp, testStamp);
  }
}
