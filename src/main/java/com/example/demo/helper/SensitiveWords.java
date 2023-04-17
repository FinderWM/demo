package com.example.demo.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * @date: 2022/3/15
 */
public class SensitiveWords {

    public static Map addSensitiveWordToHashMap(List<String> sensitiveWords) {
        Set<String> keyWordSet = new HashSet<>();
        keyWordSet.addAll(sensitiveWords);
        Map sensitiveWordMap = new HashMap(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(keyChar);

                if(wordMap != null){
                    nowMap = (Map) wordMap;
                }
                else{
                    newWorMap = new HashMap<>();
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");
                }
            }
        }
        return sensitiveWordMap;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Map words = SensitiveWords.addSensitiveWordToHashMap(Collections.singletonList("什么啊1"));

        System.out.println(new ObjectMapper().writeValueAsString(words));
    }
}
