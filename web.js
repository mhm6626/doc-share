var app = require('app');


app.get('/', function(req, res) {
	res.render('main');
});

require('./routes');

app.listen(3000, function() {
	console.log('Server Started...');
});