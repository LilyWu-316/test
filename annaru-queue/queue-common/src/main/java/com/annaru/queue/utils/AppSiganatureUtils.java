package com.annaru.queue.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppSiganatureUtils {

    protected static final Logger logger = LoggerFactory.getLogger(AppSiganatureUtils.class);

	private static long EXPIRE_IN_SECONDS=3600;//认证签名有效时间 1小时
	private static String SAPARATOR="@$@";
	public static String createSiganature(String data, String appid,String appSecret,long timestamp) {
        String plain=appid+SAPARATOR+appSecret+SAPARATOR+data+SAPARATOR+timestamp+SAPARATOR+appSecret+SAPARATOR+appid;
        logger.info("加密前：" + plain);
        String siganature = encrypt(plain,"SHA-512");
        logger.info("加密后：" + siganature);
        return siganature;
	}


	public static boolean validateSiganature(String data,String appid,String appSecret,long timestamp,String siganature){
		try{
			String signNow=createSiganature(data, appid, appSecret, timestamp);
			if(signNow!=null)
			    return (System.currentTimeMillis()-timestamp)/1000<EXPIRE_IN_SECONDS&&signNow.equalsIgnoreCase(siganature);
			else
			    return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}



	private static String encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt=null;
		try {
			bt = strSrc.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static void main(String args[]){
    	//String appid="test001";
    	//String appSecret="2770bcb4-7e35-498d-b83e-fe2d5377cf74";
    	//long timestamp=System.currentTimeMillis();
    	//String data="{'targetUrl':'http://www.sina.com?method=xxxx','mobile':'18616598806','name':'易加醫測試賬號1','sex':0,'birthday':'1982-01-22','idcard':'310228198201222222','socialSecurityCard':'1234567','logoPath':'http://xxxxx.jpg'}";
    	//String siganature=createSiganature(data, appid, appSecret, timestamp);
         //System.out.println(siganature);
         //System.out.println(validateSiganature(data, appid, appSecret, timestamp, siganature));
         //System.out.println(timestamp);

        String oppo=encrypt("有怪兽","SHA-512");
        System.out.println(oppo);
        long as =  (System.currentTimeMillis()-0);

        System.out.println(as);

/*
    	String signNow="cbcd24895106eef653a80b0ba0940a2fa93e7ff1dbd3a4ca5a468c85645188030cf40d4704037df544fcd8bb373ebc0e9cd4f50b26b9e538979c6ac4f78a8bff";
    	String signRequest="CBCD24895106EEF653A80B0BA0940A2FA93E7FF1DBD3A4CA5A468C85645188030CF40D4704037DF544FCD8BB373EBC0E9CD4F50B26B9E538979C6AC4F78A8BFF";
    	System.out.println(signNow.equalsIgnoreCase(signRequest));
    	System.out.println(new Date(1446738417));*/

    }

}
