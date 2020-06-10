package ru.ifmo.firstspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // bean beanContainer

public class HomeController  {

    @RequestMapping("/")
    public String index(){
        return "index";
    }


}

/*
html    контроллер(репозиторий)    данные

html
контроллер(сервис)
сервис(репозиторий) - обработка данных
данные
*/

/*
клиент -> сообщение -> сервер
клиент <- сообщение <- сервер

сообщение:
    строка запроса / статус
    HTTP заголовки
    тело сообщения

"/" | spring
*/
