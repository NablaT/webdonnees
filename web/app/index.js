import angular from 'npm/angular';
import TestController from './test.controller';
import kohakuHighchartsDirective from './highcharts.directive';
import HighchartsService from './highcharts.service';

angular.module('kohaku', [])
  .controller('TestController', TestController)
  .directive('kohakuHighcharts', kohakuHighchartsDirective)
  .factory('HighchartsService', HighchartsService);
