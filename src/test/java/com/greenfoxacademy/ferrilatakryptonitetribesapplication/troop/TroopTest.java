package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TroopTest {

  @MockBean
  private TroopServiceImp troopServiceImp;

  @Test
  public void constructorWithNegativeLevel() {
    Troop myTroop = new Troop(-2, 4, 5, 6, new Kingdom());
    assertEquals(myTroop.getLevel(), 1);
  }

  @Test
  public void constructorWithAllInvalidParameters() {
    Troop anotherTroop = new Troop(-1, -4, 0, 0, null);
    assertEquals(anotherTroop.getLevel(), 1);
    assertEquals(anotherTroop.getHp(), 1);
    assertEquals(anotherTroop.getAttack(), 1);
    assertEquals(anotherTroop.getDefense(), 1);
  }

  @Test
  public void troopValidityCheck() {
    Troop testTroop = new Troop(-1, -4, 0, 0, null);
    when(troopServiceImp.isValidTroop(testTroop)).thenReturn(true);
    assertTrue(troopServiceImp.isValidTroop(testTroop));
  }
}
