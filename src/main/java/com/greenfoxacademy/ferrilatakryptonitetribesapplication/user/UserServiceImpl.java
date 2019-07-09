package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingFactory;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingType;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.UserRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.UserWithKingdomDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private IKingdomRepository kingdomRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, IKingdomRepository kingdomRepository) {
    this.userRepository = userRepository;
    this.kingdomRepository = kingdomRepository;
  }

  public boolean isValidUser(User user) {
    return (user.getUsername() != null && !user.getUsername().equals(""));
  }

  public boolean isExistingUser(User user) {
    return userRepository.existsByUsername(user.getUsername());
  }

  @Override
  public List<User> findAllUser() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(user -> users.add(user));
    return users;
  }

  @Override
  public void saveUser(User user) {
    userRepository.save(user);
  }

  public Optional<User> findUserById(long id) {
    return userRepository.findById(id);
  }

  public ResponseEntity registerNewUser(UserDTO userDTO) {
    String userName = userDTO.getUsername();
    String password = userDTO.getPassword();

    if (!credentialsProvided(userName, password)) {
      return registerUserWithMissingCredentials(userDTO);
    } else if (userRepository.existsByUsername(userName)) {
      throw new UserRelatedException("Username already taken, choose another one!", "/register");
    } else {
      User userToBeSaved = createUserFromDTO(userDTO);
      Kingdom kingdom = initKingdom(createKingdom(userDTO.getKingdom(),
          new User(userToBeSaved.getUsername(), userToBeSaved.getPassword())));
      kingdom.setUser(userToBeSaved);
      kingdomRepository.save(kingdom);
      userRepository.save(userToBeSaved);
      return ResponseEntity.status(200)
          .body(new UserWithKingdomDTO(
              userToBeSaved.getId(), userToBeSaved.getUsername(), kingdom.getId()));
    }
  }

  public ResponseEntity registerUserWithMissingCredentials(UserDTO userDTO) {
    String userName = userDTO.getUsername();
    String password = userDTO.getPassword();
    if ((userName == null || userName.isEmpty()) && (password == null || password.isEmpty())) {
      throw new UserRelatedException("Invalid user details provided.", "/register");
    } else if (password == null || password.isEmpty()) {
      throw new UserRelatedException("Missing parameter: password", "/register");
    } else {
      throw new UserRelatedException("Missing parameter: username", "/register");
    }
  }

  public User createUserFromDTO(UserDTO userDTO) {
    return new ModelMapper().map(userDTO, User.class);
  }

  public Kingdom initKingdom(Kingdom kingdom) {
    Gold startingGold =  new Gold(100, kingdom);
    kingdom.getResourceList().add(0,startingGold);
    for (BuildingType buildingType : BuildingType.values()) {
      kingdom.getBuildings().add(BuildingFactory.createBuilding(buildingType));
    }
    for (Building building : kingdom.getBuildings()) {
      building.setKingdom(kingdom);
    }
    return kingdom;
  }

  public Kingdom createKingdom(String kingdomName, User user) {
    if (isKingdomNameNullOrEmpty(kingdomName)) {
      return new Kingdom(String.format("%s's kingdom", user.getUsername()), user);
    }
    return new Kingdom(kingdomName, user);
  }

  public Boolean isKingdomNameNullOrEmpty(String kingdomName) {
    return kingdomName == null || kingdomName.isEmpty();
  }

  @Override
  public boolean credentialsProvided(String username, String password) {
    return (username != null && !username.equals("") && password != null && !password.equals(""));
  }

  @Override
  public boolean validCredentials(String username, String password) {
    if (userRepository.existsByUsername(username)) {
      return userRepository.findByUsername(username).getPassword().equals(password);
    } else {
      return false;
    }
  }

  @Override
  public ResponseEntity loginResponse(String username, String password) {

    if (!credentialsProvided(username, password)) {
      return loginResponseWithValidCredentials(username, password);
    }
    if (validCredentials(username, password)) {
      return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
    }
    if (!userRepository.existsByUsername(username)) {
      throw new UserRelatedException("No such user in database, please register first", "/login");
    } else {
      throw new UserRelatedException("Invalid password, please try to log-in again.", "/login");
    }
  }

  @Override
  public ResponseEntity loginResponseWithValidCredentials(String username, String password) {
    if ((username.equals("")) && (password.equals(""))) {
      throw new UserRelatedException("Missing parameter(s): username, password", "/login");
    } else if ((username.equals(""))) {
      throw new UserRelatedException("Missing parameter(s): username", "/login");
    } else {
      throw new UserRelatedException("Missing parameter(s): password", "/login");
    }
  }
}
