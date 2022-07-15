package com.ll.exam;

import java.util.HashMap;

public class Request {
    String url;
    String queryStr;
    String path;

    HashMap<String, String> queryParams;

    public Request(String url) {
        this.url = url;
        String[] urlBits = url.split("\\?", 2);
        this.path = urlBits[0];
        queryParams = new HashMap<>();
        if(urlBits.length == 2){
            this.queryStr = urlBits[1];

            String[] paramBits = queryStr.split("&");

            for (String paramBit : paramBits) {
                String[] paramAndValue = paramBit.split("=");

                if(paramAndValue.length == 1)
                    continue;

                String paramName = paramAndValue[0].trim();
                String paramValue = paramAndValue[1].trim();
                queryParams.put(paramName, paramValue);


            }
        }



    }

    /**
     * 삭제?id=N & ~
     */
    public Long getIntParm(String paramName, Long defaultValue){

        if(!queryParams.containsKey(paramName))
            return defaultValue;

        Long Id = Long.parseLong(queryParams.get(paramName));
        return Id;

    }


    public String getPath() {
        String[] urlBits = url.split("\\?", 2);
        return urlBits[0];
    }

}
