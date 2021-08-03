package com.crhms.security.resourceserver;


import com.alibaba.fastjson.JSONObject;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.codec.Codecs;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

import java.util.Map;

/**
 * Created by yfx on 2017-05-26 0026.
 * spring-security-jwt
 */
public class SecurityJwtUtils {

    private static Object obj=new Object();

    /**
     * jzq.sso的私钥
     */
    private static String ssoAppPrivateKey="testKeytestKeytestKeytestKey";

    /**
     * jzq.sso的公钥
     */
    private static String ssoAppPublicKey="testKeytestKeytestKeytestKey";

    /**
     * 签名工具
     */
    private static RsaSigner signer;

    private static RsaVerifier verifier;

//    static {
//        signer=new RsaSigner(ssoAppPrivateKey);
//        verifier=new RsaVerifier(ssoAppPublicKey);
//    }

    public byte[] enSign(String data){
        byte[] content = Codecs.utf8Encode(data);
        byte[] signed = signer.sign(content);
        return signed;
    }


    /**
     * 加密token
     * @param json 要加密的json
     * @return
     */
    public static String encode(JSONObject json){
        Jwt jwt = JwtHelper.encode(json.toJSONString(), signer);
        return jwt.getEncoded();
    }

    /**
     *
     * @param json 要加密的json
     * @param headers 共享数据，这个可以JwtHelper.headers取得map得取得：{headers.key=headers.val, alg=HS256, typ=JWT}
     * @return
     */
    public static String encode(JSONObject json,Map<String,String> headers){
        Jwt jwt = JwtHelper.encode(json.toJSONString(), signer, headers);
        return jwt.getEncoded();
    }

    /**
     * 解密token|token自带了解密串，使用了公私对串再进行一次校验
     * @return
     */
    public static JSONObject decode(String token) throws Exception {
        try{
            Jwt jwt = JwtHelper.decodeAndVerify(token,verifier);
            return JSONObject.parseObject(jwt.getClaims());
        }catch (RuntimeException e){
            throw new Exception("jwtError");
        }
    }

    public static void main(String[] args) throws Exception {
//        JSONObject json=new JSONObject();
//        json.put("hehe","https://my.oschina.net/yifanxiang");
//        String token=SecurityJwtUtils.encode(json);
//        System.out.println(token);
//        //Map map=JwtHelper.headers(token);
//        //System.out.println(map);
        JSONObject info=SecurityJwtUtils.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsic2FtcGxlQ2xpZW50SWQiXSwidXNlcl9uYW1lIjoiZ3JvdXAiLCJzY29wZSI6WyJyZWFkIl0sImV4cCI6MTYxNDEzNTAwOSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NRURJVU0iLCJST0xFX05PUk1BTCJdLCJqdGkiOiI3Nzc0YTY5Yy0yM2VjLTRjMWItYWQxZC02OTZhMjc3YjM1MjkiLCJjbGllbnRfaWQiOiJzYW1wbGVDbGllbnRJZCJ9.9YAaCzP_sedl98LmlCVsfxonsmWdAPulm3WVFOCenWI");
        System.out.println(info);
    }

}
