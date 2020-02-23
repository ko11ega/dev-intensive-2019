package ru.skillbranch.devintensive.utils

object Utils {
    /*
 Реализуй метод Utils.parseFullName(fullName) принимающий в качестве аргумента полное имя пользователя (null, пустую строку)
 и возвращающий пару значений Pair(firstName, lastName) при невозможности распарсить полное имя или его часть
 вернуть null null/"firstName" null
Пример:
Utils.parseFullName(null) //null null
Utils.parseFullName("") //null null
Utils.parseFullName(" ") //null null
Utils.parseFullName("John") //John null
     */

    fun parseFullName(fullName:String?): Pair<String?, String?> {
        if(fullName == null || fullName == "" || fullName == " ") return Pair(null,null)

        val parts : List<String>? = fullName?.split(" ")

        val firstName =  parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return Pair(firstName,lastName)
        //return firstName to lastName

    }

/*
*toInitials
Необходимо реализовать утилитный метод toInitials(firstName lastName) принимающий в качестве аргументов имя
и фамилию пользователя и возвращающий его инициалы
Реализуй метод Utils.toInitials(firstName lastName) принимающий в качестве аргументов имя
и фамилию пользователя (null, пустую строку) и возвращающий строку с первыми буквами имени и фамилии в верхнем регистре
(если один из аргументов null то вернуть один инициал, если оба аргумента null вернуть null)
Пример:
Utils.toInitials("john" ,"doe") //JD
Utils.toInitials("John", null) //J
Utils.toInitials(null, null) //null
Utils.toInitials(" ", "") //null
 */

    fun toInitials(firstName: String?, lastName: String?): String? {
        var result:String?=null
        var a:String? = firstName?.getOrNull(0)?.toUpperCase()?.toString()?:""
        var b:String? = lastName?.getOrNull(0)?.toUpperCase()?.toString()?:""

        result ="$a"+"$b"
        if (result.isNullOrBlank()) return null


        return result
    }

    fun transliteration(payload: String, divider: String = " "): String {

        val decodePairs: Map<String, String> = mapOf(
            Pair("а", "a"),
            Pair("б","b"),

            Pair("в", "v"),

            Pair("г", "g"),

            Pair("д", "d"),

            Pair("е", "e"),

            Pair("ё", "e"),

            Pair("ж", "zh"),

            Pair("з", "z"),

            Pair("и", "i"),

            Pair("й", "i"),

            Pair("к", "k"),

            Pair("л", "l"),

            Pair("м", "m"),

            Pair("н", "n"),

            Pair("о", "o"),

            Pair("п", "p"),

            Pair("р", "r"),

            Pair("с", "s"),

            Pair("т", "t"),

            Pair("у", "u"),

            Pair("ф", "f"),

            Pair("х", "h"),

            Pair("ц", "c"),

            Pair("ч", "ch"),

            Pair("ш", "sh"),

            Pair("щ", "sh'"),

            Pair("ъ", ""),

            Pair("ы", "i"),

            Pair("ь", ""),

            Pair("э", "e"),

            Pair("ю", "yu"),

            Pair("я", "ya")
        )

        var result:String =""

        for (i in payload.indices) {
            if (payload[i].toString() == " ") result += divider
            else
                //если не русская
                if (payload[i].toString().decapitalize() !in decodePairs.keys)
                    result += payload[i]
                else
                    //если русская заглавная
                    if (payload[i].isUpperCase() && payload[i].toString().decapitalize() in decodePairs.keys)
                        result += decodePairs[payload[i].toString().decapitalize()]?.capitalize()
                    else result += decodePairs[payload[i].toString()]
                    //println(payload[i])
        }

        return result
    }



}