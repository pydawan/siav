/**
    @author: thiago.monteiro
    @version: 1.0
    @data: 10/11/2015
*/
Number.prototype.formatMoney = function(c, d, t) {
    var n = this, c = isNaN(c = Math.abs(c)) ? 2 : c, 
    d = d == undefined ? "," : d, 
    t = t == undefined ? "." : t, 
    s = n < 0 ? "-" : "", 
    i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", 
    j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};

Number.prototype.money = function() {
    return this.formatMoney(2, ',', '.');
};

Array.prototype.contains = function(obj) {
    if (obj != null) {
        if (typeof (obj) === 'number') {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == obj) {
                    return true;
                }
            }
        } else if (typeof (obj) === 'string') {
            var pattern = '({0})+'.format(obj);
            var regex = new RegExp(pattern, 'g');
            return regex.test(this.join(''));
        }
    }
    return false;
};

Array.prototype.index = function() {
    return this;
};

Array.prototype.empty = function() {
    return this.length == 0;
};

String.prototype.reverse = function() {
    var reversed = '';
    for (var i = this.length - 1; i > -1; i--) {
        reversed += this.charAt(i);
    }
    return reversed;
};

String.prototype.upper = function() {
    return this.toUpperCase();
};

String.prototype.lower = function() {
    return this.toLowerCase();
};

String.prototype.capitalize = function() {
    return this.charAt(0).upper() + this.substring(1, this.length);
};

String.prototype.isdigit = function() {
    return /^(\d)+$/.test(this);
};

String.prototype.isalnum = function() {
    return /[\w\d]+/.test(this);
};

String.prototype.isalpha = function() {
    return /^[a-zA-z]+$/.test(this);
};

String.prototype.isspace = function() {
    return /^\s+$/.test(this);
};

String.prototype.istitle = function() {
    return false;
};

String.prototype.format = function() {
    var result = '';
    var pattern = '';
    var regex = '';
    if (arguments.length == 1) {
        result = this;
        args = arguments[0];
        if (typeof (arguments[0]) === 'string') {
            args = [ arguments[0] ];
        }
        for ( var i in args) {
            pattern = '\\{' + i + '\\}';
            regex = new RegExp(pattern);
            result = result.replace(regex, args[i]);
        }
    } else if (arguments.length > 1) {
        result = this;
        for ( var i in arguments) {
            if (!typeof (arguments[i]) === 'string') {
                return '';
            } else {
                pattern = '\\{' + i + '\\}';
                regex = new RegExp(pattern);
                result = result.replace(regex, arguments[i]);
            }
        }
    }
    return result;
};

String.prototype.isupper = function() {
    return this == this.toUpperCase();
};

String.prototype.islower = function() {
    return this == this.toLocaleLowerCase();
};

String.prototype.ispalindrome = function() {
    return this == this.reverse();
};

String.prototype.times = function(n) {
    var result = '';
    if (n != null && n > 0) {
        for (var i = 0; i < n; i++) {
            result += this;
        }
    }
    return result;
};

String.prototype.startswith = function(s) {
    var result = false;
    if (s != null && typeof (s) === 'string') {
        var pattern = '^(' + s + ')';
        var regex = new RegExp(pattern);
        return regex.test(this);
    }
    return result;
};

String.prototype.endswith = function(s) {
    var result = false;
    if (s != null && typeof (s) === 'string') {
        var pattern = '(' + s + ')$';
        var regex = new RegExp(pattern);
        return regex.test(this);
    }
    return result;
};

String.prototype.ljust = function(width, fillchar) {
    if (width > this.length) {
        return this + fillchar.times(width - this.length);
    }
    return this;
};

String.prototype.rjust = function(width, fillchar) {
    if (width > this.length) {
        return fillchar.times(width - this.length) + this;
    }
    return this;
};

