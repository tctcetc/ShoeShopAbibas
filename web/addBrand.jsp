<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:05 PM
    Author     : Melnikov
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="d-flex flex-column">
        <div class="card border-light my-5" style="width: 30rem;">
            <div class="card-body">
                <form action="createBrand" method="POST">
                    <fieldset>
                      <legend>Добавление бренда</legend>
                      <div class="form-group mb-3">
                        <label for="name">Название</label>
                        <input type="text" class="form-control" id="name" name="name" aria-describedby="name" placeholder="" value="${name}">
                        <small id="caption" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        <button type="submit" class="btn btn-primary mt-4">Добавить бренд</button>
                    </fieldset>
               </form>
            </div>
        </div>
        <div class="card border-light my-5" style="width: 30rem;">
            <div class="card-body">
                <h3 class="card-body">Изменить бренд</h3>
                <c:forEach var="brand" items="${brands}">
                    <p><a href="editBrand?brandId=${brand.id}">${brand.name}</a></p>
                </c:forEach>
            </div>
        </div>
</div>