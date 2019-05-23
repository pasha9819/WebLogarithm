<%@ page contentType="text/html;charset=UTF-8" %>

<%!
    private static Double parseBase(String baseStr){
       if (baseStr == null)
           return null;
       if (baseStr.isEmpty())
           return null;
       try{
           double d = Double.parseDouble(baseStr);
           if (d <= 0 || d == 1)
               return -1.;
           return d;
       }catch (Exception e){
           return null;
       }
    }

    private static Double parseArg(String argStr){
        if (argStr == null)
            return null;
        if (argStr.isEmpty())
            return null;
        try{
            double d = Double.parseDouble(argStr);
            if (d <= 0)
                return -1.;
            return d;
        }catch (Exception e){
            return null;
        }
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Сalculate the logarithm</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
    <div class="main">
        <h4>Сalculate the logarithm</h4>
        <form action="index.jsp" method="get">
            <%
                Double arg, base = null;
                boolean argError, baseError;
                String argStr = request.getParameter("arg");
                String baseStr = request.getParameter("base");

                arg = parseArg(argStr);
                base = parseBase(baseStr);

                argError = argStr != null && !argStr.isEmpty() && arg == null;
                baseError = baseStr != null && !baseStr.isEmpty() && base == null;
                System.out.println("argStr = " + argStr);
                System.out.println("arg = " + arg);

            %>
            <p>Argument</p>
            <label>
                <input id="arg" type="text" name="arg" value="<%=argStr == null ? "" : argStr%>"
                       autocomplete="off" title="Enter arg here..." placeholder="Enter arg here...">
            </label>
            <%
                if (argError){
            %>
                <p class="error">Enter a correct number</p>
                <script type="text/javascript">selectArgInput()</script>
            <%
                }
            %>
            <%
                if (arg != null && arg == -1.){
            %>
                <p class="error">Argument must be greater than 0</p>
                <script type="text/javascript">selectArgInput()</script>
            <%
                    arg = null;
                }
            %>
            <p>Base</p>
            <label>
                <input id="base" type="text" name="base" value="<%=baseStr == null ? "" : baseStr%>"
                       autocomplete="off" title="Enter base here..." placeholder="Enter base here...">
            </label>
            <%
                if (baseError){
            %>
                <p class="error">Enter a correct number</p>
                <script type="text/javascript">selectBaseInput()</script>
            <%
                }
                if (base != null && base == -1.){
            %>
                <p class="error">Base must be greater than 0 and not equal 1</p>
                <script type="text/javascript">selectBaseInput()</script>
            <%
                    base = null;
                }

                if (arg != null && base != null){
                    model.Task task = new model.Task(base, arg);
                    session.setAttribute("task", task);
                    %>
                    <jsp:forward page="html/result.html"/>
                    <%
                }
            %>
            <input class="button" type="submit" value="Сalculate"/>
        </form>
    </div>
</body>
</html>
