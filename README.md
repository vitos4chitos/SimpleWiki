# SimpleWiki
Релизованы импорт данных цитат википедии(через разархивированный дамп из локальной папки) и сервис для поиска статьи по точному совпадению наименования.
# ER диаграмма БД
![image](https://user-images.githubusercontent.com/43905444/175365207-9391f6ef-3d6a-4624-a416-6ae68b43bb9a.png)
# Как пользоваться
- Если хотите обновить бд, запускаете с аргументами **true (путь к дампу)**.
- Без обновления и загрузки данных **без аргументов**.

SQL скрипт в директории sql (пропишите путь к файлу в String script в SimpleWiki/src/main/java/main/Application.java).
В application.properties - данные для подключения к бд.
В DriverManager.getConnection в SimpleWiki/src/main/java/main/Application.java - так же данные для подключения к бд.
# Основное задание
Смотри ветку maintask

**upd 1** выполнил первое доп задание.
**upd 2** выполнил второе доп задание.
