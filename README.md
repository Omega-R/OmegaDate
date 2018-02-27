[![](https://jitpack.io/v/Omega-R/OmegaDate.svg)](https://jitpack.io/#Omega-R/OmegaDate)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

# OmegaDate
OmegaDate it's cover for java.Util.Calendar with expandable functions. 

# Installation
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    compile 'com.github.Omega-R:OmegaDate:0.0.1'
}
```
# Usage

How to create
```
OmegaDate date = new OmegaDate(); // create with current time
          date = new OmegaDate(java.util.Date); // create with specified date
          date = new OmegaDate(OmegaDate); // create with specified OmegaDate
          date = new OmegaDate(java.util.Calendar); // create with specified calendar
```

Or you could set time with functions:
```
setDate(java.util.Date);
setDate(OmegaDate);
setDate(java.util.Calendar);
setDate(String date, SimpleDateFormat dateFormat);
setDate(int dayOfMonth, int month, int year);
setTimeOfDay(int millis, int sec, int min, int hoursOfDay);
setTime(long time);
```

Set or add specific values.
```
addMillis(int millis); // add millis to date
setMillis(long millis); // set millis to date

addSeconds(int sec);
setSeconds(int sec);

addMin(int mins);
setMin(int min);

addHours(int hours);
setHoursOfDay(int hour);

addDays(int days);
setDayOfMonth(int day);

addWeeks(int hours);

addMonth(int hours);
setMonth(int hours);

addYear(int hours);
setYear(int hours);

setDayOfWeek(int dayOfWeek);
```

You could clear time of date. Set hourOfDay, min, second, milliseconds to 0.
```
clearTime();

// If you don't want to clear time for current instance, but you need to compare with other date, you could use 
clearTime(OmegaDate); // return new instance of Date, but with hourOfDay, min, second, milliseconds = 0.
```

Other interesting functions
```
int getAges(OmegaDate currentDate); // return ages
boolean isAllowableAge(int minAge, int maxAge, OmegaDate currentDate);
boolean isInRange(OmegaDate minDate, OmegaDate maxDate);
boolean isStartOfWeek();
boolean isEndOfWeek();
boolean isStartOfMonth();
boolean isEndOfMonth();

long differenceBetweenDatesInMillis(OmegaDate compareDate);
long differenceBetweenDatesInSec(OmegaDate compareDate);
long differenceBetweenDatesInMin(OmegaDate compareDate);
long differenceBetweenDatesInHour(OmegaDate compareDate);
long differenceBetweenDatesInDays(OmegaDate compareDate);

boolean after(OmegaDate compareDate);
boolean before(OmegaDate compareDate);
boolean isSameDate(OmegaDate compareDate);

boolean isYesterday(OmegaDate currentDate);
```

# License
```
The MIT License

Copyright 2017 Omega-R

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, 
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
