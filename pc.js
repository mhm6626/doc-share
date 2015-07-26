const fs = require('fs');

function isPersianLeap(y) {
	y %= 128;
	var x = [0, 4, 8, 12, 16, 20, 24, 28, 33, 37, 41, 45, 49, 53, 57, 61, 66, 70, 74, 78, 82, 86, 90, 95, 99, 103, 107, 111, 115, 119, 123];
	//console.log(x.length)
	if (x.indexOf(y) > -1)
		return true;
	return false;
}
isPersianLeap(55) 
function isGregorianLeap(y) {
	return ((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0);
}
function DaysUpToPersianYear(y) {
	var num = Math.floor((y - 1) / 128);
	var i = (y - 1) % 128;
	var num2 = num * 46750  + 226894 ;
	while (i > 0) {
		num2 += 365 ;
		if (isPersianLeap(i, 0)) {
			num2 += 1 ;
		}
		i--;
	}
	return num2;
}
const OFFSET_DAYS = 226894; // parseInt(621 * 365.2425 + 31 + 28 + 20)
//var x = 12053; // 9 * 366 + 24 * 365 - 1
console.log(DaysUpToPersianYear(1394))

/*fs.readFile('./2', function(err, data) {
	if (err)
		throw err;
	var a = data.toString().split('\n');
	var oldType, newType, j = 0;
	oldType = parseInt(a[0]) % 2;

	for (var i = 1; i < a.length; i++) {
		newType = parseInt(a[i]) % 2;
		if (oldType != newType) {
			console.log(a[i] - a[j]);
			j = i;
			oldType = newType;
		}
	}
});**/
