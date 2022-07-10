package com.example.oopmain.util;

import com.example.oopmain.constant.NameOutputDataConstant;

public class ShowUtility {
    public static String getFileName(String topic){
        return NameOutputDataConstant.namesAndTopics.get(topic);
    }
}
