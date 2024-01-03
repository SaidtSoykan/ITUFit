package groupeighteen.itufit.application.services.meal;

import org.springframework.stereotype.Service;

import groupeighteen.itufit.application.persistence.repositories.MealRepository;
import groupeighteen.itufit.application.shared.response.IResponse;
import groupeighteen.itufit.application.shared.response.Response;
import groupeighteen.itufit.domain.meal.Meal;

import java.io.BufferedReader;
import java.io.IOException;
// import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
import java.net.URL;
// import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;

@Service
public class MealServiceImp implements MealService {
    private MealRepository mealRepository;

    public MealServiceImp(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }

    public IResponse fetchData(){
        // String url = "https://sks.itu.edu.tr/anasayfa/yemek-listesi";
        String url = "https://sks.itu.edu.tr/ExternalPages/sks/yemek-menu-v2/uzerinde-calisilan/yemek-menu.aspx";

        Double calorie = 0.0;

        try {
            // Step 1: Make an HTTP request
            String htmlContent = sendGetRequest(url);

            // Step 2: Parse HTML content
            if (htmlContent != null) {
                Document document = Jsoup.parse(htmlContent);
                document.outputSettings().charset("UTF-8");

                Element calorieElem = document.getElementById("lbKalori");
                String calorieText = calorieElem.text();
                calorieText = calorieText.replace(",",".");
                calorie = Double.parseDouble(calorieText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(calorie == 0){
            throw new RuntimeException();
        }
        mealRepository.deleteAll();// ToDo: Check if deleting al meal info is a good idea

        Meal mealToAdd = new Meal();
        mealToAdd.setCalorie(calorie);
        mealRepository.save(mealToAdd);
        // ToDo: Meal fetch is only done for 1 day, make this at least a week. Meal date has not been included.

        return new Response<>(true, "");
    }

    private static String sendGetRequest(String url) throws IOException {
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        // Set the request method
        httpClient.setRequestMethod("GET");

        // Get the response code
        int responseCode = httpClient.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("GET request failed. Response Code: " + responseCode);
            return null;
        }
        
        // Read the response content
        BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        return response.toString();
            
    }
}
