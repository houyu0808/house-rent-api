package com.hsrt.hsrtllapi.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class GenerateToken {
    //token过期时间
    public static final int jwtExpirationAt = 86400000;
    //token加密密钥
    public static final String secret = "JKKLJOoasdlfj";
    //token生成者标识
    public static final String auther = "liulei";


    //    生成token
    public static String createToken(String username, String userId,String role){
        Date nowTime = new Date();
        Date expiryDate = new Date(nowTime.getTime() + jwtExpirationAt);
        return JWT.create()
                .withIssuer(auther)
                .withIssuedAt(new Date())
                .withExpiresAt(expiryDate)
                .withClaim("identify", role)
                .withClaim("username",username)
                .withClaim("userId",userId)
                .sign(Algorithm.HMAC256(secret));
    }
//    @PostConstruct
//    public void init() {
//        jwtExpirationAtTmp = this.jwtExpirationAt;
//        secretTmp = this.secret;
//        auther = this.auther;
//    }

}