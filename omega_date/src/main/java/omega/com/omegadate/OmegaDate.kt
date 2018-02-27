package omega.com.omegadate

import omega.com.omegadate.types.DaysOfWeek
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class OmegaDate(date : Date) : Serializable, Comparable<OmegaDate>, Cloneable {

    private val DEFAULT_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val CALENDAR = Calendar.getInstance()

    companion object {

        /**
         * Clear time of date
         * set hourOfDay, min, second, milliseconds to 0
         *
         * @return new instance of this OmegaDate with cleared hours, min, sec, millis.
         */
        @JvmStatic
        fun clearTime(date: OmegaDate): OmegaDate {
            return OmegaDate(date).clearTime()
        }
    }

    constructor(date: OmegaDate) : this(date.getDate())
    constructor(calendar: Calendar) : this(calendar.time)
    constructor() : this(Date())

    init {
        setDate(date)
    }

    /**
     * @return  returns the java.util.Calendar represented by this date.
     */
    fun getCalendar(): Calendar = CALENDAR

    /**
     * @return  returns the java.util.Date represented by this date.
     */
    fun getDate(): Date = CALENDAR.time

    /**
     * Sets the date field parameters to the values given by {@code date}
     *
     * @param date the java.util.Date value
     *
     * @return this OmegaDate
     */
    fun setDate(date : Date): OmegaDate {
        CALENDAR.time = date
        return this
    }

    /**
     * Sets the date field parameters to the values given by {@code date}
     *
     * @param date       the OmegaDate value
     *
     * @return this OmegaDate
     */
    fun setDate(date: OmegaDate): OmegaDate {
        return setDate(date.getDate())
    }

    /**
     * Sets the date field parameters to the values given by {@code date}
     *
     * @param date the Calendar value
     *
     * @return this OmegaDate
     */
    fun setDate(calendar: Calendar): OmegaDate {
        return setDate(calendar.time)
    }

    /**
     * Sets the date field parameters to the values given by {@code date}
     *
     * @param date       the String value
     * @param dateFormat the SimpleDateFormat value
     *
     * @throws ParseException if {@code date} is {@code null or with mistakes}
     *
     * @return this OmegaDate
     */
    @Throws(ParseException::class)
    fun setDate(date: String, dateFormat: SimpleDateFormat): OmegaDate {
        return setDate(dateFormat.parse(date))
    }

    /**
     * Sets the date field parameters to the values given by {@code year},
     * {@code month}, and {@code dayOfMonth}. This method is equivalent to
     * a call to:
     * <pre>
     *   setFields(Calendar.YEAR, year,
     *             Calendar.MONTH, month,
     *             Calendar.DAY_OF_MONTH, dayOfMonth);</pre>
     *
     * @param year       the {@link Calendar#YEAR YEAR} value
     * @param month      the {@link Calendar#MONTH MONTH} value
     *                   (the month numbering is <em>0-based</em>).
     * @param dayOfMonth the {@link Calendar#DAY_OF_MONTH DAY_OF_MONTH} value
     * @return this OmegaDate
     */
    fun setDate(dayOfMonth: Int, month: Int, year: Int): OmegaDate {
        return setYear(year)
                .setMonth(month)
                .setDayOfMonth(dayOfMonth)
    }

    /**
     * Set the time of day field parameters to the values given by
     * {@code hourOfDay}, {@code minute}, and {@code second}. This method is
     * equivalent to a call to:
     * <pre>
     *   setTimeOfDay(hourOfDay, minute, second, 0);</pre>
     *
     * @param hourOfDay the {@link Calendar#HOUR_OF_DAY HOUR_OF_DAY} value
     *                  (24-hour clock)
     * @param minute    the {@link Calendar#MINUTE MINUTE} value
     * @param second    the {@link Calendar#SECOND SECOND} value
     * @param millis    the {@link Calendar#MILLISECOND MILLISECOND} value
     * @return this OmegaDate
     */
    fun setTimeOfDay(millis: Int, sec: Int, min: Int, hoursOfDay: Int): OmegaDate {
        return setMillis(millis)
                .setSeconds(sec)
                .setMin(min)
                .setHoursOfDay(hoursOfDay)
    }

    /**
     * Set this <code>Date</code> object to represent a point in time that is
     * <code>time</code> milliseconds after January 1, 1970 00:00:00 GMT.
     *
     * @param time the number of milliseconds.
     */
    fun setTime(time: Long): OmegaDate {
        CALENDAR.time.time = time
        return this
    }

    /**
     * Set the time zone parameter to the given {@code zone}. If no time
     * zone parameter is given to this {@code Caledar.Builder}, the
     * {@linkplain TimeZone#getDefault() default
     * <code>TimeZone</code>} will be used in the {@link #build() build}
     * method.
     *
     * @param zone the {@link TimeZone}
     * @return this {@code Calendar.Builder}
     * @throws NullPointerException if {@code zone} is {@code null}
     * @see Calendar.setTimeZone(TimeZone)
     */
    fun setTimeZone(timeZone: TimeZone): OmegaDate {
        CALENDAR.timeZone = timeZone
        return this
    }

    /**
     * Get the time zone.
     *
     * @return the time zone object associated with this date.
     */
    fun getTimeZone(): TimeZone = CALENDAR.timeZone

    /**
     * Number for <code>getDayOfWeek</code> indicating the day of the week.
     *
     * @return this field return values <code>SUNDAY</code>,
     * <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>,
     * <code>THURSDAY</code>, <code>FRIDAY</code>, and <code>SATURDAY</code>.
     *
     *  @see Calendar.DAY_OF_WEEK
     */
    fun getDayOfWeek(): Int = CALENDAR[Calendar.DAY_OF_WEEK]

    /**
     * Set the given day of week.
     *
     * @param day Int DAY_OF_WEEK
     * @return this OmegaDate
     * @see Calendar.DAY_OF_WEEK
     */
    fun setDayOfWeek(day: Int): OmegaDate {
        CALENDAR.set(Calendar.DAY_OF_WEEK, day)
        return this
    }

    /**
     * Get the start of date represented by this OmegaDate.
     *
     * @return OmegaDate
     */
    fun getStartOfDay(): OmegaDate = clearTime(this)

    /**
     * Get the end of date represented by this OmegaDate.
     *
     * @return OmegaDate
     */
    fun getEndOfDay(): OmegaDate {
        return setMillis(999)
                .setSeconds(59)
                .setMin(59)
                .setHoursOfDay(23)
    }

    /**
     * Get the day of the month represented by this OmegaDate.
     *
     * @return Int date of the month
     */
    fun getDayOfMonth(): Int = CALENDAR[Calendar.DAY_OF_MONTH]

    /**
     * Get the day of the year represented by this OmegaDate.
     *
     * @return Int date of the year
     */
    fun getDayOfYear(): Int = CALENDAR[Calendar.DAY_OF_YEAR]

    /**
     * Get the week of the month represented by this OmegaDate.
     *
     * @return Int date of the year
     */
    fun getWeekOfMonth(): Int = CALENDAR[Calendar.WEEK_OF_MONTH]

    /**
     * Get the week of the year represented by this OmegaDate.
     *
     * @return Int date of the year
     */
    fun getWeekOfYear(): Int = CALENDAR[Calendar.WEEK_OF_YEAR]

    /**
     * Get the start of the week represented by this OmegaDate.
     *
     * @return OmegaDate
     */
    fun getStartOfWeek(): OmegaDate {
        val date = Companion.clearTime(this)
        return date.setDayOfWeek(date.CALENDAR.firstDayOfWeek)
    }

    /**
     * Get the end of the week represented by this OmegaDate.
     *
     * @return new OmegaDate
     */
    fun getEndOfWeek(): OmegaDate {
        val date = getEndOfDay()
        return date.setDayOfWeek(date.CALENDAR.firstDayOfWeek)
                .addDays(6)
    }

    /**
     * Get month represented by this OmegaDate.
     *
     * @return Int Month
     */
    fun getMonth(): Int = CALENDAR[Calendar.MONTH]

    /**
     * Get the start of the month represented by this OmegaDate.
     *
     * @return OmegaDate
     */
    fun getStartOfMonth(): OmegaDate = clearTime(this).setDayOfMonth(1)

    /**
     * Get the end of the month represented by this OmegaDate.
     *
     * @return OmegaDate
     */
    fun getEndOfMonth(): OmegaDate {
        val date = getEndOfDay()
        date.setDayOfMonth(date.CALENDAR.getActualMaximum(Calendar.DAY_OF_MONTH))
        return date
    }

    /**
     * Get year represented by this OmegaDate.
     *
     * @return Int Year
     */
    fun getYear(): Int = CALENDAR[Calendar.YEAR]

    /**
     * Get the start of the year represented by this OmegaDate.
     *
     * @return OmegaDate
     */
    fun getStartOfYear(): OmegaDate {
        val date = clearTime(this)
        date.CALENDAR.set(Calendar.DAY_OF_YEAR, 1)
        return date
    }

    /**
     * Get the end of the year represented by this OmegaDate.
     *
     * @return OmegaDate
     */
    fun getEndOfYear(): OmegaDate {
        val date = getEndOfDay()
        date.CALENDAR.set(Calendar.DAY_OF_YEAR, date.CALENDAR.getActualMaximum(Calendar.DAY_OF_YEAR))
        return date
    }

    /**
     * OmegaDate will be formatted to String with your SimpleDateFormat
     *
     * @param dateFormat the SimpleDateFormat.
     * @return  return formatted String.
     */
    @JvmOverloads
    fun formatDate(simpleDateFormat: SimpleDateFormat = DEFAULT_DATE_FORMAT): String {
        return simpleDateFormat.format(getDate())
    }

    /**
     * Clear time of date
     * set hourOfDay, min, second, milliseconds to 0
     *
     * @return  return this OmegaDate.
     */
    fun clearTime(): OmegaDate {
        return setHoursOfDay(0)
                .setMin(0)
                .setSeconds(0)
                .setMillis(0)
    }

    /**
     * Add millis to this OmegaDate
     *
     * @param millis Int
     * @return  return this OmegaDate.
     */
    fun addMillis(millis: Int): OmegaDate {
        CALENDAR.add(Calendar.MILLISECOND, millis)
        return this
    }

    /**
     * Set millis to this OmegaDate
     *
     * @param millis Int
     * @return  return this OmegaDate.
     */
    fun setMillis(millis: Int): OmegaDate {
        CALENDAR.set(Calendar.MILLISECOND, millis)
        return this
    }

    /**
     * Add second to this OmegaDate
     *
     * @param sec Int
     * @return  return this OmegaDate.
     */
    fun addSeconds(sec: Int): OmegaDate {
        CALENDAR.add(Calendar.SECOND, sec)
        return this
    }

    /**
     * Set seconds to this OmegaDate
     *
     * @param sec Int
     * @return  return this OmegaDate.
     */
    fun setSeconds(sec: Int): OmegaDate {
        CALENDAR.set(Calendar.SECOND, sec)
        return this
    }

    /**
     * Add min to this OmegaDate
     *
     * @param mins Int
     * @return  return this OmegaDate.
     */
    fun addMin(mins: Int): OmegaDate {
        CALENDAR.add(Calendar.MINUTE, mins)
        return this
    }

    /**
     * Set mins to this OmegaDate
     *
     * @param mins Int
     * @return  return this OmegaDate.
     */
    fun setMin(min: Int): OmegaDate {
        CALENDAR.set(Calendar.MINUTE, min)
        return this
    }

    /**
     * Add hours to this OmegaDate
     *
     * @param hours Int
     * @return  return this OmegaDate.
     */
    fun addHours(hours: Int): OmegaDate {
        CALENDAR.add(Calendar.HOUR, hours)
        return this
    }

    /**
     * Add hours of Day to this OmegaDate
     *
     * @param hours Int
     * @return  return this OmegaDate.
     */
    fun setHoursOfDay(hours: Int): OmegaDate {
        CALENDAR.set(Calendar.HOUR_OF_DAY, hours)
        return this
    }

    /**
     * Add days of Day to this OmegaDate
     *
     * @param days Int
     * @return  return this OmegaDate.
     */
    fun addDays(days: Int): OmegaDate {
        CALENDAR.add(Calendar.DAY_OF_YEAR, days)
        return this
    }

    /**
     * Set day of month to this OmegaDate
     *
     * @param day Int
     * @return  return this OmegaDate.
     */
    fun setDayOfMonth(day: Int): OmegaDate {
        CALENDAR.set(Calendar.DAY_OF_MONTH, day)
        return this
    }

    /**
     * Add weeks to this OmegaDate
     *
     * @param weeks Int
     * @return  return this OmegaDate.
     */
    fun addWeeks(weeks: Int): OmegaDate {
        CALENDAR.add(Calendar.WEEK_OF_YEAR, weeks)
        return this
    }

    /**
     * Add months to this OmegaDate
     *
     * @param months Int
     * @return  return this OmegaDate.
     */
    fun addMonth(months: Int): OmegaDate {
        CALENDAR.add(Calendar.MONTH, months)
        return this
    }

    /**
     * Set months to this OmegaDate
     *
     * @param month Int
     * @return  return this OmegaDate.
     */
    fun setMonth(month: Int): OmegaDate {
        CALENDAR.set(Calendar.MONTH, month)
        return this
    }

    /**
     * Add years to this OmegaDate
     *
     * @param years Int
     * @return  return this OmegaDate.
     */
    fun addYears(years: Int): OmegaDate {
        CALENDAR.add(Calendar.YEAR, years)
        return this
    }

    /**
     * Set years to this OmegaDate
     *
     * @param year Int
     * @return  return this OmegaDate.
     */
    fun setYear(year: Int): OmegaDate {
        CALENDAR.set(Calendar.YEAR, year)
        return this
    }

    /**
     * Get ages
     *
     * @param currentDate OmegaDate
     * @return ages for this OmegaDate
     */
    @JvmOverloads
    fun getAges(currentDate: OmegaDate = OmegaDate()): Int {
        var age = currentDate.getYear() - getYear()
        if (currentDate.getDayOfYear() < getDayOfYear()) {
            age -= 1
        }
        return age
    }

    /**
     * Is Age between min and max
     *
     * @param minAge Int
     * @param maxAge Int
     * @param currentDate OmegaDate
     *
     * @return true if ages between min and max, else false
     */
    @JvmOverloads
    fun isAllowableAge(minAge: Int, maxAge: Int, currentDate: OmegaDate = OmegaDate()): Boolean {
        val age = getAges(currentDate)
        return age in minAge..maxAge
    }

    /**
     * Is Age between two dates
     *
     * @param minDate Int
     * @param maxDate Int
     * @param currentDate OmegaDate
     *
     * @return true if ages between min and max, else false
     */
    fun isInRange(minDate: OmegaDate, maxDate: OmegaDate): Boolean {
        return !(minDate.after(this)) && !(maxDate.before(this))
    }

    /**
     * Is OmegaDate start of week
     *
     * @param currentDate OmegaDate
     * @return true if this OmegaDate is monday, else false
     */
    fun isStartOfWeek(): Boolean {
        return isDayOfWeek(this, DaysOfWeek.values()[CALENDAR.firstDayOfWeek])
    }

    /**
     * Is OmegaDate end of week
     *
     * @param currentDate OmegaDate
     * @return true if this OmegaDate is sunday, else false
     */
    fun isEndOfWeek(): Boolean {
        val daysOfWeek = DaysOfWeek.values()
        val lastDayOfWeek = (CALENDAR.firstDayOfWeek + daysOfWeek.size - 1) % daysOfWeek.size
        return isDayOfWeek(this, daysOfWeek[lastDayOfWeek])
    }

    /**
     * @param date OmegaDate
     * @param possibleDay DaysOfWeek
     *
     * @return true if this OmegaDate is possibleDay, else false
     */
    @JvmOverloads
    fun isDayOfWeek(date: OmegaDate = OmegaDate(), possibleDay: DaysOfWeek): Boolean {
        val dayOfWeek: DaysOfWeek
        when(possibleDay) {
            DaysOfWeek.SUNDAY -> { dayOfWeek = DaysOfWeek.SUNDAY }
            DaysOfWeek.MONDAY -> { dayOfWeek = DaysOfWeek.MONDAY }
            DaysOfWeek.TUESDAY -> { dayOfWeek = DaysOfWeek.TUESDAY }
            DaysOfWeek.WEDNESDAY -> { dayOfWeek = DaysOfWeek.WEDNESDAY }
            DaysOfWeek.THURSDAY -> { dayOfWeek = DaysOfWeek.THURSDAY }
            DaysOfWeek.FRIDAY -> { dayOfWeek = DaysOfWeek.FRIDAY }
            DaysOfWeek.SATURDAY -> { dayOfWeek = DaysOfWeek.SATURDAY }
        }
        return date.CALENDAR.get(Calendar.DAY_OF_WEEK) == dayOfWeek.dayOfWeek
    }

    /**
     * Is OmegaDate start of month
     *
     * @param currentDate OmegaDate
     * @return true if this OmegaDate is start of month, else false
     */
    fun isStartOfMonth(): Boolean {
        return CALENDAR.get(Calendar.DAY_OF_MONTH) == CALENDAR.getActualMinimum(Calendar.DAY_OF_MONTH)
    }

    /**
     * Is OmegaDate end of month
     *
     * @param currentDate OmegaDate
     * @return true if this OmegaDate is end of month, else false
     */
    fun isEndOfMonth(): Boolean {
        return CALENDAR.get(Calendar.DAY_OF_MONTH) == CALENDAR.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    /**
     * Get difference between two dates in sec
     *
     * @param compareDate OmegaDate
     * @return Long difference
     */
    fun differenceBetweenDatesInSec(compareDate: OmegaDate = OmegaDate()): Long {
        return TimeUnit.MILLISECONDS.toSeconds(differenceBetweenDatesInMillis(compareDate))
    }

    /**
     * Get difference between two dates in min
     *
     * @param compareDate OmegaDate
     * @return Long difference
     */
    fun differenceBetweenDatesInMin(compareDate: OmegaDate = OmegaDate()): Long {
        return TimeUnit.MILLISECONDS.toMinutes(differenceBetweenDatesInMillis(compareDate))
    }

    /**
     * Get difference between two dates in hour
     *
     * @param compareDate OmegaDate
     * @return Long difference
     */
    fun differenceBetweenDatesInHour(compareDate: OmegaDate = OmegaDate()): Long {
        return TimeUnit.MILLISECONDS.toHours(differenceBetweenDatesInMillis(compareDate))
    }

    /**
     * Get difference between two dates in days
     *
     * @param compareDate OmegaDate
     * @return Long difference
     */
    fun differenceBetweenDatesInDays(compareDate: OmegaDate): Long {
        return TimeUnit.MILLISECONDS.toDays(differenceBetweenDatesInMillis(compareDate))
    }

    /**
     * Get difference between two dates in millis
     *
     * @param compareDate OmegaDate
     * @return Long difference
     */
    fun differenceBetweenDatesInMillis(compareDate: OmegaDate): Long {
        return CALENDAR.time.time - compareDate.getDate().time
    }

    /**
     * Tests if this date is after the specified date.
     *
     * @param   when   a date.
     * @return  <code>true</code> if and only if the instant represented
     *          by this <tt>Date</tt> object is strictly later than the
     *          instant represented by <tt>when</tt>;
     *          <code>false</code> otherwise.
     * @exception NullPointerException if <code>when</code> is null.
     */
    fun after(other: OmegaDate): Boolean = this.compareTo(other) > 0

    /**
     * Tests if this date is before the specified date.
     *
     * @param   when a date.
     * @return  <code>true</code> if and only if the instant of time
     *            represented by this <tt>Date</tt> object is strictly
     *            earlier than the instant represented by <tt>when</tt>;
     *          <code>false</code> otherwise.
     * @exception NullPointerException if <code>when</code> is null.
     */
    fun before(other: OmegaDate): Boolean = compareTo(other) < 0

    /**
     * Tests if this date is the same with the specified date.
     *
     * @param   other a OmegaDate.
     * @return  <code>true</code> if and only if the instant represented
     *          by this <tt>Date</tt> object is the same with the
     *          instant represented by <tt>when</tt>;
     *          <code>false</code> otherwise.
     */
    fun isSameDate(other: OmegaDate): Boolean = compareTo(other) == 0

    /**
     * Tests if this date is yesterday.
     *
     * @param   currentDate a OmegaDate.
     * @return  <code>true</code> if and only if the instant represented
     *          by this <tt>Date</tt> object is yesterday;
     *          <code>false</code> otherwise.
     * @exception NullPointerException if <code>when</code> is null.
     */
    @JvmOverloads
    fun isYesterday(currentDate: OmegaDate = OmegaDate()): Boolean {
        return clearTime(this).differenceBetweenDatesInDays(clearTime(currentDate)) == (-1L)
    }

    override fun compareTo(other: OmegaDate): Int {
        return CALENDAR.compareTo(other.CALENDAR)
    }

    override fun toString(): String = getDate().toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OmegaDate

        return CALENDAR == other.CALENDAR
    }

    override fun hashCode(): Int {
        return CALENDAR?.hashCode() ?: 0
    }


}