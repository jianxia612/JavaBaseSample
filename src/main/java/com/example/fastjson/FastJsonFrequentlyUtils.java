package com.example.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.fastjson.model.Person;

import java.util.List;
import java.util.Map;

/**
 * @Classname FastJsonFrequentlyUtils
 * @Description 基础Fastjson总结
 * @Date 2019/11/30 9:27
 * @Created by jianxiapc
 */
public class FastJsonFrequentlyUtils {

    /**
     * json字符串转换为json后 以及key值获得JsonArray
     * @param jsonString
     * @param dataArrayKey
     * @return
     */
    public static JSONArray convertStringToJSONArray(String jsonString,String dataArrayKey){
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONArray dataJsonArray = jsonObject.getJSONArray(dataArrayKey);
        return dataJsonArray;
    }

    /**
     * 字符串转换为JsonArray
     * @param jsonArrayString
     * @return
     */
    public static JSONArray convertStringToJSONArray(String jsonArrayString){
        JSONArray jsonArray = JSONArray.parseArray(jsonArrayString);
        return jsonArray;
    }

    public static JSONObject convertStringToJSONObject(String jsonString){
        JSONObject jsonObj = JSONObject.parseObject(jsonString);
        return jsonObj;
    }

    /**
     *
     * @param jsonArray
     * @param index
     * @return
     */
    public static JSONObject getJSONObjectFromJsonArray(JSONArray jsonArray,int index){
        JSONObject jsonData = new JSONObject();
        for(int i=0;i<jsonArray.size();i++){
            if(i==index) {
                jsonData = jsonArray.getJSONObject(i);
            }
        }
        return jsonData;
    }

    /**
     * fastjson  字符串转List
     * @param jsonString
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> List<T> convertStringToList(String jsonString,Class<T> bean){
        List<T> list = JSONObject.parseArray(jsonString,bean);
        return list;
    }

    /**
     * 自定义对象转化成json格式的字符串
     * @param bean
     * @param <T>
     * @return
     */
    public static<T>  String convertBeantoJSONString(T bean){
        String peopleJson = JSON.toJSONString(bean);
        System.out.println(peopleJson);
        return peopleJson;
    }
    /**
     * fastjson  List转JSONArray
     * @param listData
     * @return
     */
    public  static JSONArray convertListToJSONArray(List listData){
        JSONArray jsonArray= JSONArray.parseArray(JSON.toJSONString(listData));
        return jsonArray;
    }

    /**
     * 针对JsonArray转换为对应的Bean对象
     * fastjson  JSONArray转List
     * @param jsonArray
     * @param bean
     * @return
     */
    public  static <T> List<T>  convertJSONArrayToList(JSONArray jsonArray, Class<T> bean) {
        List<T>  list = JSONObject.parseArray(jsonArray.toJSONString(), bean);
        return list;
    }

    /**
     * JSONObject转化成Map集合
     * @param jsonString
     * @return
     */
    public  static Map  convertJSONStringToMap(String jsonString){
        Map map = JSONObject.parseObject(jsonString, Map.class);
        return map;
    }
    /**
     * JSONObject转换为Map对象
     * @param jsonObj
     * @return
     */
    public  static Map<String, Object> convertJSONToMap(JSONObject jsonObj){
        /**
         JSONObject obj = new JSONObject();
         {
         obj.put("key1", "value1");
         obj.put("key2", "value2");
         obj.put("key3", "value3");
         * }
         */
        Map<String, Object> resultMap = JSONObject.parseObject(jsonObj.toJSONString(), new TypeReference<Map<String, Object>>(){});
        return resultMap;
    }

    public static void main(String[] args) {

        //1、 convertStringToJSONObject
         String jsonString="{'name':'蜀山剑侠','id':'007','age':37}";
        JSONObject jsonObject=convertStringToJSONObject(jsonString);
        System.out.println("1 StringToJSONObject: \n"+jsonObject);

        //2、JSONObject转化成自定义类对象
        Person person = JSONObject.parseObject(jsonString, Person.class);
        System.out.println(person.toString());

        //3、convertJSONToMap
        Map map =convertJSONStringToMap(jsonString);
        System.out.println("3 "+map);

        //4. 自定义对象转化成json格式的字符串
        Person personObj =new Person();
        personObj.setId("008");
        personObj.setAge(18);
        personObj.setName("燕子李三");
        String peopleJson =convertBeantoJSONString(personObj);
        System.out.println("4 peopleJson:\n"+peopleJson);

        //5. String类型转化成JSONObject;
        String str = "{\"result\":\"success\",\"message\":\"成功！\"}";
        JSONObject jsonObj=convertStringToJSONObject(str);
        System.out.println(" 5 jsonObj:\n"+jsonObj);

        //6.1. JSONObject转化成JSONArray的两种方式
        String containJsonArrayJsonStr = "{\"result\":\"success\",\"message\":\"成功！\",\"data\":[{\"name\":\"怪侠一枝梅\",\"age\":\"36\"}]}";

        JSONArray dataJsonArray=convertStringToJSONArray(containJsonArrayJsonStr,"data");
        System.out.println("6.1 data jsonArray:\n"+dataJsonArray);
        //6.2  直接传输JsonArray 字符串对象返回JsonArray
        String jsonArrayStr="[{\"name\":\"怪侠一枝梅\",\"age\":\"36\"}]";
        JSONArray dataJsonArrayObj= convertStringToJSONArray(jsonArrayStr);
        System.out.println("6.2 dataJsonArrayObj :\n"+dataJsonArrayObj);

        //7. jsonArray转化成JSONObject并取出其中的元素数据
        JSONObject jsonObj0=getJSONObjectFromJsonArray(dataJsonArrayObj,0);
        String name = jsonObj0.getString("name");
        System.out.println("7 name :"+name);
        System.out.println("7 jsonObj0 :\n"+jsonObj0);

        //8. convertJSONToMap 转换JSON为Map
       Map mapObj=convertJSONStringToMap(jsonString);
       System.out.println("8. map: "+mapObj.toString());

       //9. convertJSONToMap 转换JSON对象为Map
        Map jsonMapObj=convertJSONToMap(jsonObj);
        System.out.println("9. jsonMap: "+jsonMapObj.toString());
        // 10. convertJSONArrayToList
        String jsonArrayListStr="[{\"name\":\"怪侠一枝梅\",\"age\":\"36\",\"id\":\"007\"},{\"name\":\"蜀山剑侠\",\"age\":\"28\",\"id\":\"008\"}]";
        JSONArray jsonListArray=convertStringToJSONArray(jsonArrayListStr);
        List<Person> personList=convertJSONArrayToList(jsonListArray,Person.class);
        System.out.println("10. personList "+personList.toString());

        //11. convertListToJSONArray
        JSONArray personJsonArray=convertListToJSONArray(personList);
        System.out.println("11. personJsonArray "+personJsonArray.toString());

        //12. convertStringToList
        List<Person> personList2=convertStringToList(jsonArrayListStr,Person.class);
        System.out.println("12. personListOne "+personList2.toString());
        /**
        String jsonArrayStr = "";
        JSONArray jsonArray=convertStringToJSONArray(jsonArrayStr);
        System.out.println("jsonArray: \n"+jsonArray.toJSONString());
         */
    }


}
