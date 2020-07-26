<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.net.*" %> 
<%

    String hostname, serverAddress, node;
    hostname = "[]";
    serverAddress = "[]";
    // node="[]";
    node = System.getenv("NODE");

    try {
        InetAddress inetAddress;
        inetAddress = InetAddress.getLocalHost();
        hostname = inetAddress.getHostName();
        serverAddress = inetAddress.toString();
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<body>
<h2>Hello World!</h2>
<h3>Hello from Sree 2017</h3>
<h3>5+2=<%= 5+2 %></h3>
<h2>Current time is <%= LocalDateTime.now() %></h2>

<h2>InetAddress: <%=serverAddress %></h2>
<h2>Hostname: <%=hostname %></h2>
<h2>Node: <%=node %></h2>
<h2>Build: BUILD_ID</h2>

</body>
</html>
