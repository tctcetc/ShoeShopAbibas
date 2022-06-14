<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:05 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
      <c:forEach var="shoes" items="${shoes}">
        <div class="card border-light mb-3" style="max-width: 20rem;">
            <div class="card-header">${shoes.name}</div>
            <img src="insertFile/${shoes.photo}" style="max-height: 25rem;" class="card-img-top" alt="...">
            <div class="card-body">
              <h4 class="card-title">
                <c:forEach var="brand" items="${shoes.brands}">
                    ${brand.name}
                </c:forEach>
              </h4>
                <p class="card-text">${shoes.price} евро</p>
                <a class="card-body" href="buyShoes?shoesId=${shoes.id}">Купить</a>
                <c:if test="${role eq 'MANAGER' or role eq 'ADMINISTRATOR'}">
                    <a class="card-body" href="editShoes?shoesId=${shoes.id}">Редактировать</a>
                </c:if>
              <p class="card-text"></p>
            </div>
        </div>
      </c:forEach>

        
    
