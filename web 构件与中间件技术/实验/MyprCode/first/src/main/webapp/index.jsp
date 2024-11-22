<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upper Case Web</title>
    <link href="css/index.css" rel="stylesheet">
</head>
<body>
<form action="/first/UpperCaseServlet" method="post">
    <label for="input">please input string: </label>
    <input type="text" id="input" name="input">
    <button type="submit">conversion</button>
</form>
<br>
<label for="result">converted_uppercase_string: </label>
<input type="text" id="result" name="result" value="${result}" readonly>
</body>
</html>
