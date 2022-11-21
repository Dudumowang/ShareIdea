package com.example.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class TokenUtils {

    //生成token
    public static String genToken(String userId,String sign){
        return JWT.create().withAudience(userId).
                withExpiresAt(DateUtil.offsetHour(new Date(),2)) //2小时后过期
                .sign(Algorithm.HMAC256(sign));
    }
}
