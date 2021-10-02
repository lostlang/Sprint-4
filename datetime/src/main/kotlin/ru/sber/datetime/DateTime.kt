package ru.sber.datetime

import java.time.LocalDateTime

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val searchedZones = ZoneId.getAvailableZoneIds()
                        .filter { TimeZone.getTimeZone(it)
                                  .rawOffset % 3600000 != 0 }
                        .toSet()

    return searchedZones
}


// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    var searchedDays: MutableList<String> = mutableListOf()
    for (mounth in 1..12) {
        searchedDays.add(YearMonth.of(year, mounth)
                         .atEndOfMonth()
                         .dayOfWeek
                         .toString())
    }

    return searchedDays
}


// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var count = 0
    var date: LocalDate = LocalDate.of(year - 1, 12,31)

    do  {
        date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
        if (date.dayOfMonth == 13) count++
    } while (date.isBefore(LocalDate.of(year + 1, 1,1)))

    return count
}


// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    var out = dateTime.format (DateTimeFormatter
                               .ofPattern("dd MMM yyyy, HH:mm")
                               .withLocale(Locale.ENGLISH))
    return out
}