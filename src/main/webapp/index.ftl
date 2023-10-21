<#macro title>Сайт о животных</#macro>
<#macro scripts></#macro>

<#macro content>
  <div class="container">
    <h2>Добро пожаловать на наш сайт!</h2>
    <p>Здесь вы найдете много интересной информации о животных.</p>

    <div class="animal-feed">
      <div class="animal-card">
        <h3>${lion.name}</h3>
        <img src="${lion.image}" alt="${lion.name}" width="180px">
        <p>${lion.description}</p>
        <a href="articles?animal=lion"><input type="button" class="button" value="Перейти"></a>
      </div>
    </div>
  </div>
</#macro>


<#include "base.ftl">
