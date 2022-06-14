<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:05 PM
    Author     : Melnikov
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="card border-light my-5" style="width: 30rem;">
            <div class="card-body">
                <form action="createShoes" method="POST" enctype="multipart/form-data">
                    <fieldset>
                      <legend>Добавление обуви</legend>
                      <div class="form-group mb-3">
                        <label for="caption">Название модели</label>
                        <input type="text" class="form-control" id="name" name="name" aria-describedby="name" placeholder="">
                        <small id="name" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        <label for="authors">Бренды</label>
                        <select class="form-select" id="brands" name="brands">
                          <c:forEach var="brand" items="${brands}">
                              <option value="${brand.id}">${brand.name}</option>
                          </c:forEach>
                        </select>
                      <div class="form-group mt-3">
                        <label for="publishedYear">Год выпуска</label>
                        <input type="text" class="form-control" id="year" name="year" aria-describedby="year" placeholder="">
                        <small id="year" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                      <div class="form-group  mt-3">
                        <label for="price">Цена</label>
                        <input type="text" class="form-control" id="price" name="price" aria-describedby="price" placeholder="">
                        <small id="price" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                      <div class="form-group  mt-3">
                        <label for="cover">Фото</label>
                        <input type="file" class="form-control" id="photo" name="photo" aria-describedby="photo" placeholder="">
                        <small id="photo" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>

                        <button type="submit" class="btn btn-primary mt-4">Добавить обувь</button>
                    </fieldset>
               </form>
            </div>
        </div>
     
