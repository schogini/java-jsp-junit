<html>
<body>
<h1>Hello World!</h1>
<h2>Container ID: <% out.print(System.getenv("HOSTNAME")); %></h2>
<h2>App Version: <% out.print(System.getenv("WEB")); %></h2>
</body>
</html>
