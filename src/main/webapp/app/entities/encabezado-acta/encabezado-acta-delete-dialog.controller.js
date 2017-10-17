(function() {
    'use strict';

    angular
        .module('capturaValidacion18App')
        .controller('Encabezado_actaDeleteController',Encabezado_actaDeleteController);

    Encabezado_actaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Encabezado_acta'];

    function Encabezado_actaDeleteController($uibModalInstance, entity, Encabezado_acta) {
        var vm = this;

        vm.encabezado_acta = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Encabezado_acta.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
