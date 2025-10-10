package in.ajay.moneymanager.controller;


import in.ajay.moneymanager.dto.AuthDTO;
import in.ajay.moneymanager.dto.ProfileDTO;
import in.ajay.moneymanager.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

   @PostMapping("/register")
    public ResponseEntity<ProfileDTO> registerProfile(@RequestBody ProfileDTO profileDTO){

        ProfileDTO registerdProfile=profileService.registerProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerdProfile);


   }
@GetMapping("/activate")
   public ResponseEntity<String>activateProfile(@RequestParam String token){

       boolean isActivated= profileService.activateProfile(token);
       if(isActivated){
           return ResponseEntity.ok("Profile activated successfully");
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation token not found or already used");
       }
   }
   @PostMapping("/login")
public ResponseEntity<Map<String,Object>>login(@RequestBody AuthDTO authDTO){

       try{
           if(!profileService.isAccountActive(authDTO.getEmail())) {

               return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Account is not active.Please activate your account first"));
           }
           Map<String,Object>response=profileService.authenicateAndGenerateToken(authDTO);
           return ResponseEntity.ok(response);}
       catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                   "message",e.getMessage()
           ));
           }

       }

}


