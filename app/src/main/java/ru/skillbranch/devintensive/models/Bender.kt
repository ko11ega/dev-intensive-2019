package ru.skillbranch.devintensive.models

class Bender (var status:Status = Status.NORMAL, var question:Question = Question.NAME) {
    var errorCounter:Int =0
    fun askQuestion():String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int, Int>> {
        if (question == Question.IDLE) return "${question.question}" to status.color

        var message = ""//question.validateAnswer(answer)
        if (message.isEmpty()) {
            if (question.answers.contains(answer.toLowerCase())) {
                message = "Отлично - ты справился"
                question = question.nextQuestion()
            } else {
                status = status.nextStatus()
                if (status == Status.NORMAL) {
                    question = Question.NAME
                    message = "Это неправильный ответ. Давай все по новой"
                }
                else message = "Это неправильный ответ"
            }
        }
        return "${message}\n${question.question}" to status.color
    }


    enum class Status(val color : Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus():Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal+1]
            }else{
                values()[0]
            }
        }

    }



    enum class Question(val question:String, val answers:List<String>){
        NAME("Как меня зовут?", listOf("бендер","bender")) {
            override fun nextQuestion(): Question =PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")){
            override fun nextQuestion(): Question =MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал","дерево","metal","iron","wood")){
            override fun nextQuestion(): Question =BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question =SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question =IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf("бендер","bender")){
            override fun nextQuestion(): Question =IDLE
        };

        abstract fun nextQuestion(): Question
    }

/*
Bender.listenAnswer (negative case)
Необходимо реализовать метод listenAnswer класса Bender, принимающий в качестве аргумента ответ пользователя
и возвращающий Pair, содержащую текст ошибки и цвет следующего статуса экземпляра класса Bender
Реализуй метод listenAnswer со следующей сигнатурой listenAnswer(answer: String): Pair>.

Вопросы и верные ответы, а также значения цветов статусов, прикреплены к ресурсам урока

Требования к методу:
При вводе неверного ответа изменить текущий статус на следующий статус (status = status.nextStatus()),
вернуть "Это неправильный ответ\n${question.question}" to status.color и изменить цвет ImageView (iv_bender)
на цвет status.color (метод setColorFilter(color,"MULTIPLY"))
При вводе неверного ответа более 3 раз сбросить состояние сущности Bender на значение по умолчанию
(status = Status.NORMAL, question = Question.NAME) и вернуть
"Это неправильный ответ. Давай все по новой\n${question.question}" to status.color и
изменить цвет ImageView (iv_bender) на цвет status.color
Необходимо сохранять состояние экземпляра класса Bender при пересоздании
Activity (достаточно сохранить Status, Question)

Пример:
//Как меня зовут? #NORMAL(Triple(255, 255, 255))
benderObj.listenAnswer("Fry") //Это неправильный ответ\nКак меня зовут? #WARNING(Triple(255, 120, 0))

//Мой серийный номер? #CRITICAL(Triple(255, 0, 0))
benderObj.listenAnswer("0000000") //Это неправильный ответ. Давай все по новой\nКак меня зовут? #NORMAL(Triple(255, 255, 255))

//Как меня зовут? #DANGER(Triple(255, 60, 60))
benderObj.listenAnswer("Fry") //Это неправильный ответ\nКак меня зовут? #CRITICAL(Triple(255, 0, 0))
//onPause() -> onStop() -> onDestroy() -> onCreate()
//Как меня зовут? #CRITICAL(Triple(255, 0, 0))
 */


}