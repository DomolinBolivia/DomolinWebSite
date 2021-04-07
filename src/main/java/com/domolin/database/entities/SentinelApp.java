package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseSentinelApp;
import com.domolin.database.util.QueryUtil;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedEntityGraphs({
    @NamedEntityGraph(includeAllAttributes = false, name = SentinelApp.G_SENTAPP_ONLY_ROW),
})
@NamedQueries({
    @NamedQuery(name = SentinelApp.Q_SENTAPP_BY_CODE, query = "SELECT sa FROM SentinelApp sa WHERE sa.code=:code")
})
@javax.persistence.Entity
@Table(name = "sentinel_app")
public class SentinelApp extends BaseSentinelApp{
    public static final String G_SENTAPP_ONLY_ROW = "G_SENTAPP_ONLY_ROW";
    public static final String Q_SENTAPP_BY_CODE = "Q_SENTAPP_BY_CODE";
    
    private final static long BASE_CODE = 61;
    private final static char HEX_CHARS_CODE_BASE[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    public static void registerSentinelApp(String code,String hashSentinelJar, int version, String versionName){
        BaseSentinelApp sentinelApp = new SentinelApp();
        sentinelApp.setGenerationDate(new Date());
        sentinelApp.setHash_app(hashSentinelJar);
        sentinelApp.setCode(code);
        sentinelApp.setVersion(version);
        sentinelApp.setVersionName(versionName);
        
        QueryUtil.insert(sentinelApp);
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
