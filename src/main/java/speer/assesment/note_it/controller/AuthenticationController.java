/*

 * Date : 03/01/24

 * Author : SWASTIK PREETAM DASH

 */

package speer.assesment.note_it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import speer.assesment.note_it.constant.URLConstants;
import speer.assesment.note_it.utility.AuthenticationUtility;

@RestController
@RequestMapping(value = URLConstants.AUTH_BASE_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationUtility authenticationUtility;

    @PostMapping(value = URLConstants.AUTH_SIGNUP_ENDPOINT)
    public ResponseEntity<Object> signup() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = URLConstants.AUTH_LOGIN_ENDPOINT)
    public ResponseEntity<Object> login() throws Exception {
        return new ResponseEntity<>(authenticationUtility.generateToken("test"), HttpStatus.OK);
    }

}
