package com.Topiko.UserMasterControllers;

import com.Topiko.DTOClasses.MobileResponseDTO;
import com.Topiko.UserMasterModels.Users;
import com.Topiko.UserMasterServices.UserMasterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/User")
public class UserMasterContoller
{
  public static final Logger logger = LoggerFactory.getLogger(UserMasterContoller.class);

    @Autowired private UserMasterService userMasterService;

    @GetMapping(value="/error")
    public String errorMethod()
    {
        return "error method called for error calling";
    }
    @GetMapping(value="/getAllUsersInfo")
    public MobileResponseDTO getAllUsersInfo()
    {
    	MobileResponseDTO resp =new MobileResponseDTO();
    	logger.info("inside getAllUsersInfo method controller");
    	return resp= userMasterService.getAllUsersInfo(resp);
    }

    @PostMapping(value="/createUser")
    public String addUser(@RequestBody Users users)
    {
        logger.info("inside user save method controller");
        String response =  userMasterService.addUser(users);
        return response;
    }
}
