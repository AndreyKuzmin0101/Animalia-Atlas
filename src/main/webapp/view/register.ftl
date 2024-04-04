<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Animal Kingdom Registration</title>
  <link rel="stylesheet" type="text/css" href="/view/styles/styles-group1.css" />
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<form class="container" action="register" method="post" onsubmit="submit_form(event)">
  <div class="animal-icon">&#129434;</div>
  <div class="header">Animal Kingdom Registration</div>

  <label for="name">Ваше имя (опционально):</label>
  <input type="text" name="first_name" id="name">

  <label for="lastName">Ваша фамилия (опционально):</label>
  <input type="text" name="last_name" id="lastName">

  <label for="age">Ваш возраст (опционально):</label>
  <input type="number" name="age" id="age">

  <label for="email">Ваш email:</label>
  <input type="text" name="email" required id="email">

  <label for="login">Логин:</label>
  <input type="text" name="login" required id="login">
  <label for="password">Пароль:</label>
  <h6>Имеет длину не менее 8 символов.</h6>
  <h6>По крайней мере, одна заглавная английская буква.</h6>
  <h6>Хотя бы одна строчная буква английского алфавита.</h6>
  <h6>Должен содержать хотя бы одну цифру.</h6>
  <h6>По крайней мере, один специальный символ. (#?!@$%^&*-)</h6>
  <input type="password" name="password" required id="password">

  <input class="button" type="submit" value="Зарегистрироваться">
  <div class="optional-fields">* Мы также рекомендуем заполнить необязательные поля, чтобы улучшить опыт использования платформы.</div>
</form>
<script src="/view/scripts/validate.js"></script>
</body>
</html>
