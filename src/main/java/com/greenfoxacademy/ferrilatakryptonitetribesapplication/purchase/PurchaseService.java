package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface PurchaseService {

  Boolean isGoldEnough(Gold gold, Long cost);

  Gold getGoldOfKingdom(List<Resource> kingdomResource);

  int purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost) throws Exception;

  ResponseEntity purchaseTroop(Kingdom kingdom) throws Exception;

  int purchaseBuilding(Kingdom kingdom, BuildingDTO buildingDTO) throws Exception;

  String purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo) throws Exception;

  int purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo)
      throws Exception;

  int purchaseBuildingIfEnoughGold(Kingdom kingdom, BuildingDTO buildingDTO,
      Gold gold) throws Exception;

  String constructNewBuilding(BuildingDTO buildingDTO);

  void buildingToSaveInit(BuildingDTO buildingDTO, Kingdom kingdom);
}
