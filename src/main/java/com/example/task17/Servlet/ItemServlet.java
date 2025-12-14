package com.example.task17.Servlet;

import com.example.task17.Service.ItemService;
import com.example.task17.Model.Item;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/items/*")
public class ItemServlet extends HttpServlet {

    private ItemService itemService;

    @Override
    public void init() throws ServletException {
        super.init();
        itemService = new ItemService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if ("/getAll".equals(path)) {
            List<Item> items = itemService.getAllItems();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(new Gson().toJson(items));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdStr = request.getParameter("id");
        if (itemIdStr != null) {
            Long itemId = Long.parseLong(itemIdStr);
            boolean isDeleted = itemService.deleteItemById(itemId);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            if (isDeleted) {
                out.println("{\"message\":\"Item deleted successfully\"}");
            } else {
                out.println("{\"message\":\"Item not found\"}");
            }
        }
    }
}
