package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern : String="HH:mm:ss dd:MM:yy"):String{
  val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
  return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits=TimeUnits.SECOND):Date{
  var time = this.time
  time+=when(units){
    TimeUnits.SECOND -> value * SECOND
    TimeUnits.MINUTE  -> value * MINUTE
    TimeUnits.HOUR -> value * HOUR
    TimeUnits.DAY  -> value * DAY
    //else -> throw IllegalStateException("invalid unit")
  }
  this.time = time
  return this
}
/*
enum class TimeUnits{
  SECOND,
  MINUTE,
  HOUR,
  DAY
}
*/
enum class TimeUnits(val size : Long, val russianName: Array<String>) {

  SECOND(1000L, arrayOf("секунд", "секунду", "секунды")),

  MINUTE(60000L, arrayOf("минут", "минуту", "минуты")),

  HOUR(3600000L, arrayOf("часов", "час", "часа")),

  DAY(86400000L, arrayOf("дней", "день", "дня"));

  fun plural(value:Int): String = "$value ${this.russianName[calculateEnding(value)]}"

  private fun calculateEnding(value: Int) = when {
    value % 100 in 5..20 -> 0
    value % 10 in 2..4 -> 2
    value % 10 == 1 -> 1
    else -> 0
  }
}

/*
*Date.humanizeDiff
Необходимо реализовать extension для форматирования вывода разницы между текущим экземпляром Date
и текущим моментом времени (или указанным в качестве аргумента) в человекообразном формате
Реализуй extension Date.humanizeDiff(date) (значение по умолчанию текущий момент времени)
для форматирования вывода разницы между датами в человекообразном формате, с учетом склонения числительных.
Временные интервалы преобразований к человекообразному формату доступны в ресурсах к заданию
Пример:
Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
Date().add(-400, TimeUnits.DAY).humanizeDiff() //более года назад
Date().add(400, TimeUnits.DAY).humanizeDiff() //более чем через год
*/
//TODO написать ф-цию
fun Date.humanizeDiff(date: Date = Date()): String {
  //TODO("not implemented")
  var result:String =""
  val diff:Long = date.compareTo(Date()).toLong()
  println("diff: $diff")

  when(diff){
    (0*SECOND)-(1*SECOND) -> result = "только что"
    (1*SECOND) -(45*SECOND)  -> result = "несколько секунд назад"
    (45*SECOND) -(75*SECOND) -> result = "минуту назад"
    (75*SECOND)- (45* MINUTE) -> result = "N минут назад"
    (45* MINUTE) - (75* MINUTE)-> result = "час назад"
    (75* MINUTE) - (22*HOUR)-> result =  "N часов назад"
    (22*HOUR) -(26*HOUR) -> result = "день назад"
    (26*HOUR) -(360*DAY) -> result = "N дней назад"
    (360*DAY)- (36000*DAY) -> result = "более года назад"


  }
  return result
}

/*
0с - 1с "только что"

1с - 45с "несколько секунд назад"

45с - 75с "минуту назад"

75с - 45мин "N минут назад"

45мин - 75мин "час назад"

75мин 22ч "N часов назад"

22ч - 26ч "день назад"

26ч - 360д "N дней назад"

>360д "более года назад"
 */

