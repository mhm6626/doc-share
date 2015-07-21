/**
 * Created by mohammad on 7/10/15.
 */

var app = require('app');

app.post('/users', function(req, res) {
	res.send({
		id: 10
	});
});