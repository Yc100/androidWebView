/**
 * @description 自定义弹出框.
 * @author wangsl
 */

define(["dojo/dom", "dojo/on", "dojo/_base/declare", "dojo/_base/lang",
    "dojo/query", "dojo/dom-construct",
    "dojo/dom-attr", "dojo/dom-style",
    "dojo/dom-class", "dojo/topic",
    "esri/dijit/Popup"],
  function (dom, on, declare, lang, query, domConstruct, domAttr, domStyle, domClass, topic,
            Popup) {
    //
    var actionsPanel = null;
    //
    var _imagePanel = null;

    var _extraGraphic = null;

    var popupSelf = null;

    var infoPopup = declare([Popup], {
      //
      constructor: function (parameters) {
        //
        this.inherited(arguments);

        lang.mixin(this, parameters);
        actionsPanel = query(".actionsPane", this.domNode)[0];
        //
        var contentPanel = query(".contentPane", this.domNode)[0];

        _imagePanel = domConstruct.create("div", {}, contentPanel, "before");
        domConstruct.destroy(this._actionList);
        popupSelf = this;
      },
      //
      _getSelectedFeature: function (options) {

        var graphic = null;
        //从左侧面板中点击显示InfoWindow
        if (options && options.type === "left") {
          //
          graphic = _extraGraphic;
          this.features = [graphic];
        } else {
          graphic = this.getSelectedFeature();
          if (!graphic) {
            //
            if (this.features) {
              graphic = this.features[0];
            }
          }
        }
        return graphic;
      },

      show: function (type) {
        //debugger;
        // this.inherited(arguments);
        var options = arguments[1];
        //
        var graphic = this._getSelectedFeature(options);
        //

        if (this.showCallBack) {
          //
          this.showCallBack(graphic);
          //
        }
      },
      // hide info window.
      hide: function () {
        //
        this.inherited(arguments);
      },
    });
    //
    return infoPopup;
  });
