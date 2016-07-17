package com.sina.retrofit_easysourceexpress.newwork.parse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wuzp on 2016/6/1.
 */
public class ExpressList implements Serializable {
    public int showapi_res_code;
    public String showapi_res_error;
    public ExpressBeanList showapi_res_body;

    public class ExpressBeanList implements Serializable {
        public List<ExpressBean> expressList;
        public boolean flag;
        public int ret_code;

        public class ExpressBean implements Serializable{
            public String expName;
            public String imgUrl;
            public String itf1;
            public String itf2;
            public String itf3;
            public String itf4;
            public String note;
            public String phone;
            public String simpleName;
            public String url;

            @Override
            public String toString() {
                return "{expName:"+expName+";imgUrl:"+ imgUrl +"; itf1:"+ itf1 +"; itf2:"+ itf2
                        +"; itf3:"+ itf3 +"; itf4:"+ itf4 +"; note:"+ note +"; phone:"+ phone
                        +"; simpleName:"+ simpleName +"; url:"+ url +" }";
            }
        }
    }
}
