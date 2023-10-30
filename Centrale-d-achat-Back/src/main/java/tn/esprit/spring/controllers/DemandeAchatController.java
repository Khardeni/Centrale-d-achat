package tn.esprit.spring.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.DemandeAchat;
import tn.esprit.spring.interfaces.IDemandeAchat;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/demande-achat")
public class DemandeAchatController {

    IDemandeAchat iDemandeAchat;

    @PostMapping("/add-demande-achat")
    @ResponseBody
    public void addDemandeAchat(@RequestBody DemandeAchat demandeAchat){
        iDemandeAchat.addDemandeAchat(demandeAchat);
        sendSMSNotification(demandeAchat);
    }
    private void sendSMSNotification(DemandeAchat demandeAchat) {
        try {
            // Create a new URL object with the TextLocal API endpoint
            URL url = new URL("https://api.txtlocal.com/send/");

            // Create a new HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Construct the POST request parameters
            Map<String, String> params = new HashMap<>();
            params.put("apikey", "Njg2NDUxNGY0YjU4NjE2OTQ1NGY1MTMxNmUzNTRlNmM=");
            params.put("numbers", "21656532009");
            params.put("message", "New DemandeAchat added:\n" +
                    "Titre: " + demandeAchat.getTitreDA() + "\n" +
                    "Description: " + demandeAchat.getDescDA() + "\n" +
                    "Date de publication: " + demandeAchat.getDatePublication() + "\n" +
                    "Date dexpiration: " + demandeAchat.getDateExpiration() + "\n" +
                    "Statut: " + demandeAchat.getStatusDA());
            params.put("sender", "TheAdam");

            // Encode the parameters and write them to the output stream
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(params));
            writer.flush();
            writer.close();
            os.close();

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the response from the server
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }



    @PutMapping("/edit-demande-achat")
    @ResponseBody
    public  DemandeAchat ediDemandeAchat(@RequestBody DemandeAchat demandeAchat){
      return   iDemandeAchat.editDemandeAchat(demandeAchat);

    }

    @DeleteMapping("/delete-demande-achat/{id-demande-achat}")
    @ResponseBody
    public String deleteDemandeAchat(@PathVariable("id-demande-achat") Integer id ){

        iDemandeAchat.deleteDemandeAchat(id);

        return "Entry Deleted";

    }
    @GetMapping("/get-all-demande-achat")
    public List<DemandeAchat>  getAllDemandeAchat(){
        return  iDemandeAchat.getAllDemandesAchat();

    }

    @GetMapping("/get-demande-achat-by-id/{id-demande-achat}")

    public DemandeAchat getDemandeAchatById(@PathVariable("id-demande-achat") Integer id){
        return  iDemandeAchat.getDemandeAchatById(id);
    }

}
