<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<%
    model.Task task = (model.Task)session.getAttribute("task");
    if (task == null){
%>
<div class="main">
    <p class="error" style="font-size: 18px; text-align: center">
        Result does not exists
    </p>
    <p style="font-size: 16px; text-align: center">
        You will be redirected
    </p>
    <form action="index.jsp" method="get">
        <input class="button" type="submit" value="Back"/>
    </form>
</div>
<script type="text/javascript">
    window.setTimeout(function(){
        window.location.href = "index.jsp";
    }, 5000);
</script>
<%
    }else{
%>
<div class="main">
    <h4>Result</h4>
    <form action="index.jsp" method="get">
        <i>
            log<sub><%=task.getBase()%></sub>(<%=task.getArgument()%>) = <%=task.getResult()%>
        </i>
        <input class="button" type="submit" value="Back"/>
    </form>
    <form action="result.xml" method="get">
        <input class="button" type="submit" value="Download"/>
    </form>
</div>
<%
    }
%>
</body>
</html>