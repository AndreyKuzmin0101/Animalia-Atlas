<#macro title>Список животных</#macro>
<#macro scripts></#macro>

<#macro content>
    <div class="container">
        <div class="row">
            <#list animals as animal>
                <div class="col-lg-4 col-md-6">
                    <div class="card" style="width: 18rem; height: 620px; margin-bottom: 20px">
                        <img src="${animal.image}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${animal.name}</h5>
                            <p class="card-text">${animal.description}</p>
                            <a href="articles?animal=${animal.enName}"><input type="button" class="button" value="Перейти"></a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<#include "base.ftl">