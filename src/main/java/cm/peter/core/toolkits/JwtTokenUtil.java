package cm.peter.core.toolkits;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.Assert;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    public final static String MY_ISSUER = "DBH";

    public static void main(String[] args) {
        String secret = "aW16STdrY0NWSHV3eWZvSG9jVFJTUXFhemRyZU16M3d3ZkdHeFFzZ1pKYjVFMW1tU3pKR2Z2NTVDalc5OUpUNWt1OGY4VW95dnpqRGxibkVZdGNMbnUwSVpoWlRjQVhhRjJTOUNhM0J3ZlJHNnZVNHIxSHZZTlg5SjV6TkMwVDhhcExnMGFEV29TcUNsc2hEODF4YW5RY2dJYnhHajRROGRwMmtHN1hUZGF6Rm1Rck9FRHRvYXVsdVpvUVFsUkl2aGxFRGdDdUo2RHdvMlpOWkZCbkJOTHdNVDM2eVU5UXl1WUcxdDNpU3M5MWZJZTlIRnY0OGtaT01JTmUxVUpnWUpKeEFPNlFLWkxTcVNWMjNzS3JwQlNQZmtQTTU3SWEwRDFjMVgzSTkwR1ZOU2xOODN5RlRJM1NxVXZjMm5oTW9leXFHYzBNT1lyWm9Jc1dyNHZFUEdhMFExbUhjYUVDc0JFTVRDWEpyTmtITHhVVHNJOEVZOTFqYUJSYk5naGNvODBIcDVRbG5qVUlVdzFvaXFpOGRWUlpkcmVTeVByWVR6VXUxNnMxbzcyS2ViQnBhRXBNS2J5U2NzNEp4aFNPZFptalhKYlJmNENHUGkxNWJLaUVDYkEyNW9nTkdaWUtsbm9OV1dLeGZPMlVQODZ6NFZnRUJ2Y1BmQmliQkRYN0s=";
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//        String secret = RandomStringUtils.getRandomString(512);

        System.out.println("!!!secret bytes length = " + secret.getBytes().length);

        jwtTokenUtil.setSecret(secret);
        System.out.println("!!!jwtToken Secret = " + jwtTokenUtil.getSecret());
//        JwtUser user = new JwtUser();
//        user.setUid("238");
//        String jwtToken = jwtTokenUtil.generateToken(user);
//        System.out.println("!!!jwtToken = " + jwtToken);
//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJkYmgiLCJzdWIiOiJCT0IiLCJpYXQiOjE1NzQ5MjkxMzcsImV4cCI6MTU3NDk0NzEzN30.16czhy0OEAZcK5Czm2kbg3bgjfadTgmjbk0sO7dyUe4JqQ7FgOgwdfm1xDRqfhqnEEgrU8O4A28JcGLeWbpPIg";
//        Assert.isTrue(jwtTokenUtil.validateToken(jwtToken, user), "token invalid");

//        System.out.println("!!!getSubjectFromToken = " + jwtTokenUtil.getSubjectFromToken(jwtToken));
//        System.out.println("!!!getExpirationDateFromToken = " + jwtTokenUtil.getExpirationDateFromToken(jwtToken).toString());
//        Assert.isTrue(jwtTokenUtil.validateToken(jwtToken, user), "token invalid");
    }

    public static final int JWT_TOKEN_VALIDITY = 8 * 60 * 60;

    private String secret;

    public String randomSecret() {
        return Encoders.BASE64.encode(RandomStringUtils.getRandomString(SignatureAlgorithm.HS512.getMinKeyLength()).getBytes());
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }


    public String getIssuer(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getIssuer();
    }

    public String getSubjectFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public String getClaimsValue(String token, String key, String defaultValue) {
        final Claims claims = getAllClaimsFromToken(token);
        return (String) claims.getOrDefault(key, defaultValue);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userId);
    }

    public String generateToken(String userId, Map<String, Object> claims) {
        return doGenerateToken(claims, userId);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setIssuer(MY_ISSUER)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    //validate token
    public Boolean validateToken(String token, String userId) {
        final String uid = getSubjectFromToken(token);
        return (uid.equals(userId) && !isTokenExpired(token));
    }

    private Key getSigningKey() {
        Assert.notNull(this.secret, "secret cant be null");
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
