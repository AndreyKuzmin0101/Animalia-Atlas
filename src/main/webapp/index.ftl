<#macro title>Сайт о животных</#macro>
<#macro parametrs></#macro>

<#macro content>
  <div class="container">
    <h2>Добро пожаловать на наш сайт!</h2>
    <p>Здесь вы найдете много интересной информации о животных.</p>

    <div id="carouselExampleIndicators" class="carousel slide">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <div class="d-block w-100">
            <div class="card-container">
              <div class="card" style="width: 18rem; height: 620px; margin-bottom: 20px; display: inline-block;">
                <img src="${lion.image}" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">${lion.name}</h5>
                  <p class="card-text">${lion.description}</p>
                  <a href="articles?animal=${lion.enName}"><input type="button" class="button" value="Перейти"></a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <div class="d-block w-100">
            <div class="card-container">
              <div class="card" style="width: 18rem; height: 620px; margin-bottom: 20px; display: inline-block;">
                <img src="${japaneseSable.image}" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">${japaneseSable.name}</h5>
                  <p class="card-text">${japaneseSable.description}</p>
                  <a href="articles?animal=${japaneseSable.enName}"><input type="button" class="button" value="Перейти"></a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <div class="d-block w-100">
            <div class="card-container">
              <div class="card" style="width: 18rem; height: 620px; margin-bottom: 20px; display: inline-block;">
                <img src="${stripedHyena.image}" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">${stripedHyena.name}</h5>
                  <p class="card-text">${stripedHyena.description}</p>
                  <a href="articles?animal=${stripedHyena.enName}"><input type="button" class="button" value="Перейти"></a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>


</#macro>


<#include "base.ftl">
