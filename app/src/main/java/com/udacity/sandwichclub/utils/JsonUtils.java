package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String sandwich_Name = "name";
    private static final String sandwich_MainName = "mainName";
    private static final String sandwich_AlsoKnownAs = "alsoKnownAs";
    private static final String sandwich_PlaceOfOrigin = "placeOfOrigin";
    private static final String sandwich_Description = "description";
    private static final String sandwich_Image = "image";
    private static final String sandwich_Ingredients="ingredients";


    public static Sandwich parseSandwichJson(String json) {

        ArrayList<String> alsoKnownAs = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();


        String mainName = null;
        String placeOfOrigin = null;
        String description = null ;
        String image = null ;

        try {
            JSONObject wholeObject = new JSONObject(json);

            placeOfOrigin = wholeObject.optString(sandwich_PlaceOfOrigin);
            description   = wholeObject.optString(sandwich_Description);
            image         = wholeObject.optString(sandwich_Image);
            ingredients   = JSONArrayToList(wholeObject.getJSONArray(sandwich_Ingredients));

            JSONObject nameObject = wholeObject.getJSONObject(sandwich_Name);

            mainName    = nameObject.optString(sandwich_MainName);
            alsoKnownAs = JSONArrayToList(nameObject.getJSONArray(sandwich_AlsoKnownAs));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }


    private static ArrayList<String> JSONArrayToList(JSONArray jsonArray) throws JSONException {
        ArrayList<String> list = new ArrayList<String>();

        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i=0;i<len;i++){
                list.add(jsonArray.get(i).toString());
            }
        }
        return list;
    }
}
