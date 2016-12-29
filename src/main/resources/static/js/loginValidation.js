$('.field.example form')
    .form({
        on: 'blur',
        fields: {
            login: {
                identifier: 'login',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Podaj jakiś login'
                    }
                ]
            },
            password: {
                identifier: 'password',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Podaj jakieś hasło'
                    }
                ]
            }
        }
    });