String.prototype.center = function(width, fillchar) {
    switch (arguments.length) {
    case 1:
        if (width != null && typeof (width) === 'number' && width > this.length) {
            var times = width - this.length;
            times = Math.round(times / 2);
            return ' '.times(times) + this + ' '.times(times);
        }
        break;
    case 2:
        var width = arguments[0];
        var fillchar = arguments[1];
        if (width != null && typeof (width) === 'number' && width > this.length && fillchar != null
                && typeof (fillchar) === 'string') {
            var times = width - this.length;
            times = Math.round(times / 2);
            return fillchar.times(times) + this + fillchar.times(times);
        }
        break;
    }
    if (arguments.length == 1) {

    } else if (arguments.length == 2) {
        var width = arguments[0];
        var fillchar = arguments[1];
        if (arguments[0] != null && typeof (arguments[0]) === 'number' && arguments[0] > this.length
                && fillchar != null && typeof (fillchar) === 'string') {
            var times = width - this.length;
            times = Math.round(times / 2);
            return fillchar.times(times) + this + fillchar.times(times);
        }
    }
    return this;
};

String.prototype.swapcase = function() {
    return this.isupper() ? this.lower() : this.upper();
};

String.prototype.join = function(collection) {
    if (typeof (collection) === 'object') {
        return collection.join(this);
    }
    return collection;
};

String.prototype.shuffle = function() {
    if (!this.empty()) {
        var chars = [];
        var random_number = 0;
        var sorted_random_numbers = [];
        var i = 0;
        while (i < this.length) {
            random_number = Math.floor(Math.random() * this.length);
            if (!sorted_random_numbers.contains(random_number)) {
                chars.push(this.charAt(random_number));
                sorted_random_numbers.push(random_number);
                i++;
            }
        }
        return ''.join(chars);
    }
    return this;
};

String.prototype.randomize = function() {
    if (!this.empty()) {
        var chars = [];
        var random_number = 0;
        var i = 0;
        while (i < this.length) {
            random_number = Math.floor(Math.random() * this.length);
            chars.push(this.charAt(random_number));
            i++;
        }
        return ''.join(chars);
    }
    return this;
};

String.prototype.lstrip = function() {
    return this.replace(/^\s+/, '');
};

String.prototype.rstrip = function() {
    return this.replace(/\s+$/, '');
};

String.prototype.strip = function() {
    return this.rstrip().lstrip();
};

String.prototype.count = function() {
    var value = null;
    var modifier = 'g';
    if (arguments.length) {
        value = arguments[0];
    }
    if (arguments.length == 2) {
        modifier = arguments[1];
    }
    if (value && typeof (value) === 'string') {
        return (this.length - this.replace(new RegExp(value, modifier), '').length) / value.length;
    }
    return 0;
};

String.prototype.empty = function() {
    return this.length == 0 ? true : false;
};

String.prototype.zfill = function(size) {
    var times = size - this.length;
    return '0'.times(times) + this;
};

String.prototype.contains = function(e) {
    var array = this.split('');
    return array.contains(e);
};

String.prototype.index = function() {
    return this;
};

String.prototype.splitlines = function() {
    return this.split('\n');
};

String.prototype.partition = function() {
    return this;
};

String.prototype.find = function() {
    return this;
};

String.prototype.expandtabs = function() {
    return this;
};

String.prototype.title = function() {
    return this;
};

String.prototype.encode = function() {
    return this;
};

String.prototype.decode = function() {
    return this;
};

String.prototype.translate = function() {
    return this;
};

String.prototype.mul = function(n) {
    return this.times(n);
};

String.prototype.ascii_lowercase = function() {
    return 'abcdefghijklmnopqrstuvwxyz';
};

String.prototype.ascii_uppercase = function() {
    return 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
};

String.prototype.ascii_letters = function() {
    return this.ascii_lowercase() + this.ascii_uppercase();
};

String.prototype.digits = function() {
    return '0123456789';
};

String.prototype.hexdigits = function() {
    return '0123456789abcdefABCDEF';
};

String.prototype.letters = function() {
    return this;
};

