package com.estrellaticona.vetcare.iam.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estrellaticona.vetcare.iam.domain.services.UserCommandService;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateDniResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateEmailResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateNameResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdatePasswordResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdatePhoneResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateSpecialityResource;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.UpdateDniCommandFromResourceAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.UpdateEmailCommandFromResourceAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.UpdateNameCommandFromResourceAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.UpdatePasswordCommandFromResourceAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.UpdatePhoneCommandFromResourceAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.UpdateSpecialityCommandFromResourceAssembler;
import com.estrellaticona.vetcare.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/update-email")
    public ResponseEntity<Object> updateEmail(@RequestBody UpdateEmailResource resource) {
        try {
            var userId = ((Long) request.getAttribute("userId"));

            var command = UpdateEmailCommandFromResourceAssembler.toCommandFromResource(userId, resource);

            var user = userCommandService.handle(command);

            var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(userResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<Object> updatePassword(@RequestBody UpdatePasswordResource resource) {
        try {
            var userId = ((Long) request.getAttribute("userId"));

            var command = UpdatePasswordCommandFromResourceAssembler.toCommandFromResource(userId, resource);

            var user = userCommandService.handle(command);

            var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(userResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-name")
    public ResponseEntity<Object> updateName(@RequestBody UpdateNameResource resource) {
        try {
            var userId = ((Long) request.getAttribute("userId"));

            var command = UpdateNameCommandFromResourceAssembler.toCommandFromResource(userId, resource);

            var user = userCommandService.handle(command);

            var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(userResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-speciality")
    public ResponseEntity<Object> updateSpeciality(@RequestBody UpdateSpecialityResource resource) {
        try {
            var userId = ((Long) request.getAttribute("userId"));

            var command = UpdateSpecialityCommandFromResourceAssembler.toCommandFromResource(userId, resource);

            var user = userCommandService.handle(command);

            var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(userResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-dni")
    public ResponseEntity<Object> updateDni(@RequestBody UpdateDniResource resource) {
        try {
            var userId = ((Long) request.getAttribute("userId"));

            var command = UpdateDniCommandFromResourceAssembler.toCommandFromResource(userId, resource);

            var user = userCommandService.handle(command);

            var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(userResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-phone")
    public ResponseEntity<Object> updatePhone(@RequestBody UpdatePhoneResource resource) {
        try {
            var userId = ((Long) request.getAttribute("userId"));

            var command = UpdatePhoneCommandFromResourceAssembler.toCommandFromResource(userId, resource);

            var user = userCommandService.handle(command);

            var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);

            return ResponseEntity.ok(userResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}