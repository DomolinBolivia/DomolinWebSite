package com.domolin.website.querys;

import com.domolin.database.annot.JpqlQuery;
import com.domolin.database.annot.PersistEntity;
import com.domolin.database.annot.QueryRepository;
import com.domolin.database.annot.SqlNativeQuery;
import com.domolin.database.annot.SqlParam;
import com.domolin.database.entities.SentinelApp;
import java.math.BigInteger;

@QueryRepository
public interface SentinelAppQuerys {
    @SqlNativeQuery(type = BigInteger.class, sql = "SELECT nextval('code_sequence')")
    public BigInteger generateCode();
    
    @JpqlQuery(queryName = SentinelApp.Q_SENTAPP_BY_CODE,entityGraph = SentinelApp.G_SENTAPP_ONLY_ROW)
    public SentinelApp findByCode(@SqlParam("code") String code);
    
    @PersistEntity
    public void register(SentinelApp sentinelApp);
}