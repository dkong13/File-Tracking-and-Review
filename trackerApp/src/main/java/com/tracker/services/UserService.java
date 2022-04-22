package com.tracker.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tracker.models.LoginUser;
import com.tracker.models.User;
import com.tracker.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
    	// TO-DO - Reject values or register if no errors:
    	
        // Reject if email is taken (present in database)
    	if(userRepo.findByEmail(newUser.getEmail())!=null) {
    		result.rejectValue("email", "unique", "This email is already used");
    	}
        // Reject if password doesn't match confirmation
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("password", "Mismatch", "Password does not match");
        }
        // Return null if result has errors
        if(result.hasErrors()) {
        	return null;
        }
        // Hash and set password, save user to database
        String hashed= BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        userRepo.save(newUser);
        return null;
    }
	
	public User login(LoginUser newLogin, BindingResult result) {
        // TO-DO: Additional validations!
    	// Find user by email, reject if not present
    	User user = userRepo.findByEmail(newLogin.getEmail());
    	if(user==null) {
    		result.rejectValue("email", "missingEmail", "Email not found");
    		return null;
    	} 
    	else {
    		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    		    result.rejectValue("password", "Matches", "Invalid Password");
    		}
    		
    	 
    	}
    	
    	return user;
    	
    }
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}

}
