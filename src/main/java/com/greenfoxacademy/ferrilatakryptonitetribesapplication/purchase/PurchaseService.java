package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.List;

public interface PurchaseService {

  Boolean isGoldEnough(Gold gold, Long cost);

  Gold getGoldOfKingdom(List<Resource> kingdomResource);

  int purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost) throws Exception;

  int purchaseTroop(Kingdom kingdom) throws Exception;

  int purchaseBuilding(Kingdom kingdom, BuildingDTO buildingDTO) throws Exception;

  int purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo) throws Exception;

  int purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo)
      throws Exception;

  int purchaseBuildingIfEnoughGold(Kingdom kingdom, BuildingDTO buildingDTO,
      Gold gold) throws Exception;

  String constructNewBuilding(BuildingDTO buildingDTO);

  void buildingToSaveInit(BuildingDTO buildingDTO, Kingdom kingdom);

  Building upgradeBuildingByOneLevel(long buildingId, long kingdomId);

  int findBuildingIndexByBuildingId(long buildingId, Kingdom kingdom);

  void executeBuildingUpgrade(Building building, long buildingId, List<Resource> resources,
      Kingdom kingdom);
}
