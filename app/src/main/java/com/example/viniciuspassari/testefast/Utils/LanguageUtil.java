package com.example.viniciuspassari.testefast.Utils;

public class LanguageUtil {

    public static String getEntireLanguageNameFromCode(String code){
        String lang = "";

        switch (code){
            case "ar-sa":
                lang = "Arabic (Saudi Arabia)";
            break;
            case "ar-eg":
                lang = "Arabic (Egypt)";
            break;
            case "zh-tw":
                lang = "Chinese (Taiwan)";
            break;
            case "da":
                lang = "Danish";
            break;
            case "en-us":
                lang = "English (United States)";
            break;
            case "en":
                lang = "English";
            break;
            case "fr":
                lang = "French";
            break;
            case "el":
                lang = "Greek";
            break;
            case "hi":
                lang = "Hindi";
            break;
            case "id":
                lang = "Indonesian";
            break;
            case "it":
                lang = "Italian";
            break;
            case "ja":
                lang = "Japanese";
            break;
            case "ko":
                lang = "Korean";
            break;
            case "ms":
                lang = "Malaysian";
            break;
            case "pt-br":
                lang = "Portuguese (Brazil)";
            break;
            case "pt":
                lang = "Portuguese (Portugal)";
            break;
            case "ru":
                lang = "Russian";
            break;
            case "es":
                lang = "Spanish";
            break;
            case "th":
                lang = "Thai";
            break;
        }

        return lang;
    }

}
