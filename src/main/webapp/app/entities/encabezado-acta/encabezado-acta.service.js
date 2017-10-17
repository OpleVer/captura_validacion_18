(function() {
    'use strict';
    angular
        .module('capturaValidacion18App')
        .factory('Encabezado_acta', Encabezado_acta);

    Encabezado_acta.$inject = ['$resource'];

    function Encabezado_acta ($resource) {
        var resourceUrl =  'api/encabezado-actas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
