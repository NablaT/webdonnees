const Highcharts = require('highcharts');
require('highcharts/modules/exporting')(Highcharts);

module.exports = function() {
  return {
    restrict: 'E',
    replace: true,
    scope: {
      id: '@',
      chart: '='
    },
    template: '<div></div>',
    link: link
  };

  function link(scope, el, attrs, vm) {
    Highcharts.chart(scope.id, scope.chart);
  }
};
