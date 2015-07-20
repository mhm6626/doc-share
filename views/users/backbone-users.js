/**
 * Created by Mohammad on 7/10/15.
 */
var User = Backbone.Model.extend({
	urlRoot: '/users'
});

var user = new User({
	email: 'javad@mhm.it',
	password: '123456'
});
user.save({created: Date.now()});

var DocumentRow = Backbone.View.extend({

	tagName: "form",

	template: _.template($('#users-login-tpl').html()),

	events: {
	 "click .btn": "btnSave",
	 },

	initialize: function () {
		this.$el = $('#view');
	},

	render: function () {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},

	btnSave: function (e) {
		alert(e)
	}
});
var v = new DocumentRow({model: user});
v.render();
v.btnSave(0320)