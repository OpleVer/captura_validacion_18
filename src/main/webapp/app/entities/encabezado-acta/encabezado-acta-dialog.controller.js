(function() {
    'use strict';

    angular
        .module('capturaValidacion18App')
        .controller('Encabezado_actaDialogController', Encabezado_actaDialogController);

    Encabezado_actaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Encabezado_acta'];

    function Encabezado_actaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Encabezado_acta) {
        var vm = this;

        vm.encabezado_acta = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.encabezado_acta.id !== null) {
                Encabezado_acta.update(vm.encabezado_acta, onSaveSuccess, onSaveError);
            } else {
                Encabezado_acta.save(vm.encabezado_acta, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('capturaValidacion18App:encabezado_actaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
