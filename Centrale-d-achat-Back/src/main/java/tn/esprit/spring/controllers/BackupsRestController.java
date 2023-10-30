package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IBackupsManager;
import tn.esprit.spring.repositories.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/backups")

public class BackupsRestController {

    private class dataa{
        private String request;

        public dataa() {
        }

        public dataa(String request) {
            this.request = request;
        }

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }
    }
    @Autowired
    IBackupsManager backupsManager;
    @Autowired
    private LivreurRepository livreurRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/show")
    public List<String> getBackups() throws IOException {
        Process process1 = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","dir /b C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\backups\\"});
        List<String> backupsFileNames = new ArrayList<>();
        final InputStream stream = process1.getInputStream();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(stream));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        backupsFileNames.add(line);
                        System.out.println("one\n");
                    }
                } catch (Exception e) {
                    // TODO
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            // ignore
                        }
                    }
                }
        System.out.println(backupsFileNames.size());
        return backupsFileNames;
    }

    @GetMapping("/restore/{filename}")
    public void restoreDatabase(@PathVariable("filename") String filename) throws IOException {
        System.out.println("Deleting current Database..");
        Process process2 = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","mysqladmin -u root drop -f pidev1"});
        System.out.println("Database successfully deleted..");
        System.out.println("Initiating Database Recovery..");
        Process process1 = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","mysql -u root -e \"source backups\\"+filename+"\""});
        System.out.println("Database successfully recovered..");
    }

    @GetMapping("/deleteDatabase/")
    public void delete() throws IOException {
        Process process1 = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","mysqladmin -u root drop -f pidev1"});
        List<String> backupsFileNames = new ArrayList<>();
        final InputStream stream = process1.getInputStream();
    }

    @GetMapping("/health/facture")
    public List<Facture> getFactureHealth(){
        return backupsManager.checkFacture();
    }


    @GetMapping("/health/replication")
    public List<String> getReplicatedData(){
        List<String> replicatedData = new ArrayList<>();
        replicatedData.add("Section livreur");
        List<Livreur> listl1 = livreurRepository.findAll();
        List<Livreur> listl2 = livreurRepository.findAll();
            for(Livreur l1 : listl1){
                for(Livreur l2 : listl2){
                    if(l1.getLivreurId()!=l2.getLivreurId()){
                        if(l1.getNumLivreur().equals(l2.getNumLivreur())){
                            replicatedData.add(l1.toString());
                        }
                    }
                }
            }
        return replicatedData;
    }

    @GetMapping("/health/error/g/{type}/{url}")
    private static void writeUsingOutputStream(@PathVariable("type") Integer type,
                                               @PathVariable("url") String url) throws IOException {
        OutputStream os = null;
        Writer output;
        String path;
        Integer existing=0;
        switch (type){
                case 1 : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\AE.txt";
                            break;
                case 2 : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\BE.txt";
                            break;
                case 3 : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\PE.txt";
                            break;
                default : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\UE.txt";
                            break;
            }


        output = new BufferedWriter(new FileWriter(path, true));
        String request="fefezf";
        output.append("\n"+url+"@"+ LocalDate.now());
        output.close();
    }


    @GetMapping("/health/error/log/{type}")
    private static List<String> getErrorLogs(@PathVariable("type") Integer type) throws IOException {
        String path;
        Integer lines = 0;
        List<String> content = new ArrayList<>();
        String line;

        switch (type){
            case 1 : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\AE.txt";
                break;
            case 2 : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\BE.txt";
                break;
            case 3 : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\PE.txt";
                break;
            default : path="C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\UE.txt";
                break;
        }

        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine())!=null){
            content.add(line);
        }
        reader.close();
        return content;
    }


    @GetMapping("/health/request/{type}")
    public Integer getErrorCount(@PathVariable("type") Integer type) throws IOException {
        String path;
        Integer existing = 0;
        switch (type) {
            case 1:
                path = "C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\AE.txt";
                break;
            case 2:
                path = "C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\BE.txt";
                break;
            case 3:
                path = "C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\PE.txt";
                break;
            default:
                path = "C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\logs\\UE.txt";
                break;
        }

        BufferedReader reader = new BufferedReader(new FileReader(path));
        Integer lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
}


