import VendorTypes.VendorTypeClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class OllamaLLMModel extends LLMModelClass{
    private String convertResponseToString(String response){
        String ret = "";
        String[] responseParts = response.split("\"");
        for(int i=0; i<responseParts.length-2; i++){
            if(responseParts[i].equals("response") && responseParts[i+1].equals(":"))
                ret = ret.concat(responseParts[i+2]);
        }
        return ret;
    }
    public String ask(ArrayList<VendorTypeClass> datas){
        String prompt = "please read these informations and answer the question. do not, and i repeat, do not say anything more than the answer to the question. start of datas : { ";
        for(int i=0; i<datas.size(); i++){
            prompt = prompt.concat("%d-th data: ".formatted(i) + datas.get(i).describe()+" end of %d-th data. ".formatted(i));
        }
        prompt = prompt.concat(" } end of datas. question: what conclusion can you have from these datas?");

        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String requestBody = "{\"model\": \"llama3.2:1b\", \"prompt\": \"%s\"}".formatted(prompt);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if(responseCode != 200)
                return "unable to reach ollama!";

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return convertResponseToString(response.toString());
            }finally {
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not connected!";
    }
}
