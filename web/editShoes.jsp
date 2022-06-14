<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:05 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="card border-light my-5" style="width: 30rem;">
            <div class="card-body">
                <form action="updateShoes" method="POST" multiple>
                    <fieldset>
                      <legend>Редактирование данных об обуви</legend>
                      <div class="form-group mb-3">
                        <label for="caption">Название модели</label>
                        <input type="hidden" name="shoesId" value="${shoes.id}">
                        <input type="text" class="form-control" id="caption" name="name" aria-describedby="name" placeholder="" value="${shoes.name}">
                        <small id="name" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        <label for="authors">Бренды</label>
                        <select class="form-select" id="brands" name="listBrands">
                          <c:forEach var="entry" items="${brandsMap}">
                              <option ${entry.value} value="${entry.key.id}">${entry.key.name}</option>
                          </c:forEach>
                        </select>
                      <div class="form-group mt-3">
                        <label for="publishedYear">Год выпуска</label>
                        <input type="text" class="form-control" id="year" name="year" aria-describedby="year" placeholder="" value="${shoes.year}">
                        <small id="year" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                      <div class="form-group  mt-3">
                        <label for="price">Цена</label>
                        <input type="text" class="form-control" id="price" name="price" aria-describedby="price" placeholder="" value="${shoes.price}">
                        <small id="price" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        <button type="submit" class="btn btn-primary mt-4">Изменить данные</button>
                    </fieldset>
               </form>
            </div>
        </div>
     
