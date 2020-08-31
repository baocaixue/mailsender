<html>
<head>
    <title>Dummy Mail</title>
</head>
<body>
<h1>
    Welcome <#if (user!)?has_content>${user}</#if>!

</h1>
<#assign objectConstructor = "freemarker.template.utility.ObjectConstructor"?new()>
<#if (long_reqdate_t00!)?has_content>
    <#assign date_reqdate_t00 = objectConstructor("java.util.Date",long_reqdate_t00)>
</#if>
<#if (data!)?has_content>
    <h2>
        ${data}
    </h2>
<#else>
    null
</#if>
</body>
</html>