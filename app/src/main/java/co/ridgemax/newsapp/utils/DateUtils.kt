package co.ridgemax.newsapp.utils

import co.ridgemax.newsapp.services.string.StringManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val apiDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    private val phoneDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    private val classicDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)

    init {
        apiDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        phoneDateFormat.timeZone = TimeZone.getDefault()
    }


    fun parseApiStringToDate(dateAsString: String): Date? {
        return try {
            val date = apiDateFormat.parse(dateAsString)
            if (date != null) {
                phoneDateFormat.parse(phoneDateFormat.format(date))
            } else null
        } catch (e: Exception) {
            null
        }
    }

    fun isPast(dateAsString: String): Boolean {
        return try {
            apiDateFormat.parse(dateAsString)?.before(Calendar.getInstance().time) ?: false
        } catch (e: ParseException) {
            false
        }
    }

    fun isDateAfter(firstDate: Date, secondDate: Date): Boolean {
        return firstDate.after(secondDate)
    }

    fun getDifferenceInMillis(startDate: Date, endDate: Date): Long {
        return endDate.time - startDate.time
    }


    fun parseToDayMonthYear(dateAsString: String): String {
        val calendar = Calendar.getInstance().apply {
            apiDateFormat.parse(dateAsString)?.let {
                time = it
            }
        }
        return "${calendar.get(Calendar.DAY_OF_MONTH)} ${
            calendar.getDisplayName(
                Calendar.MONTH,
                Calendar.SHORT,
                Locale.UK
            )
        } ${calendar.get(Calendar.YEAR)}"
    }

    fun classicDateParse(dateAsString: String): String {
        val date = apiDateFormat.parse(dateAsString)
        return if (date != null) classicDateFormat.format(date) else StringManager.EMPTY
    }

}