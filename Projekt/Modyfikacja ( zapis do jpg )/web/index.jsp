<%-- 
    Document   : index
    Created on : 2017-11-16, 21:08:13
    Author     : User
--%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Main</title>
  <link rel="stylesheet" href="IndexStyle.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div class="container">
  <form id="contact" name="secondForm" action="wykres" method="get">

    <h3 style="text-align: center;">Rysowanie wykresu</h3>
   <!-- <h4>Prosze podaj dwie liczby</h4>-->
   <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
        X: <input type="text" name="xParam" placeholder="X parameter" value="2" tabindex="0" required autofocus><br/>
    </fieldset>
   <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
        A: <input type="text" name="aParam" placeholder="A parameter" value="1" tabindex="1" required ><br/>
    </fieldset>
    <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
        B: <input type="text" name="bParam" placeholder="B parameter" value="0" tabindex="2" required><br/>
    </fieldset>
    <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
        C: <input type="text" name="cParam" placeholder="C parameter" value="0" tabindex="3" required><br/>
    </fieldset>
    <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
        <br>Zoom: <br><input style="width: 200px" type="range" name="Scale" value="50" tabindex="4" step="5" min="15" max="75"><br/>
    </fieldset>
    <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
      Kolor osi: <input type="color" name="kolorOs"  value="#000000" tabindex="5" required><br/>
    </fieldset>
    <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
      Kolor paraboli: <input type="color" name="kolorParam" value="#0000FF" tabindex="6" required><br/>
    </fieldset>
    <fieldset style="width:300px;text-align: center;margin: 0 auto;border: 0px;">
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Rysuj</button>
    </fieldset>
  </form>

</div>

</body>
</html>
