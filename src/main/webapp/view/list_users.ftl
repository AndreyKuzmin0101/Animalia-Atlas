<#macro title>Пользователи</#macro>
<#macro parametrs></#macro>

<#macro content>
    <div class="container">
        <div class="row">
            <#list users as user>
                <div class="col-lg-3 col-md-6">
                    <a href="/users?id=${user.id}" style="text-decoration: none;">
                        <div class="user-container" style="margin-bottom: 20px">
                            <img class="comment-avatar" src="${user.image}">
                            <strong>${user.login}</strong>
                            <div class="comment-user">${user.firstName} ${user.lastName}</div>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<#include "base.ftl">