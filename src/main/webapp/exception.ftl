<#macro title>Exception details</#macro>
<#macro scripts></#macro>

<#macro content>
    <div class="exception">
        <h1>Exception details</h1>
        <h3>Request uri: ${exception.uri}</h3>
        <h3>Status code: ${exception.statusCode}</h3>
        <#if exception.message??><h3>Message: ${exception.message}</h3></#if>
    </div>

</#macro>

<#include "base.ftl">