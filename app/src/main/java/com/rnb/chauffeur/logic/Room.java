package com.rnb.chauffeur.logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;

public class Room {

    /**
     *
     *
     * @return
     */
    public String createRoom() throws MalformedURLException {

        String roomCode = "";
        StringBuilder response = new StringBuilder();

        String url = "http://192.168.254.69:5000/create";
        URL url1 = new URL(url);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    HttpURLConnection con = (HttpURLConnection)url1.openConnection();
                    con.setRequestMethod("GET");
                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    System.out.println("Response: " + response);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Return Response: " + response);

        thread.start();

        while(true) {
            if (!thread.isAlive()) {
                response.deleteCharAt(response.length() - 1);
                response.deleteCharAt(0);
                return response.toString();
            }
        }

    }

    /**
     *
     * @param roomNumber the room id
     * @return -1 if room not found, 0 if leader, index otherwise
     */
    public int joinRoom(String roomNumber) throws MalformedURLException {

        final int[] roomIndex = {-1};

        String url = "http://192.168.254.69:5000/join/" + roomNumber;
        URL url1 = new URL(url);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    HttpURLConnection con = (HttpURLConnection)url1.openConnection();
                    con.setRequestMethod("GET");
                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        roomIndex[0] = Integer.parseInt(inputLine);
                    }
                    System.out.println("Index: " + roomIndex[0]);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Return Response: " + roomIndex[0]);

        thread.start();

        while(true) {
            if (!thread.isAlive()) {
                return roomIndex[0];
            }
        }
    }

    /**
     *
     * @param roomNumber
     * @return the leader's name and a header
     */
    public static String getLeader(String roomNumber) throws IOException {

        String leaderName = "";
        StringBuilder response = new StringBuilder();

        String url = "http://192.168.254.69:5000/leader/" + roomNumber;
        URL url1 = new URL(url);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    HttpURLConnection con = (HttpURLConnection)url1.openConnection();
                    con.setRequestMethod("GET");
                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    System.out.println("Response: " + response);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        while(true) {
            if (!thread.isAlive()) {
                return ("Leader: " + response);
            }
        }
    }

    /**
     *
     * @param roomNumber
     * @return a list of all users other than the leader
     */
    public static int getUsers(String roomNumber) throws MalformedURLException {

        int[] roomIndex = {-1};

        String url = "http://192.168.254.69:5000/users/" + roomNumber;
        URL url1 = new URL(url);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    HttpURLConnection con = (HttpURLConnection)url1.openConnection();
                    con.setRequestMethod("GET");
                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        roomIndex[0] = Integer.parseInt(inputLine);
                    }
                    System.out.println("Index: " + roomIndex[0]);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Return Response: " + roomIndex[0]);

        thread.start();

        while(true) {
            if (!thread.isAlive()) {
                return roomIndex[0];
            }
        }
    }


    public static boolean startCheck(String roomNumber) throws MalformedURLException, JSONException {

        String url = "http://192.168.254.69:5000/statusCheck/" + roomNumber;
        URL url1 = new URL(url);

        final JSONObject[] status = new JSONObject[1];

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    HttpURLConnection con = (HttpURLConnection)url1.openConnection();
                    con.setRequestMethod("GET");
                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        status[0] = new JSONObject(inputLine);
                    }
                    System.out.println("Index: " + status[0]);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JSONArray requestArray = status[0].getJSONArray("results");

        System.out.println("Return Response: " + requestArray.get(2));

        thread.start();

        while(true) {
            if (!thread.isAlive()) {
                return requestArray.get(2) != "";
            }
        }
    }

}
