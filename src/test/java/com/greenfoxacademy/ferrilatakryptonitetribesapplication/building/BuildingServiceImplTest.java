package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BuildingServiceImplTest {

  private BuildingServiceImpl buildingService;

  @Mock private BuildingRepository buildingRepository;

  private Building academy = BuildingFactory.createBuilding(BuildingType.Academy);

  private Building farm;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    buildingService = new BuildingServiceImpl(buildingRepository);
  }

  @Test
  public void isValidBuildingWithCorrectInput() {
    assertTrue(buildingService.isValidBuilding(academy));
  }

  @Test
  public void isValidBuildingWithIncorrectInput() {
    assertFalse(buildingService.isValidBuilding(farm));
  }

  @Test
  public void saveValidBuilding() {
    Building mine = BuildingFactory.createBuilding(BuildingType.Mine);
    when(buildingService.saveBuilding(any(Building.class))).thenReturn(mine);
    assertEquals(mine, buildingRepository.save(mine));
  }

  @Test
  public void findValidBuildingById() {
    Building buildingToReturn = BuildingFactory.createBuilding(BuildingType.Academy);
    buildingToReturn.setLevel(100L);
    when(buildingService.findById(1)).thenReturn(buildingToReturn);
    assertEquals(100L, buildingRepository.findById(1).getLevel(), 0);
  }
}
