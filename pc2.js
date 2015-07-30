function isPersianLeap(y) {
	y %= 128;
	var x = [0, 4, 8, 12, 16, 20, 24, 28, 33, 37, 41, 45, 49, 53, 57, 61, 66, 70, 74, 78, 82, 86, 90, 95, 99, 103, 107, 111, 115, 119, 123];

	if (x.indexOf(y) > -1)
		return true;
	return false;
}

//isPersianLeap(55);
//console.log(31 * 366 + 97 * 365) // 46751

function isGregorianLeap(y) {
	return ((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0);
}

function pYear(y) {
	var num = Math.floor((y - 1) / 128);
	var i = (y - 1) % 128;
	var num2 = num * 46751 + 226894;
	while (i > 0) {
		num2 += 365;
		if (isPersianLeap(i, 0)) {
			num2 += 1;
		}
		i--;
	}
	return num2;
}

function p2gYear(d) {
	var num = Math.floor(d / 146097);
	var i = d % 146097;
	var num2 = num * 400;
	while (i > 0) {
		i -= 365;
		num2 += 1;
		if (isGregorianLeap(num2)) {
			i -= 1;
		}

	}
	var days = i + 365;
	if (isGregorianLeap(num2)) {
		days += 1;
	}
	return [num2, days];
}

console.log(p2gYear(pYear(1298)))

function DaysUpToPersianYear(y) {
	var num = Math.floor((y - 1) / 128);
	var i = (y - 1) % 128;
	var num2 = num * 46750 + 226894;
	while (i > 0) {
		num2 += 365;
		if (isPersianLeap(i, 0)) {
			num2 += 1;
		}
		i--;
	}
	return num2;
}
const OFFSET_DAYS = 226894; // parseInt(621 * 365.2425 + 31 + 28 + 20)
//var x = 12053; // 9 * 366 + 24 * 365 - 1
