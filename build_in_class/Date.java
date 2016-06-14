package build_in_class;

// format dd/mm/yyyy hh:mm (24-hour)
public class Date
{
	public int day;
	public int month;
	public int year;
	public int hour;
	public int minute;
	
	
	public static void main(String[] args)
	{
		Date d = new Date(new Date(5,1, 2015, 5, 4));
		Date d1 = d;
		System.out.println(d1.Date2String());
		System.out.println(d.isBefore(d1));
		System.out.println(d1 == d);
	}
	
	public Date()
	{
		day = month = 1;
		year = 2001;
		hour = 0;
		minute = 0;
	}
	
	public Date(Date d)
	{
		this.day = d.day;
		this.month = d.month;
		this.year = d.year;
		this.hour = d.hour;
		this.minute = d.minute;
	}
	
	public Date(int d, int M, int y, int h, int m)
	{
		day = d;
		month = M;
		year = y;
		hour = h;
		minute = m;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public int getHour()
	{
		return hour;
	}
	
	public int getMinute()
	{
		return minute;
	}
	
	public boolean setDay(int day)
	{
		this.day = day;
		return true;
	}
	
	public boolean setMonth(int month)
	{
		if(month < 1 || month > 12)
		{
			System.out.println("Invalid Month Value: " + month + "\n");
			return false;
		}
		this.month = month;
		return true;
	}
	
	public boolean setYear(int year)
	{
		this.year = year;
		return true;
	}
	
	public boolean setHour(int hour)
	{
		if(hour < 0 || hour > 24)
		{
			System.out.println("Invalid Hour value: " + hour + "\n");
			return false;
		}
		this.hour = hour;
		return true;
	}
	
	public boolean setMinute(int minute)
	{
		if(minute < 0 || minute > 24)
		{
			System.out.println("Invalid Hour value: " + minute + "\n");
			return false;
		}
		this.minute = minute;
		return true;
	}
	
	public boolean isLeapYear()
	{
		if(this.year%400 != 0)
			return false;
		if (!(this.year%4 == 0 && this.year%100 != 0))
			return false;
		return true;
	}
	
	public String Date2String()
	{
		String date = "";
		if (day < 10)
			date = "0";
		date = date + day + "/";
		if (month < 10)
			date = date + "0" + month + "/" + year + " ";
		else
			date = date + month + "/" + year + " ";
		if (hour < 10)
			date = date + "0" + hour + ":";
		else
			date = date + hour + ":";
		if(minute < 10)
			date = date + "0" + minute;
		else
			date = date + minute;
		return date;
	}
	
	public String Date2String(Date d)
	{
		String date = "";
		if (d.day < 10)
			date = "0";
		date = date + d.day + "/";
		if (d.month < 10)
			date = date + "0" + d.month + "/" + d.year + " ";
		else
			date = date + d.month + "/" + d.year + " ";
		if (d.hour < 10)
			date = date + "0" + d.hour + ":";
		else
			date = date + d.hour + ":";
		if(d.minute < 10)
			date = date + "0" + d.minute;
		else
			date = date + d.minute;
		return date;
	}
	
	public Date string2Date(String date)
	{
		Date d = new Date();
		int i=-1;
		int j=-1;
		i = date.indexOf('/', 0);
		d.day = Integer.parseInt(date.substring(0, i));
		j = i + 1 + date.indexOf('/', i);
		d.month = Integer.parseInt(date.substring(i + 1, j));
		System.out.println(d.month);
		d.year = Integer.parseInt(date.substring(j + 1, i));
		System.out.println(d.year);
		d.hour = Integer.parseInt(date.substring(i + 1, j));
		d.minute = Integer.parseInt(date.substring(j + 1, date.length()));
		return d;
	}
	
	public void String2Date(String date)
	{
		int i=-1;
		int j=-1;
		i = date.indexOf('/', 0);
		day = Integer.parseInt(date.substring(0, i));
		j = i + 1 + date.indexOf('/', i);
		month = Integer.parseInt(date.substring(i + 1, j));
		i = date.indexOf(' ', j);
		year = Integer.parseInt(date.substring(j + 1, i));
		j = date.indexOf(':', i);
		hour = Integer.parseInt(date.substring(i + 1, j));
		minute = Integer.parseInt(date.substring(j + 1, date.length()));
	}
	
	// check that 'this' before 'd'
	public boolean isBefore(Date d)
	{
		if (this.year >= d.year)
			return false;
		else
			if (this.month >= d.month)
				return false;
			else
				if (this.day >= d.day)
					return false;
				else
					if(this.hour >= d.hour)
						return false;
					else
						if(this.minute >= d.minute)
							return false;
		return true;
	}
	
	// check that t before
	public boolean isBefore(Date t, Date d)
	{
		if (t.year >= d.year)
			return false;
		else
			if (t.month >= d.month)
				return false;
			else
				if (t.day >= d.day)
					return false;
				else
					if(t.hour >= d.hour)
						return false;
					else
						if(t.minute >= d.minute)
							return false;
		return true;
	}
	
	public boolean isEqual(Date d)
	{
		if (this.year != d.year)
			return false;
		else
			if (this.month != d.month)
				return false;
			else
				if (this.day != d.day)
					return false;
				else
					if(this.hour != d.hour)
						return false;
					else
						if(this.minute != d.minute)
							return false;
		return true;
	}
}
