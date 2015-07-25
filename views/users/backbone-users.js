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

    tagName: "div",
    className: "div-cls",
    el: $('#view'),
    template: _.template($('#users-login-tpl').html()),

    events: {
        "click .btn": "btnSave"
    },

    initialize: function () {
        //this.$el = $('#view');
        this.render();
    },

    render: function () {
        this.$el.html(this.template(this.model.toJSON()));
        //$('#view').append(this.el);
        console.log(this.el);
    },

    btnSave: function (e) {
        console.log(this.model.toJSON().email)
    }
});
var v = new DocumentRow({
    model: user
});
