package com.saurav.cms.comms.security;

import com.saurav.cms.comms.enums.UserType;
import com.saurav.cms.comms.security.dto.ClaimResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class JWTTokenUtil {
    private static final long JWT_EXPIRATION_AFTER = 1;
    private static final ChronoUnit JWT_EXPIRATION_UNIT = ChronoUnit.DAYS;

    private static final String SECRET_KEY = "4GRlPB01flTTSwfKUfDPUeIIFa0yZaXQc3MAAcR8mvxEWtqAauOeMaKxLzzDAxRekOFNRmnsA0Cv44jH0FunLxQWRoFMJ7xeQiB2HgV7BxGOnq";

//    public static void main(String[] args) {
//        String jwt = createJWT("saurav", new String[]{"CEO", "Developer"}, "ADMIN");
//        ClaimResponse usernameFromToken = getUsernameFromToken(jwt);
//        System.out.println("User name and permission is::");
//        System.out.println(usernameFromToken.getUsername());
//        System.out.println(usernameFromToken.getPermission());
//    }

    public String createJWT(String username, String[] permission, String role) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        return Jwts.builder()
                .claim("username", username)
                .claim("permission", permission)
                .claim("role", role)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(JWT_EXPIRATION_AFTER, JWT_EXPIRATION_UNIT)))
                .signWith(hmacKey)
                .compact();
    }

    public Jws<Claims> getClaimsFromToken(String jwtString){
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY),
                SignatureAlgorithm.HS256.getJcaName());

        return Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);
    }

    public ClaimResponse getUserInformationFromToken(String jwtString){
        Jws<Claims> claimsJws = getClaimsFromToken(jwtString);
        System.out.println(claimsJws);
        Claims body = claimsJws.getBody();
        String username = body.get("username", String.class);
        ArrayList<String> permissions = body.get("permission", ArrayList.class);
        String userType = body.get("userType", String.class);
        return new ClaimResponse(username, permissions, userType);
    }

}
