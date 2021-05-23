package com.ylz.common.utils;

/**
 * @author ylz
 * @Description
 * @date 2021-05-23-13:55
 */

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * JWTUtils工具类，生成jwt和解析jwt
 * JSON WEB TOKEN 结构组成：
 * (1)Header(头部)：包含加密算法，通常直接使用 HMAC SHA256
 * (2)Payload(负载)：存放有效信息，比如消息体、签发者、过期时间、签发时间等
 * (3)Signature(签名)：由header(base64后的)+payload(base64后的)+secret(秘钥)三部分组合，然后通过head中声明的算法进行加密
 * @author sixmonth
 * @date 2019年3月20日
 *
 */
public class JWTUtils {

    static String SECRETKEY = "KJHUhjjJYgYUllVbXhKDHXhkSyHjlNiVkYzWTBac1Yxkjhuad";

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey(String stringKey) {
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(APP_SECRET);
        return new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
    }
    /**
     * 创建jwt
     * @param ，uuid即可
     * @param subject json形式字符串或字符串，增加用户非敏感信息存储，如用户id或用户账号，与token解析后进行对比，防止乱用
     * @param expirationDate  生成jwt的有效期，单位秒
     * @return jwt token
     * @throws Exception
     */
    public static String createJWT(String uuid, String subject, long expirationDate) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(SECRETKEY);
        JwtBuilder builder = Jwts.builder().setIssuer("").setId(uuid).setIssuedAt(now).setSubject(subject)
                .signWith(signatureAlgorithm, getKeyInstance());
        if (expirationDate >= 0) {
            long expMillis = nowMillis + expirationDate*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt，获取实体
     * @param jwt
     */
    public static Claims parseJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
        SecretKey key = generalKey(SECRETKEY);
        String token =jwt;
        jwt =jwt.replace("\"", "");
        Claims claims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 实例演示
     */
    public static void main(String[] args) {
        try {
            JSONObject subject = new JSONObject(true);
            subject.put("tem", "哈哈哈");
            subject.put("userName", "sixmonth");
            String token = createJWT(StringUtil.getUUID(), subject.toJSONString(), 10);//10秒过期
            //System.out.println(token);
            Claims claims = parseJWT(token);
            System.out.println("解析jwt："+claims.getSubject());
            JSONObject tem = JSONObject.parseObject(claims.getSubject());
            System.out.println("获取json对象内容："+tem.getString("userName"));
            System.out.println(claims.getExpiration()+"///"+claims.getExpiration().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
