module.exports = TestController;

TestController.$inject = ['HighchartsService'];
function TestController(HighchartsService) {
  let vm = this;
  angular.extend(vm, {
    title: 'Webpack + Angular',
    pie: {},
    graph: {}
  });

  activate();

  function activate() {
    let data = [{
        name: 'Microsoft Internet Explorer',
        y: 56.33
    }, {
        name: 'Chrome',
        y: 24.03,
        // sliced: true,
        // selected: true
    }, {
        name: 'Renard',
        y: 10.38
    }, {
        name: 'Safari',
        y: 4.77
    }, {
        name: 'Opera',
        y: 0.91
    }, {
        name: 'Proprietary or Undetectable',
        y: 0.2
    }];
    vm.pie = HighchartsService.pie('Twitter subjects', data);
    vm.graph = HighchartsService.graph('Twitter subjects', data);
  }
}
