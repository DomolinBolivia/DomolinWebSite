package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseSentinelApp;
import com.domolin.database.util.QueryUtil;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Query;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "sentinel_app")
public class SentinelApp extends BaseSentinelApp{
    private final static long BASE_CODE = 61;
    private final static char HEX_CHARS_CODE_BASE[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    public static BigInteger generateCode(){
        Query query = QueryUtil.getDBConnector().getEntityManager().createNativeQuery("SELECT nextval('code_sequence')");
        BigInteger code = (BigInteger)query.getSingleResult();
        return code;
    }
    
    public static void registerSentinelApp(String code,String hashSentinelJar, int version, String versionName){
        BaseSentinelApp sentinelApp = new SentinelApp();
        sentinelApp.setGenerationDate(new Date());
        sentinelApp.setHash_app(hashSentinelJar);
        sentinelApp.setCode(code);
        sentinelApp.setVersion(version);
        sentinelApp.setVersionName(versionName);
        
        QueryUtil.insert(sentinelApp);
    }
    
    public static SentinelApp find(String code){
        SentinelApp sentinelApp = QueryUtil.findOneByColumn(SentinelApp.class,"code", code);
        return sentinelApp;
    }
        
    public static String encodeCode(BigInteger decimalBig) {
        long decimal = decimalBig.longValue();
        int rem;
        String hex = "";
        while (decimal > 0l) {
            rem = (int) (decimal % BASE_CODE);
            hex = HEX_CHARS_CODE_BASE[rem] + hex;
            decimal = decimal / BASE_CODE;
        }
        return hex;
    }

    public static long decodeCode(String numberCode) {
        long val = 0;
        for (int i = 0; i < numberCode.length(); i++){
            char c = numberCode.charAt(i);
            int d = HEX_CHARS_CODE_BASE[c];
            val = BASE_CODE*val + d;
        }
        return val;
    }
}
