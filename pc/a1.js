
const 
	A  = [29, 33, 33, 33],
	B  = [29, 33, 33, 33, 33],
	YA = 128,
	YB = 161,
	Z  = 4166;
	M =[YA, YA, YA, YA, YA, YB,
		YA, YA, YA, YA, YB, 
		YA, YA, YA, YA, YB, 
		YA, YA, YA, YA, YB, 
		YA, YA, YA, YA, YB, 
		YA, YA, YA, YA, YB];
function isLeap(y) 
{
	y = parseInt(y) % Z;
	var sum = 0, i = 0;
	
	while (y > sum) {
		sum += M[i];
		i++;
	}
	i--;
	sum -= M[i];
	return [i, y - sum];
}
	
var s = '';	
for (var i = 0; i < 1500; i++) {
	var a = isLeap(i);
	s += '[' + a[0] + ',' + a[1] + '] ';
}

console.log(s);

