const fs = require('fs');

function isLeap(y) {
	y %= 128;
	var x = [0, 4, 8, 12, 16, 20, 24, 28, 33, 37, 41, 45, 49, 53, 57, 61, 66, 70, 74, 78, 82, 86, 90, 95, 99, 103, 107, 111, 115, 119, 123];
	if (x.indexOf(y) > -1)
		return true;
	return false;
}

for (var i = 1; i < 1600; i++) {

	if (isLeap(i)) {
		//console.log(i);
	}
}

fs.readFile('./2', function(err, data) {
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
});