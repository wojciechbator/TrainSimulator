$(document).ready(function () {
    $(".JQValidate").validate(
        {
            rules: {
                name: {
                    minlength: 3,
                    required: true,
                    remote: {
                        url: "<spring:url value='/register/available.html' />",
                        type: "get",
                        data: {
                            username: function () {
                                return $("#name").val();
                            }
                        }
                    }
                },
                email: {
                    minlength: 5,
                    required: true,
                    email: true
                },
                password: {
                    minlength: 5,
                    required: true
                },
                password_again: {
                    minlength: 5,
                    required: true,
                    equalTo: "#password"
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            unhighlight: function (element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            messages: {
                name: {
                    remote: "Such username already exists!"
                }
            }
        });
});