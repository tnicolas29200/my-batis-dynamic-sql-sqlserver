package org.mybatis.dynamic.ext.sql.select;

public enum ConvertStyle {
	
	dateDefaultWithoutCentury("0"), // mon dd yy hh:miAM/PM	
	dateUSWithoutCentury("1"), // mm/dd/yy
	dateAnsiWithoutCentury("2"), // yy.mm.dd
	dateBritishFrenchWithoutCentury("3"), // dd/mm/yy
	dateGermanWithoutCentury("4"), // dd.mm.yy
	dateItalianWithoutCentury("5"), // dd-mm-yy
	dateWithSpaceWithoutCentury("6"), // dd mon yy
	dateWithCommaWithoutCentury("7"), // Mon dd, yy
	dateHourWithoutCentury("8"), // hh:mm:ss
	dateDefaultWithMsWithoutCentury("9"), // mon dd yy hh:mi:ss:mmmAM 
	dateUSAWithoutCentury("10"), // mm-dd-yy
	dateJapanWithoutCentury("11"), // yy/mm/dd
	dateISOWithoutCentury("12"), // yymmdd
	dateEuropaWithoutCentury("13"), // dd mon yyyy hh:mi:ss:mmm
	dateHour24HoursWithoutCentury("14"), // hh:mi:ss:mmm
	dateODBCWithoutCentury("20"), // yy-mm-dd hh:mi:ss
	dateODBCCanonicalWithoutCentury("21"), // yy-mm-dd hh:mi:ss.mmm	
	dateDefault("100"), // mon dd yyyy hh:miAM/PM	
	dateUS("101"), // mm/dd/yyyy
	dateAnsi("102"), // yyyy.mm.dd
	dateBritishFrench("103"), // dd/mm/yyyy
	dateGerman("104"), // dd.mm.yyyy
	dateItalian("105"), // dd-mm-yyyy
	dateWithSpace("106"), // dd mon yyyy
	dateWithComma("107"), // Mon dd, yyyy
	dateHour("108"), // hh:mm:ss
	dateDefaultWithMs("109"), // mon dd yyyy hh:mi:ss:mmmAM 
	dateUSA("110"), // mm-dd-yyyy
	dateJapan("111"), // yyyy/mm/dd
	dateISO("112"), // yyyymmdd
	dateEuropa("113"), // dd mon yyyy hh:mi:ss:mmm
	dateHour24Hours("114"), // hh:mi:ss:mmm
	dateODBC("120"), // yyyy-mm-dd hh:mi:ss
	dateODBCCanonical("121"), // yyyy-mm-dd hh:mi:ss.mmm
	dateISO8601("126"), // yyyy-mm-ddThh:mi:ss.mmm
	dateISO8601WithTimeZone("127"), // yyyy-mm-ddThh:mi:ss.mmmZ
	dateHijiriLong("130"), // dd mon yyyy hh:mi:ss:mmmAM
	dateHijiri("131"), // dd/mm/yy hh:mi:ss:mmmAM
	float6Digits("0"),
	float8Degits("1"),
	float16Digits("2"),
	moneyNoComma2Digits("0"),
	moneyComma2Digits("1"),
	moneyNoComma4Digits("2");
	
	
	protected String representation;
	
	private ConvertStyle(String representation) {
		this.representation = representation;
	}
	
	public String toString() {
		return this.representation;
	}
}
