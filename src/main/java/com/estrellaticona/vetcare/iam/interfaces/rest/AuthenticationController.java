package com.estrellaticona.vetcare.iam.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estrellaticona.vetcare.iam.domain.services.UserCommandService;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.SignInResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.SignUpResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserCommandService userCommandService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpResource resource) {
        try {

            var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);

            var user = userCommandService.handle(command);

            var token = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInResource resource) {
        try {
            var command = SignInCommandFromResourceAssembler.toCommandFromResource(resource);

            var user = userCommandService.handle(command);

            var token = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}