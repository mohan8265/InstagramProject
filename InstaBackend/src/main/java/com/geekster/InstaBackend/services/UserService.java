package com.geekster.InstaBackend.services;

import com.geekster.InstaBackend.models.AuthenticationToken;
import com.geekster.InstaBackend.models.User;
import com.geekster.InstaBackend.models.dto.SignInInput;
import com.geekster.InstaBackend.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private AuthTokenService authTokenService;

    public String signUp(User user) {
        String currentEmail = user.getUserEmail();
        User existingUser = userRepo.findByUserEmail(currentEmail);
        if(existingUser == null){
            String encryptedPassword = "##" + user.getUserPassword() + "##";
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            return "sign up successfully";
        }else {
            return "user already signed up";
        }
    }

    public String signIn(SignInInput signInInput) {
        String currentEmail = signInInput.getUserEmail();
        User existingUser = userRepo.findByUserEmail(currentEmail);
        if(existingUser == null){
            return "sign up first";
        }

        AuthenticationToken existingToken = authTokenService.getTokenByUser(existingUser);
        if(existingToken == null){
            String encryptedPassword = "##" + signInInput.getUserPassword() + "##";
            if(encryptedPassword.equals(existingUser.getUserPassword())) {
                AuthenticationToken token = new AuthenticationToken(existingUser);
                authTokenService.addToken(token);
                return "signed in your token value is: " + token.getTokenName();
            }
            else{
                return "not a valid user";
            }
        }
        else {
            return "already signed in";
        }
    }

    public String updateUserInfo(Integer id,String email,User user) {

        User existingUser = userRepo.findById(id).get();
        if(existingUser != null){
            AuthenticationToken token = authTokenService.getTokenByUser(existingUser);
            if(token == null){
                return "sign in first";
            }
            else {
                user.setUserId(id);
                user.setUserEmail(email);
                String encryptedPassword = "##" + user.getUserPassword() + "##";
                user.setUserPassword(encryptedPassword);
                userRepo.save(user);
                return "information updated successfully";
            }
        }
        else {
            return "not a valid user";
        }
    }

    public String signOut(String email, String tokenValue) {
        User existingUser = userRepo.findByUserEmail(email);
        if(existingUser != null){
            return "this email is not registered";
        }

        authTokenService.removeToken(tokenValue);
        return "sign out successfully";
    }

    public User getUserByEmail(String email) {
        return userRepo.findByUserEmail(email);
    }
}
