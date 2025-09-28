package com.example.library.controller;


import com.example.library.model.Member;
import com.example.library.repo.MemberRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
private final MemberRepository members;
public AuthController(MemberRepository m) { this.members = m; }


record LoginRequest(@NotBlank String universityId, @NotBlank String name, String email) {}
record LoginResponse(String token, String memberId) {}


@PostMapping("/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
var m = members.findByUniversityId(req.universityId())
.orElseGet(() -> members.save(new Member(null, req.universityId(), req.name(), req.email())));
// In a real app use SSO + JWT. Here we reuse the API key for simplicity.
return ResponseEntity.ok(new LoginResponse("API", m.getId()));
}
}
