

window.DSE = {
  // arcgis开发请求路径
  options: {
    url: 'http://sw.nxstjt.com/arcgis_js_v48_api/library/4.8/init.js',
    css: 'http://sw.nxstjt.com/arcgis_js_v48_api/library/4.8/esri/css/main.css',

  },
  // 后台接口
  // baseUrl: 'http://sw.nxstjt.com',
  baseUrl: 'http://www.whdse.cn:56070',
  //贾勋接口
  // baseUrl: 'http://10.100.50.210:8080',
  //雷成洋接口
  // baseUrl:'http://10.100.50.197:8080',
  // 图片路径
  photoUrl: '',

  featureLayerUrl: {
    // flowService: 'http://sw.nxstjt.com/arcgis/rest/services/monitorPointFlowService/MapServer/0',
    // GageService: 'http://sw.nxstjt.com/arcgis/rest/services/monitorPointGageService/MapServer/0',
    // QualityService: 'http://sw.nxstjt.com/arcgis/rest/services/monitorPointQualityService/MapServer/0'
    //测试环境图层
    BasicsService:'http://10.100.50.71:6080/arcgis/rest/services/',
    flowService: 'http://10.100.50.71:6080/arcgis/rest/services/monitorPointFlowService/MapServer/0',
    GageService: 'http://10.100.50.71:6080/arcgis/rest/services/monitorPointGageService/MapServer/0',
    QualityService: 'http://10.100.50.71:6080/arcgis/rest/services/monitorPointQualityService/MapServer/0'

  }
}