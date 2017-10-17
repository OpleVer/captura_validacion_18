(function() {
    'use strict';

    angular
        .module('capturaValidacion18App')
        .controller('Encabezado_actaDetailController', Encabezado_actaDetailController);

    Encabezado_actaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Encabezado_acta'];

    function Encabezado_actaDetailController($scope, $rootScope, $stateParams, previousState, entity, Encabezado_acta) {
        var vm = this;

        vm.encabezado_acta = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('capturaValidacion18App:encabezado_actaUpdate', function(event, result) {
            vm.encabezado_acta = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
