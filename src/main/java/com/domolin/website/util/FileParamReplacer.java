package com.domolin.website.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParamReplacer {

    private final static String PATTERN_STR = "\\{\\{([a-z_A-Z][a-zA-z_.\\d]*)\\}\\}+";
    
    private FileParamReplacer(){
        
    }
    
    public static void replaceParams(Map<String,String> map, File source, File destination) throws IOException{
        Pattern pattern = Pattern.compile(PATTERN_STR);
        Matcher m = pattern.matcher(readAllText(source));
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            if(map!=null && map.containsKey(m.group(1))){
                m.appendReplacement(sb, map.get(m.group(1)));
            }else{
                m.appendReplacement(sb,"");
            }
        }
        m.appendTail(sb);
        
        if(!destination.exists())
            destination.createNewFile();
        Files.write(destination.toPath(),sb.toString().getBytes(),StandardOpenOption.WRITE);
    }
    
    public static String readAllText(File file) throws IOException{
        String result = Files.readString(Path.of(file.getAbsolutePath()));
        return result;
    }
}