String.prototype.lowercase = function() {
    return this;
};

String.prototype.uppercase = function() {
    return this;
};

String.prototype.octdigits = function() {
    return '01234567';
};

String.prototype.punctuation = function() {
    return '!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~';
};

String.prototype.printable = function() {
    return '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ \t\n\r\x0b\x0c';
};

String.prototype.whitespace = function() {
    return '\t\n\x0b\x0c\r ';
};

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    var regArray = /^([^\[\]]+)\[(\d+)\]$/;
    $.each(a, function(i) {
        var name = this.name;
        var value = this.value;
        // let's also allow for "dot notation" in the input names
        var props = name.split('.');
        var position = o;
        while (props.length) {
            var key = props.shift();
            var matches;
            if (matches = regArray.exec(key)) {
                var p = matches[1];
                var n = matches[2];
                if (!position[p]) position[p] = [];
                if (!position[p][n]) position[p][n] = {};
                position = position[p][n];
            } else {
                if (!props.length) {
                    if (!position[key]) position[key] = value || '';
                    else if (position[key]) {
                        if (!position[key].push) position[key] = [position[key]];
                        position[key].push(value || '');
                    }
                } else {
                    if (!position[key]) position[key] = {};
                    position = position[key];
                }
            }
        }
    });
    return o;
};

// Validação de CNPJ
jQuery.validator.addMethod("cnpj", function(cnpj, element) {
    cnpj = jQuery.trim(cnpj);
    cnpj = cnpj.replace('/', '');
    cnpj = cnpj.replace('.', '');
    cnpj = cnpj.replace('.', '');
    cnpj = cnpj.replace('-', '');

    var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
    digitos_iguais = 1;

    if (cnpj.length < 14 && cnpj.length < 15) {
        return false;
    }
    for (i = 0; i < cnpj.length - 1; i++) {
        if (cnpj.charAt(i) != cnpj.charAt(i + 1)) {
            digitos_iguais = 0;
            break;
        }
    }

    if (!digitos_iguais) {
        tamanho = cnpj.length - 2
        numeros = cnpj.substring(0, tamanho);
        digitos = cnpj.substring(tamanho);
        soma = 0;
        pos = tamanho - 7;

        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(0)) {
            return false;
        }
        tamanho = tamanho + 1;
        numeros = cnpj.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1)) {
            return false;
        }
        return true;
    } else {
        return false;
    }
}, "CNPJ inválido.");

// Validação de telefone fixo
jQuery.validator.addMethod('telefone', function(value, element) {
    return this.optional(element) || /^\(\d{2}\) \d{4}-\d{4}$/.test(value);
}, 'Telefone inválido.');

// Veículo
jQuery.validator.addMethod('placaVeiculo', function(value, element) {
    value = value.replace(/_/g, '');
    return this.optional(element) || /^\w{3}-\d{4}$/.test(value);
}, 'Placa inválida.');

jQuery.validator.addMethod('numeroVeiculo', function(value, element) {
    return this.optional(element) || /^\d{4}-\d{2}$/.test(value);
}, 'Número inválido');

jQuery.validator.addMethod('ano', function(value, element) {
    return this.optional(element) || /^\d{4}$/.test(value);
}, 'Ano inválido.');

jQuery.validator.addMethod('lotacao', function(value, element) {
    return this.optional(element) || /^\d{1}\d?$/.test(value);
}, 'Lotação inválida.');

jQuery.validator.addMethod('data', function(value, element) {
    return this.optional(element) || /^\d{2}\/\d{2}\/\d{4}$/.test(value);
}, 'Data inválida.');

jQuery.validator.addMethod('hora', function(value, element) {
    return this.optional(element) || /^\d{2}\:\d{2}\:\d{2}$/;
}, 'Hora inválida.');

jQuery.validator.addMethod('datahora', function(value, element) {
    return this.optional(element) || /^\d{2}\/\d{2}\/\d{4} \d{2}:\d{2}:\d{2}$/;
}, 'Data e hora inválida.');

