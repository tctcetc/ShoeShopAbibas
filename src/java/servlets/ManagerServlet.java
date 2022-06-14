/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Brand;
import entity.Shoes;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import session.BrandFacade;
import session.ShoesFacade;
import session.UserRolesFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    
    "/addShoes", 
    "/createShoes", 
    "/editShoes", 
    "/updateShoes", 
    "/addBrand", 
    "/createBrand",
    "/editBrand",
    "/updateBrand",
    
})
@MultipartConfig
public class ManagerServlet extends HttpServlet {
    
    @EJB private ShoesFacade shoesFacade;
    @EJB private BrandFacade brandFacade;
    @EJB private UserRolesFacade userRolesFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        
        if(!userRolesFacade.isRole("MANAGER", authUser)){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            
            case "/addShoes":
                List<Brand> brands = brandFacade.findAll();
                request.setAttribute("brands", brands);
                request.getRequestDispatcher("/addShoes.jsp").forward(request, response);
                break;
            case "/createShoes":
                String pathToDir = "D:\\UploadDir\\ShoeShopWebPU";
                //String pathToDir = "C:\\UploadDir\\ShoeShopWebPU";
                Part part = request.getPart("photo");
                String filename = getFileName(part);
                String pathToFile = pathToDir+File.separator+filename;
                File file = new File(pathToFile);
                file.mkdirs();
                try(InputStream fileContent = part.getInputStream()){
                    Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                String name = request.getParameter("name");
                String[] shoesBrands = request.getParameterValues("brands");
                String year = request.getParameter("year");
                String price = request.getParameter("price");
                Shoes shoes = new Shoes();
                shoes.setName(name);
                List<Brand> listShoesBrands= new ArrayList<>();
                for (int i = 0; i < shoesBrands.length; i++) {
                    String brandId = shoesBrands[i];
                    listShoesBrands.add(brandFacade.find(Long.parseLong(brandId)));
                }
                shoes.setBrands(listShoesBrands);
                shoes.setYear(Integer.parseInt(year));
                shoes.setPrice(price);
                shoes.setPhoto(pathToFile);
                shoesFacade.create(shoes);
                request.getRequestDispatcher("/addShoes.jsp").forward(request, response);
                break;
            case "/editShoes":
                String shoesId = request.getParameter("shoesId");
                shoes = shoesFacade.find(Long.parseLong(shoesId));
                request.setAttribute("shoes", shoes);
                Map<Brand, String>brandsMap= new HashMap<>();
                List<Brand> listBrands =  brandFacade.findAll();
                for(Brand brand : listBrands){
                    if(shoes.getBrands().contains(brand)){
                        brandsMap.put(brand,"selected");
                    }else{
                        brandsMap.put(brand,"");
                    }
                }
                request.setAttribute("brandsMap",brandsMap);
                request.getRequestDispatcher("/editShoes.jsp").forward(request, response);
                break;
            case "/updateShoes":
                String newShoesId = request.getParameter("shoesId");
                String newName = request.getParameter("name");
                String[] newBrands = request.getParameterValues("listBrands");
                String newYear = request.getParameter("year");
                price = request.getParameter("price");
                if("".equals(newShoesId) || newShoesId == null
                     || "".equals(newName) || newName == null   
                     || newBrands == null || newBrands.length == 0   
                     || "".equals(newYear) || newYear == null 
                     || "".equals(price)
                        ){
                    request.setAttribute("info", "Заполните все поля (выберите авторов)");
                    request.getRequestDispatcher("/editShoes").forward(request, response);
                    break;
                }
                Shoes editShoes = shoesFacade.find(Long.parseLong(newShoesId));
                editShoes.setName(newName);
                List<Brand> newListBrands = new ArrayList<>();
                for(String brandId : newBrands){
                    newListBrands.add(brandFacade.find(Long.parseLong(brandId)));
                }
                editShoes.setBrands(newListBrands);
                editShoes.setYear(Integer.parseInt(newYear));
                editShoes.setPrice(price);
                shoesFacade.edit(editShoes);
                request.getRequestDispatcher("/listShoes").forward(request, response);
                break;
            case "/addBrand":
                brands = brandFacade.findAll();
                request.setAttribute("brands", brands);
                request.getRequestDispatcher("/addBrand.jsp").forward(request, response);
                break;
            case "/createBrand":
                name = request.getParameter("name");
                if("".equals(name) ){
                    request.setAttribute("name", name);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/addBrand.jsp").forward(request, response);
                    break;
                }
                Brand newbrand = new Brand();
                newbrand.setName(name);
                brandFacade.create(newbrand);
                request.setAttribute("info", "Новый бренд создан");
                request.getRequestDispatcher("/addBrand").forward(request, response);
                break;
            case "/editBrand":
                String brandId = request.getParameter("brandId");
                Brand editBrand = brandFacade.find(Long.parseLong(brandId));
                request.setAttribute("brand", editBrand);
                
                request.getRequestDispatcher("/editBrand.jsp").forward(request, response);
                break;
            case "/updateBrand":
                brandId = request.getParameter("brandId");
                Brand updateBrand = brandFacade.find(Long.parseLong(brandId));
                name = request.getParameter("name");
                if("".equals(name)){
                    request.setAttribute("info", "Заполните все поля");
                    request.setAttribute("brand", updateBrand);
                    request.getRequestDispatcher("/editBrand").forward(request, response);
                    break;
                }
                updateBrand.setName(name);
                brandFacade.edit(updateBrand);
                request.setAttribute("info", "Бренд обновлен");
                request.getRequestDispatcher("/addBrand").forward(request, response);
                break;
        }
        
    }
    private String getFileName(Part part){
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content
                        .substring(content.indexOf('=')+1)
                        .trim()
                        .replace("\"",""); 
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
