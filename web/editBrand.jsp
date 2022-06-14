<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:05 PM
    Author     : Melnikov
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div class="card border-light my-5" style="width: 30rem;">
            <div class="card-body">
                <form action="updateBrand" method="POST">
                    <fieldset>
                      <legend>Редактирование бренда</legend>
                      <div class="form-group mb-3">
                        <label for="name">Название</label>
                        <input type="hidden" name="brandId" value="${brand.id}">
                        <input type="text" class="form-control" id="name" name="name" aria-describedby="name" placeholder="" value="${brand.name}">
                        <small id="caption" class="form-text text-muted d-none">Это поле не должно быть пустым</small>
                      </div>
                        <button type="submit" class="btn btn-primary mt-4">Изменить бренд</button>
                    </fieldset>
               </form>
            </div>
        </div>
        