package com.news;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class NewsServlet extends HttpServlet {

    private static final String API_KEY = "YOUR_API_KEY";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String apiUrl = "https://newsapi.org/v2/top-headlines?country=in&apiKey=" + API_KEY;

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        request.setAttribute("newsData", content.toString());
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
