package helper;

import com.google.gson.Gson;
import models.Email;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONReadFromFile {

    String fileAddress;

    public JSONReadFromFile(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public List<Email> ParseJsonEmail() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(fileAddress));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("userArray");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            Iterator<JSONObject> iterator = companyList.iterator();
            Gson gson = new Gson();

            List<Email> emailList = new ArrayList<>();

            while (iterator.hasNext()) {
                Email email = gson.fromJson(iterator.next().toJSONString(), Email.class);
                emailList.add(email);
            }
            return emailList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}