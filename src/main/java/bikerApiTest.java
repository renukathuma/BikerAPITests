import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gherkin.deps.com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.InputStream;

public class bikerApiTest {

    private static String getcitybikerInfoURL = "http://api.citybik.es/v2/networks";

    private static HttpResponse<JsonNode> response;

//    private static String name = "";
//    private static Double price;


    public static void getcitybikeinfo() throws UnirestException {

        response = Unirest.get(getcitybikerInfoURL).asJson();

        }

        public static Boolean validateResponseCode(){
         System.out.println("Status -->" + response.getStatus());
         System.out.println("getbikerLocation reponse body -->" + response.getBody());
         return response.getStatus()==200;

        }
       public static Boolean validatecountrycodeforcity(String city, String code) throws UnirestException {

        JSONArray networkArray = response.getBody().getArray().getJSONObject(0).getJSONArray("networks");
        String countryResponse="";

        String[] length = new String[networkArray.length()];
           //System.out.println("json[1]->"+networkArray.getJSONObject(0).getJSONArray("networks").getJSONObject(0).getJSONObject("location").getString("country"));
           for(int i=0; i<networkArray.length();i++ ){
               String cityResponse = networkArray.getJSONObject(i).getJSONObject("location").getString("city");
               if(cityResponse.contentEquals(city)){
                   countryResponse = networkArray.getJSONObject(i).getJSONObject("location").getString("country");
               break;
               }
           }
           return countryResponse.contentEquals(code);

    }

    public static Boolean getLatLongforCity(String city) throws UnirestException {
        Boolean flag = false;
        JSONArray networkArray = response.getBody().getArray().getJSONObject(0).getJSONArray("networks");
        Double latitudeResponse=null;
        Double longitudeResponse=null;

        String[] length = new String[networkArray.length()];
        //System.out.println("json[1]->"+networkArray.getJSONObject(0).getJSONArray("networks").getJSONObject(0).getJSONObject("location").getString("country"));
        for(int i=0; i<networkArray.length();i++ ){
            String cityResponse = networkArray.getJSONObject(i).getJSONObject("location").getString("city");
            if(cityResponse.contentEquals(city)){
                latitudeResponse = networkArray.getJSONObject(i).getJSONObject("location").getDouble("latitude");
                longitudeResponse = networkArray.getJSONObject(i).getJSONObject("location").getDouble("longitude");

                break;

            }
        }
        if(latitudeResponse!=null && longitudeResponse!=null){
            flag = true;
        }
        System.out.println("Lattitude for city"+city+" is "+latitudeResponse);
        System.out.println("Longitude for city"+city+" is "+latitudeResponse);
        return flag;

    }

}


