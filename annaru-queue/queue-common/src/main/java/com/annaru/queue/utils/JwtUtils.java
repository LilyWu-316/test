package com.annaru.queue.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Description Json Web Token工具类
 * @Author TuMinglong
 * @Date 2019/5/10 11:19
 **/
public class JwtUtils {
    /**
     * 生成token
     *
     * @param claims 加密内容
     * @param secret 秘钥
     * @param expire 过期时间
     * @return
     */
    public String generateToken(Map<String, Object> claims, String secret, int expire) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, expire);
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(c.getTime())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;
    }

    /**
     * 验证并解密Token
     *
     * @param token
     * @param secret
     * @return 返回NULL表示验证失败
     */
    public Map<String, Object> validateAndExtractToken(String token, String secret) throws Exception {
        if (token == null) {
            return null;
        }
        try {
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return body;
        } catch (Exception e) {
            throw new Exception("token已失效");
        }
    }

    public static void main(String[] args) {
        String url = "https://www.jianliyisheng.com/api/site/getprovincedata";
        HttpClient client = HttpClients.createDefault();
        //默认post请求
        HttpPost post = new HttpPost(url);
        //拼接多参数
        JSONObject json = new JSONObject();
        json.put("uid", "79");
        json.put("key", "d86e33fb43036df9f9c29ff8085ac653");
        json.put("timestamp", "1562296283");
        json.put("typekey", "wshh");

        try {
            post.addHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(json.toString(), Charset.forName("utf-8")));
            HttpResponse httpResponse = client.execute(post);

            HttpEntity entity = httpResponse.getEntity();
            System.err.println("状态:" + httpResponse.getStatusLine());
            System.err.println("参数:" + EntityUtils.toString(entity));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

