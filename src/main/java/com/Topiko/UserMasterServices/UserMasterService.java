package com.Topiko.UserMasterServices;

import com.Topiko.DTOClasses.MobileResponseDTO;
import com.Topiko.UserMasterModels.Users;
import com.Topiko.UserMasterRepository.UserMasterRepository;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class UserMasterService
{
	public static final Logger logger = LoggerFactory.getLogger(UserMasterService.class);
    @Autowired private UserMasterRepository userMasterRepository;



    public MobileResponseDTO getAllUsersInfo(MobileResponseDTO resp)
    {
        logger.info("inside all users method");
        List<Users> userList = userMasterRepository.findAll();
        JSONObject jsonObject = new JSONObject();
        try {
        	jsonObject.put("Users", userList);
    		resp.setMessage(HttpStatus.OK.toString());
    		resp.setStatusCode(HttpStatus.OK.toString());
    		resp.setRespData(jsonObject.toString());
    		return resp;
		} catch (Exception e) {
			resp.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    		resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    		return resp;
		}
    }

    public String addUser(Users users)
    {
        logger.info("inside user save method service class");
        List<Users> usr = userMasterRepository.getUserDetailsByNameAndMobileNumber(users.getMobile(),users.getName());
        if(usr.size()>0)
        {
            logger.info("inside user already existed block");
            return "User Already Existed";
        }
        else {
            users.setCreated_date(getDateWithTimeStamp());
             userMasterRepository.save(users);
            return "User Added successfully";
        }
    }
    public String getDateWithTimeStamp()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return dtf.format(now);
    }
}
