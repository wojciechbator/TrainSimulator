$(document).ready(function () {
    $(".JQValidate").validate(
        {
            rules: {
                name: {
                    minlength: 3,
                    required: true,
                    remote: {
                        url: "<spring:url value='/register/available' />",
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
                $(element).closest('.field').removeClass('success').addClass('error');
            },
            unhighlight: function (element) {
                $(element).closest('.field').removeClass('error').addClass('success');
            },
            messages: {
                name: {
                    remote: "Taka nazwa już istnieje!"
                }
            }
        });
});