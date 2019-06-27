package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import static org.junit.Assert.assertFalse;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KingdomTest {

  @MockBean
  private KingdomServiceImpl kingdomService;


  @Test
  public void isValidKingdomWithIncorrectInputs() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom("", new User())));
  }

  @Test
  public void isValidKingdomWithNull() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom(null, new User())));
  }

  @Test
  public void isExistingKingdomForExisting() {
    Kingdom myKingdom = new Kingdom("Tanya", new User());
    assertFalse(kingdomService.isExistingKingdom(myKingdom));
  }
}