jQuery.validator.addMethod('extensao', function(value, element) {
    value = value.replace(' km', '');
    value = value.replace(/\./g, '');
    value = value.replace(',', '.');
    return this.optional(element) || /^\d{1,4}(\.\d{1,2})?$/.test(value);
}, 'Extensão inválida.');

jQuery.validator.addMethod('monetario', function(value, element) {
    value = value.replace('R$ ', '');
    value = value.replace(/\./g, '');
    value = value.replace(',', '.');
    return this.optional(element) || /^\d{1,8}(\.\d{1,2})?$/.test(value);
}, 'Valor monetário inválido.');

jQuery.validator.addMethod('cnh', function(value, element) {
    value = value.replace(/_/g, '');
    return this.optional(element) || /^\d{11}$/.test(value);
}, 'CNH inválida.');

// Campos de formulário inválidos.
$.validator.setDefaults({
    highlight : function(element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight : function(element) {
        $(element).closest('.form-group').removeClass('has-error');
    },
    errorElement : 'span',
    errorClass : 'help-block',
    errorPlacement : function(error, element) {
        if (element.parent('.input-group').length) {
            error.insertAfter(element.parent());
        } else {
            error.insertAfter(element);
        }
    },
});

jQuery.extend(jQuery.validator.messages, {
    required : 'Campo de preenchimento obrigatório.',
    email : 'E-mail inválido.',
    telefone : 'Telefone inválido.',
    celular : 'Celular inválido.',
    cnpj : 'CNPJ inválido.',
    extensao : 'Extensão inválida.',
    equalTo: 'As senhas não conferem.',
    minlength: 'Informe no mínimo {0} caracteres.',
    maxlength: 'Informe no máximo {0} caracteres.',
    number: 'Número inválido.',
    monetario: 'Valor monetário inválido.'
});

function preencher_formulario(form, data, prefix) {
    data = JSON.stringify(data);
    data = $.parseJSON(data);
    var $element;
    $.each(data, function(key, value) {
        prefix = prefix == null ? '' : prefix;
        if (prefix == '') {
            $element = $('[name=' + key + ']', form);
        } else {
            $element = $('[name="' + prefix + key + '"]', form);
        }
        if ($element.is('textarea')) {
            $element.empty().append(value);
        } else {
            $element.attr('value', value);
        }
    });
}

// Configuração de diversos campos do sistema.
$(document).ready(function() {
    // // Torna todos os caracteres digitados maiúsculos.
    // $('textarea, input[type="text"], input[type="email"]').each(function(i,
    // e) {
    // $(this).val($(this).val().toUpperCase());
    // });
    // $('textarea, input[type="text"], input[type="email"]').each(function(i,
    // e) {
    // $(e).blur(function() {
    // $(this).val($(this).val().toUpperCase());
    // console.log($(this).val());
    // });
    // });
    // Tooltips.
    $('[data-toggle="tooltip"]').tooltip({
        'placement' : 'bottom',
        'trigger' : 'hover',
        'delay' : {
            'show' : 100,
            'hide' : 1
        }
    });
    // Máscaras
    $('.cnpj').mask('99.999.999/9999-99');
    $('.fone').mask('(99) 9999-9999');
    $('.placa-veiculo').mask('aaa-9999');
    $('.numero-veiculo').mask('9999-99');
    $('.ano').mask('9999');
    $('.lotacao').mask('99');
    $('.cnh').mask('99999999999');
    $('.extensao').mask('9999');
    // Datas.
    $('.datepicker').mask('99/99/9999');
    $('.input-group.date').datepicker({
        language : 'pt-BR',
        format : 'dd/mm/yyyy',
        showOnFocus : false,
        todayHighlight : true
    });
    // Caixas de seleção.
    $('.selectpicker').selectpicker();
    // Remove os títulos das opções das caixas de seleção.
    $('.selectpicker').next().find('button').attr('title', '');
    $('.selectpicker').change(function(i, e) {
        $(this).next().find('button').attr('title', '');
    });
    $('.monetario').maskMoney({
       'affixes-stay': false,
        prefix: 'R$ ',
        thousands: '.',
        decimal: ',',
        allowNegative: false,
        allowZero: true
    });
    $('.extensao').maskMoney({
       'affixes-stay': false,
        suffix: ' km',
        thousands: '.',
        decimal: ',',
        allowNegative: false,
        allowZero: true
    });
    // Upload de arquivos.
    $('#arquivos input[type=file]').each(function(i, e) {
        var paragrafo = '#nome-' + $(e).attr('id');
        $(e).fileupload({
            dataType : 'json',
            add : function(e, data) {
                $.each(data.files, function(index, file) {
                    $(paragrafo).text(file.name);
                    $(paragrafo).prepend('<span class="glyphicon glyphicon-file"></span> ');
                });
            }
        });
    });
    // Validação do formulário de autorização de viagem.
    $('#form-autorizacao').validate({
        debug : false,
        onfocusout : function(element) {
            $(element).valid();
        },
        rules : {
            'autorizacao.empresa.cnpj' : {
                cnpj : true
            },
            'autorizacao.empresa.razaoSocial' : {
                required : true
            },
            'autorizacao.empresa.nomeFantasia' : {
                required : true
            },
            'autorizacao.empresa.endereco' : {
                required : true
            },
            'autorizacao.empresa.telefone' : {
                telefone : true
            },
            'autorizacao.empresa.email' : {
                required : true,
                email : true
            },
            'autorizacao.empresa.contato' : {
                required : true
            },
            'autorizacao.veiculo.placa' : {
                placaVeiculo : true
            },
            'autorizacao.veiculo.numero' : {
                numeroVeiculo : true
            },
            'autorizacao.veiculo.anoFabricacao' : {
                ano : true
            },
            'autorizacao.veiculo.lotacao' : {
                lotacao : true
            },
            'autorizacao.veiculo.primeiroMotorista' : {
                required : true
            },
            'autorizacao.veiculo.cnhPrimeiroMotorista' : {
                required : true
            },
            'autorizacao.veiculo.segundoMotorista' : {
                required : true
            },
            'autorizacao.veiculo.cnhSegundoMotorista' : {
                required : true
            },
            'autorizacao.veiculo.modelo' : {
                required : true
            },
            'autorizacao.veiculo.modelo.fabricante' : {
                required : true
            },
            'autorizacao.roteiro.origem' : {
                required : true
            },
            'autorizacao.roteiro.destino' : {
                required : true
            },
            'autorizacao.roteiro.distancia' : {
                required : true
            },
            'autorizacao.viagem.quantidadePassageiros' : {
                required : true
            },
            'autorizacao.viagem.dataSaida' : {
                data : true
            },
            'autorizacao.viagem.dataRetorno' : {
                data : true
            },
            'autorizacao.viagem.extensao' : {
                extensao : true
            },
            'autorizacao.viagem.localSaida' : {
                required : true
            },
            'autorizacao.notaFiscal.numero' : {
                required : true
            },
            'autorizacao.notaFiscal.serie' : {
                required : true
            },
            'autorizacao.notaFiscal.dataEmissao' : {
                data : true
            },
            'autorizacao.notaFiscal.valor' : {
                required : true
            },
            'autorizacao.boletoBancario.numero' : {
                required : true
            },
            'autorizacao.boletoBancario.dataPagamento' : {
                data : true
            },
            'autorizacao.boletoBancario.valor' : {
                required : true
            }
        }
    });
    $(':reset').each(function(i, e) {
        $(e).click(function() {
            var form = $(this).closest('form');
            form.validate().resetForm();
            form.find('.has-error').removeClass("has-error");
            form.find('.has-success').removeClass("has-success");
            form.find('.form-control-feedback').remove();
            return false;
        });
    });
});